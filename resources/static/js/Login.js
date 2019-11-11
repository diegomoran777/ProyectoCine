// Se definio la variable asignandole el tag por ID del formulario
var formEmployee = document.getElementById("formPass");
// Regex para campos vacios
var whiteSpace = /^\s+$/;

/**
 * @descriptor Esta funcion va a ser ejecutada cuando se accione el boton submit del formulario
 * @param  {event} parametro en el cual se va a prevenir el evento del formulario
 */

formEmployee.onsubmit = function(event){

	/*
	   Recorre los elementos del formulario y verifica por cada elemento del mismo
	   que no este vacio ni con espacios en blanco, previniendo la accion por default del formulario
	   y mostrando el aviso apropiado
	*/

	for (var i = 0; i < formEmployee.length; i++){
		if(formEmployee.elements[i].value == "" || whiteSpace.test(formEmployee.elements[i].value)){
			event.preventDefault();
			swal("Aviso", "Verifique los campos!", "warning");
		}
	}
}

// Funcion que produce una tooltip en cada campo, recibiendo el id del tag y el mensaje que queremos mostrar
tippy("#EmployeeId", {content: "Campo requerido"});
tippy("#EmployeePass", {content: "Campo requerido"});