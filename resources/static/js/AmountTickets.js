//Variable que guarda el total del valor de entradas
var total = 0;
//Variable que guarda las cantidades de entradas por categoria para enviar al backend
var sent = "";
//Variables que traen el elemento correspondiente a su id para utilizar sus valores
var totalPay = document.getElementById("pesos");
//Variables que guarda el elemento formulario
var formClient = document.getElementById("formClient");
//Variable que guarda el valor del elemento input del cliente 
var inputIdClient = document.getElementById("inputIdClient");
//Variable que guarda el elemento del formulario de registro del cliente
var formRegisterClient = document.getElementById("formRegisterClient");
//Variables que guardan los elementos input del formulario de registro del cliente
//para hacer las validaciones correspondientes
var completeName = document.getElementById("completeName");
var email = document.getElementById("email");
var date = document.getElementById("date");
var dni = document.getElementById("dni");
//Trae el elemento del boton para cerrar el formulario
var closeFormBtn = document.getElementById("closeFormBtn");
//Trae el div contenedor de enetradas
var containerTables = document.getElementById("containerTables");
//Trae el elemento que contiene el panel de botones
var panel = document.getElementById("panel");
//Regex para validar que no haya espacios vacios en los campos del formulario
var whiteSpace = /^\s+$/;
//Arrays
var elementNames = [ 'Menor', 'Jubilado', 'Adulto', 'Promo2x1', 'RegistradoAdulto', 'RegistradoMenor', 'RegistradoMayor' ];
var counters = [];
var inputs = [];
var elements = [];

//Carga la funcion para crear los elementos luego de cargar la pagina
document.getElementById("AmountBody").onload = function(){
	cargar();
}

function cargar(){
	var elem;
	for (elem in elementNames) {
		elements.push(document.getElementById(elementNames[elem]));
		inputs.push(document.getElementById("input" + elementNames[elem]));
		counters.push(0);
	}
	
}

/* Funcion para aumnetar la cantidad de entradas
   @param {element} elemento proveniente de la pagina referido a el nmobre de categoria 
*/
function add(element) {
  var i;
  for(i = 0; i < inputs.length; i++) {
    if(element == elementNames[i]) {
      counters[i]++;
      total += parseInt(inputs[i].value);
      totalPay.innerHTML = "$" + total;
      elements[i].innerHTML = counters[i];
    }
  }
}

/* Funcion para restar la cantidad de entradas
   @param {element} elemento proveniente de la pagina referido a el nmobre de categoria 
*/
function minus(element) {
  var i;
  for(i = 0; i < inputs.length; i++) {
    if(element == elementNames[i] && counters[i] != 0) {
      counters[i]--;
      total = total - parseInt(inputs[i].value);
      totalPay.innerHTML = "$" + total;
      elements[i].innerHTML = counters[i];
    }
  }
}

/**
 * @descriptor Esta funcion va a ser ejecutada cuando se accione el boton para ir a la 
 * pagina de pago.
 * Valida que aunque sea se haya agregado una entrada de alguna categoria, de lo contrario
 * no deja continuar dando un aviso.
 */
function pay(){
	if (total == 0) {
		swal("Aviso", "No hay entradas seleccionadas!", "warning");
	}else {
		var counter = null;
		var i = 0;
		var values = "";
		for(i = 0; i < counters.length; i++) {
			if(i == 0){
				values = values + "categories=" + counters[i];
			}else{
				values = values + "," + counters[i];
			}
		}
		window.open("/presencial_pagar?" + values + "&total="+total,"_self");
	}
}

/**
 * @descriptor Esta funcion va a ser ejecutada cuando se accione el boton para volver 
 * a la pagina de seleccion de pelicula y funcion.
 */
function back(){
	window.open("/presencial_volver","_self");
}

/**
 * @descriptor Esta funcion va a ser ejecutada cuando se aprete el boton de submit
 * de ingreso del cliente, validando el input id.
 * @param {event}
 */
formClient.onsubmit = function(event){
	if (formClient.elements[0].value == "" || whiteSpace.test(formClient.elements[0].value)) {
		event.preventDefault();
	    swal("Aviso", "Asegurese de ingresar un id Cliente antes de continuar!", "warning");
	}
}

/**
 * @descriptor Esta funcion va a ser ejecutada cuando se aprete el boton de submit
 * del formulario de registro de cliente, validando sus campos.
 * @param {event}
 */
formRegisterClient.onsubmit = function(event){
	if (completeName.value == "" || whiteSpace.test(completeName.value)) {
		event.preventDefault();
	    swal("Aviso", "Verifique los campos!", "warning");
	}
	if (dni.value == "" || whiteSpace.test(dni.value)) {
		event.preventDefault();
	    swal("Aviso", "Verifique los campos!", "warning");
	}
	if (date.value == "" || whiteSpace.test(date.value)) {
		event.preventDefault();
	    swal("Aviso", "Verifique!", "warning");
	}
	if (email.value == "" || whiteSpace.test(email.value)) {
		event.preventDefault();
	    swal("Aviso", "Verifique los capos!", "warning");
	}
}

/**
 * @descriptor Esta funcion va a ser ejecutada cuando se aprete el boton para
 * cerrar el formulario de registro de clientes, cerrando el formulario y 
 * activando la pagina principal.
 * @param {event}
 */
closeFormBtn.onclick = function(event){
	event.preventDefault();
	formRegisterClient.style.display = "none";
	formClient.style.opacity = "1"
	formClient.style.pointerEvents = "auto";
	panel.style.opacity = "1";
	panel.style.pointerEvents = "auto";
	containerTables.style.opacity = "1";
	containerTables.style.pointerEvents = "auto";
}

/**
 * @descriptor Esta funcion va a ser ejecutada cuando se aprete el boton para
 * tener acceso al formulario de registro de clientes, abriendo el formulario y 
 * desactivando la pagina principal.
 * @param {event}
 */
function registerClient(){
	formRegisterClient.style.display = "block";
	formClient.style.opacity = "0.3";
	formClient.style.pointerEvents = "none";
	panel.style.opacity = "0.3";
	panel.style.pointerEvents = "none";
	containerTables.style.opacity = "0.3";
	containerTables.style.pointerEvents = "none";
}

//Funcion reservada para asociar tooltips a determinados campos
//trayendolos por id y popniendole un mensaje a el campo
tippy('#name',{content: 'Campo requerido'});
tippy('#date',{content: 'Campo requerido'});
tippy('#email',{content: 'Campo requerido'});
tippy('#dni',{content: 'Campo requerido'});