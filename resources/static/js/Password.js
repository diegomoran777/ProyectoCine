var form = document.getElementById("formPass");
var oldPass = document.getElementById("oldPass");
var newPass = document.getElementById("newPass");
var repeatPass = document.getElementById("repeatPass");
var whiteSpace = /^\s+$/;
var specialCharacter = /[_\\\/]/;
/**

 * Esta funcion va a ser ejecutada cuando se accione el input submit del formulario

 * @param  {event}

 * @return  {string}

 */
form.onsubmit = function (event){

	/*  Verifica si el tag traido por id esta vacio o es un espacio en blanco
	    de ser asi previene el evento por default del formulario y ejecuta el alert 
	*/
	if (oldPass.value == "" || whiteSpace.test(id.value)) {
		event.preventDefault();
		swal("Aviso","Verificar los campos!","warning");
	}
	/*  Verifica si el tag traido por id esta vacio o es un espacio en blanco
	    de ser asi previene el evento por default del formulario y ejecuta el alert 
	*/
	if (newPass.value == "" || whiteSpace.test(id.value) || specialCharacter.test(newPass.value)){
		event.preventDefault();
		swal("Aviso","Verificar los campos!","warning");
	}
	/*  Verifica si el tag traido por id esta vacio o es un espacio en blanco
	    de ser asi previene el evento por default del formulario y ejecuta el alert 
	*/
	if (repeatPass.value == "" || whiteSpace.test(id.value) || specialCharacter.test(repeatPass.value)){
	 	event.preventDefault();
		swal("Aviso","Verificar los campos!","warning");
	}
	/*  Verifica si los campos repeat y new pass son distintos
	    de ser asi previene el evento por default del formulario y ejecuta el alert 
	*/
	if (newPass.value != repeatPass.value){
			event.preventDefault();
			swal("Aviso","Pass distintos","warning");
		}
}
// Tooltips para cada campo con el mensaje en cuestion
tippy('#Id',{content: 'Campo requerido'});
tippy('#oldPass',{content: 'Campo requerido'});
tippy('#newPass',{content: 'Campo requerido, caracteres no permitidos ( /, _ )'});
tippy('#repeatPass',{content: 'Campo requerido, caracteres no permitidos ( /, _ )'});