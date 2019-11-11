//Declaracion de variables
var inTotal = document.getElementById("totalSales");
var amountTaken = document.getElementById("amountTaken");
var amountSelected = amountTaken; 
var totalSales = inTotal;
var withdrawDone = 0; 
var withdrawDoneSent = document.getElementById("withdrawDoneSent");
var forms = document.getElementById("formPasss");
var adminId = document.getElementById("adminId");
var passAdmin = document.getElementById("passAdmin");
var closeFormx = document.getElementById("closeFormx");
var whiteSpace = /^\s+$/;
//Fin declaración de variables

//Verifico que el valor ingresado para retirar sea valido, e igual o mejor que el total
//vendido.
function VerifyWithdraw(){
    amountSelected = parseInt(amountTaken.value);
    totalSales = parseInt(inTotal.value);
    if(amountSelected < 1 || amountSelected > totalSales || amountSelected === null || Number.isNaN(amountSelected)){
        amountSelected.value = "";
        return false;
    }
    return true;
}

//Pide la credencial de un supervisor.
function AskForId(){
    if(VerifyWithdraw()){
    	totalSales = parseInt(inTotal.value);
    	amountSelected = parseInt(amountTaken.value);
    	withdrawDone = totalSales - amountSelected;
    	withdrawDoneSent.value = withdrawDone;
        ShowSimpleMessage('Por favor ingrese credenciales del supervisor!');
        forms.style.display = "block";
        //pedir credenciales
    }else{
          ShowSimpleMessage('Monto ingresado invalido!');
    }
}

//Muestra un mensaje tipo pop up.
function ShowSimpleMessage(text){
	swal(text);
}

/**
 * @descriptor Esta funcion va a ser ejecutada cuando se accione se envie el formulario
 * validando que sus campos no esten vacios
 * @param  {event} este evento de el formulario sera prevenido en caso de que se envie el form
 * y no cumpla conlas validaciones.
 */
forms.onsubmit = function(event){
	/*
	   Por cada elemento del formulario verifica que no este vacio ni con espacios en blanco, 
	   previniendo la accion por default del formulario
	   y mostando el aviso paropiado
	*/
	 if (Number.isNaN(adminId.value) || whiteSpace.test(adminId.value))
	 {
	 	event.preventDefault();
		swal("Aviso","Pass o Id de administrador invalido!","warning");
	 }else{
	 	if (passAdmin.value.length == 0 || whiteSpace.test(passAdmin.value))
	 	{
	 		event.preventDefault();
	 		swal("Aviso","Pass o Id de administrador invalido!","warning");
	    }
	 }
}

//Cierra el form de credenciales
closeFormx.onclick = function(event){
	event.preventDefault();
	forms.style.display = "none";
}