//Variable que trae el input que guardara el valor del id de reserva
var bookingIdSent = document.getElementById("bookingIdSent");
//Variables que traen los elementos tag
var reservationCode = document.getElementById("reservationCode");
var adminId = document.getElementById("adminId");
var passAdmin = document.getElementById("passAdmin");
var inputIdClientModal = document.getElementById("inputIdClientModal");
var idClientCode = document.getElementById("idClientCode");
//Variables que traen los formularios
var form = document.getElementById("formPass");
var formBookings = document.getElementById("formBookings");
var formAllBookings = document.getElementById("formAllBookings");
var formClientModal = document.getElementById("formClientModal");
//Variables que traen los botones
var closeFormx = document.getElementById("closeFormx");
var closeFormBookings = document.getElementById("closeFormBookings");
var closeFormAllBookings = document.getElementById("closeFormAllBookings");
var closeFormModal = document.getElementById("btnCloseFormModal");
var btnShowUpFormBookings = document.getElementById("btnShowUpFormBookings");
var btnShowUpFormAllBookings = document.getElementById("btnShowUpFormAllBookings");
var btnConsultClient = document.getElementById("btnConsultClient");
var showUpForm = document.getElementById("showUpForm");
var containerbookings = document.getElementById("containerbookings");
var whiteSpace = /^\s+$/;

/**
 * @descriptor Esta funcion va a ser ejecutada cuando se accione el boton cerrar
 * habilitando y deshabilitando vistas
 * @param  {event} este evento de el formulario sera prevenido en caso de que se cierre el form.
 */
closeFormBookings.onclick = function(event){
	event.preventDefault();
	formBookings.style.display = "none";
	btnShowUpFormBookings.style.display = "block";
}

/**
 * @descriptor Esta funcion va a ser ejecutada cuando se accione el boton cerrar
 * habilitando y deshabilitando vistas de botnes, formularios o lo que sea necesario
 * @param  {event} este evento de el formulario sera prevenido en caso de que se cierre el form.
 */
btnShowUpFormBookings.onclick = function(){
	btnShowUpFormBookings.style.display = "none";
	formBookings.style.display = "block";
}

/**
 * @descriptor Esta funcion va a ser ejecutada cuando se accione el boton cerrar
 * habilitando y deshabilitando vistas de botones, formularios o lo que sea necesario 
 * @param  {event} este evento de el formulario sera prevenido en caso de que se cierre el form.
 */
closeFormAllBookings.onclick = function(event){
	event.preventDefault();
	formAllBookings.style.display = "none";
	btnShowUpFormAllBookings.style.display = "block";
}

/**
 * @descriptor Esta funcion va a ser ejecutada cuando se accione el boton cerrar
 * habilitando y deshabilitando vistas de botones, formularios o lo que sea necesario  
 */
btnShowUpFormAllBookings.onclick = function(){
	btnShowUpFormAllBookings.style.display = "none";
	formAllBookings.style.display = "block";
}

/**
 * @descriptor Esta funcion va a ser ejecutada cuando se accione el boton cerrar
 * habilitando y deshabilitando vistas de botones, formularios o lo que sea necesario 
 * @param  {event} este evento de el formulario sera prevenido en caso de que se cierre el form.
 */
closeFormModal.onclick = function(event){
	event.preventDefault();
	formClientModal.style.display = "none";
	showUpForm.style.opacity = "1";
	showUpForm.style.pointerEvents = "auto";
	containerbookings.style.opacity = "1";
	formBookings.style.opacity = "1";
	formBookings.style.pointerEvents = "auto";
	formAllBookings.style.opacity = "1";
	formAllBookings.style.pointerEvents = "auto";
	btnShowUpFormBookings.style.opacity = "1";
	btnShowUpFormBookings.style.pointerEvents = "auto";
	btnShowUpFormAllBookings.style.opacity = "1";
	btnShowUpFormAllBookings.style.pointerEvents = "auto";
	btnConsultClient.style.opacity = "1";
	btnConsultClient.style.pointerEvents = "auto";
}

/**
 * @descriptor Esta funcion va a ser ejecutada cuando se accione el boton cerrar
 * habilitando y deshabilitando vistas de botones, formularios o lo que sea necesario 
 * @param  {event} este evento de el formulario sera prevenido en caso de que se cierre el form.
 */
closeFormx.onclick = function(event){
	event.preventDefault();
	form.style.display = "none";
	showUpForm.style.opacity = "1";
	showUpForm.style.pointerEvents = "auto";
	containerbookings.style.opacity = "1";
	formBookings.style.opacity = "1";
	formBookings.style.pointerEvents = "auto";
	formAllBookings.style.opacity = "1";
	formAllBookings.style.pointerEvents = "auto";
	btnShowUpFormBookings.style.opacity = "1";
	btnShowUpFormBookings.style.pointerEvents = "auto";
	btnShowUpFormAllBookings.style.opacity = "1";
	btnShowUpFormAllBookings.style.pointerEvents = "auto";
	btnConsultClient.style.opacity = "1";
	btnConsultClient.style.pointerEvents = "auto";
}

