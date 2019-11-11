//Defino elelemento tarifa sugerida
var rateSuggested = document.getElementById("suggested");
var month = "";

//Funcion para enviar a pagina de votacione en caso de aceptar
function sendRate(element){
	month = element;
	swal({
        title: "Aviso",
        text: "Desea seleccionar esta tarifa?",
        icon: "warning",
        buttons: true,
        dangerMode: true,
      })
      .then((selectionRate) => {
        if (selectionRate) {
          swal("Tarifa "+ rateSuggested.innerText +" seleccionada!", {
            icon: "warning",
          });
          setTimeout("MoveToVote()", 2000);
        }
      });
}

//Funcion que redirecciona a pagina de votacion
function MoveToVote(){
	window.open("/cambiar_tarifa?monthRate="+month,"_self");
}