//Fucncion que activa el formulario de cliente
function activeFormClient(){
	showUpForm.style.opacity = "0.3";
	showUpForm.style.pointerEvents = "none";
	containerbookings.style.opacity = "0.3";
	formBookings.style.opacity = "0.3";
	formBookings.style.pointerEvents = "none";
	formAllBookings.style.opacity = "0.3";
	formAllBookings.style.pointerEvents = "none";
	btnShowUpFormBookings.style.opacity = "0.3";
	btnShowUpFormBookings.style.pointerEvents = "none";
	btnShowUpFormAllBookings.style.opacity = "0.3";
	btnShowUpFormAllBookings.style.pointerEvents = "none";
	btnConsultClient.style.opacity = "0.3";
	btnConsultClient.style.pointerEvents = "none";
	formClientModal.style.display = "block";
}

/**
 * @descriptor Esta funcion va a ser ejecutada cuando se accione el boton de 
 * consultar cliente
 */
btnConsultClient.onclick = function(){
	activeFormClient();
}

/**
 * @descriptor Esta funcion va a ser ejecutada cuando se accione el boton 
 * que va hacia el reembolso
 */
function showUp(){
	showUpForm.style.opacity = "0.3";
	showUpForm.style.pointerEvents = "none";
	form.style.display = "block";
	containerbookings.style.opacity = "0.3";
	formBookings.style.opacity = "0.3";
	formBookings.style.pointerEvents = "none";
	formAllBookings.style.opacity = "0.3";
	formAllBookings.style.pointerEvents = "none";
	form.style.display = "block";
	btnShowUpFormBookings.style.opacity = "0.3";
	btnShowUpFormBookings.style.pointerEvents = "none";
	btnShowUpFormAllBookings.style.opacity = "0.3";
	btnShowUpFormAllBookings.style.pointerEvents = "none";
	btnConsultClient.style.opacity = "0.3";
	btnConsultClient.style.pointerEvents = "none";
}

/**
 * @descriptor Esta funcion va a ser ejecutada cuando se envie el formulario
 * validando que sus campos no esten vacios
 * @param  {event} este evento de el formulario sera prevenido en caso de que se envie el form
 * y no cumpla conlas validaciones.
 */
formAllBookings.onsubmit = function (event){

	if (idClientCode.value.length == 0 || whiteSpace.test(idClientCode.value)) 
	{
		event.preventDefault();
		swal("Aviso","Codigo de cliente invalido!","warning");
	}
}

/**
 * @descriptor Esta funcion va a ser ejecutada cuando se accione se envie el formulario
 * validando que sus campos no esten vacios
 * @param  {event} este evento de el formulario sera prevenido en caso de que se envie el form
 * y no cumpla conlas validaciones.
 */
formBookings.onsubmit = function (event){
	/*
	   Verifica que el campo codigo de reserva no se procese vacio o con espacio en blanco
	   previniendo la accion por default y mostrando el mensaje apropiado 
	*/
	if (reservationCode.value.length == 0 || whiteSpace.test(reservationCode.value)) 
	{
		event.preventDefault();
		swal("Aviso","Codigo de reserva invalido!","warning");
	}
}

/**
 * @descriptor Esta funcion va a ser ejecutada cuando se accione se envie el formulario
 * validando que sus campos no esten vacios
 * @param  {event} este evento de el formulario sera prevenido en caso de que se envie el form
 * y no cumpla conlas validaciones.
 */
form.onsubmit = function(event){
	/*
	   Por cada elemento del formulario verifica que no este vacio ni con espacios en blanco, 
	   previniendo la accion por default del formulario
	   y mostando el aviso paropiado
	*/
	 if (adminId.value.length == 0 || whiteSpace.test(adminId.value))
	 {
	 	event.preventDefault();
		swal("Aviso","Pass o Id de administrador invalido!","warning");
	 }else{
	 	if (passAdmin.value.length == 0 ||  whiteSpace.test(passAdmin.value))
	 	{
	 		event.preventDefault();
	 		swal("Aviso","Pass o Id de administrador invalido!","warning");
	    }else{
	    	if (bookingIdSent.value.length == 0 || whiteSpace.test(bookingIdSent.value))
	    	{
	    		event.preventDefault();
	    		swal("Aviso","No ha cargado una reserva!","warning");
	 	    }
	    }
	 }
}


/**
 * @descriptor Esta funcion va a ser ejecutada cuando se accione se envie el formulario
 * validando que sus campos no esten vacios
 * @param  {event} este evento de el formulario sera prevenido en caso de que se envie el form
 * y no cumpla conlas validaciones.
 */
formClientModal.onsubmit = function (event){
	/*
	   Verifica que el campo id del cliente no se procese vacio o con espacio en blanco
	   previniendo la accion por default y mostrando el mensaje apropiado 
	*/
	if (inputIdClientModal.value.length == 0 || whiteSpace.test(inputIdClientModal.value)) 
	{
		event.preventDefault();
		swal("Aviso","Ingrese un codigo de cliente!","warning");
	}
}

// Tooltips para los campos
tippy('#reservationCode',{content: 'Campo requerido'});
tippy('#adminId',{content: 'Campo requerido'});
tippy('#passAdmin',{content: 'Campo requerido'});
tippy('#btnShowUpFormBookings',{content: 'Id reserva'});
tippy('#btnShowUpFormAllBookings',{content: 'Listar reservas con Id cliente'});
tippy('#btnConsultClient',{content: 'Consultar info cliente'});
tippy('#showUpForm',{content: 'Reembolsar'});
