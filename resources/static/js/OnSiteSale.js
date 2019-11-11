var idMovie = -1;
var idFunction;

   // Borra todos los colores de fondo de los tag td
document.getElementById("body").onload = function(){
	$('td').on('click', function(){
		$('td').css("background-color" , "");
		});
	
	
	 // Llena de un determinado color el fondo de los tag td  
	$('td').on('click', function(){
		$(this).css("background-color" , "rgb(4, 124, 179)");
		});
	}


  // Guarda el valor del id de la pelicula en una variable
function saveValue(element){
	var valueId = $(element).attr("class");
	idMovie = valueId;
}

 
  // Guarda el valor del id de la funcion, pelicula y fecha en una variables y redirecciona a la ruta del controller correspondiente
function saveBooking(element){
	if (idMovie == -1){
		swal("Aviso", "Seleccione una pelicula!", "warning");
	}else{
		idFunction = $(element).attr("class");
		date = $('#idDate').attr("class");
		window.open("/presencial_cantentradas?functionId="+idFunction+"&movieId="+idMovie+"&dateShow="+date,"_self");
	}
}

  // Redirecciona al controller para sumar un dia
function addDays(){
	date = $('#idDate').attr("class");
	window.open("/sumar_fecha?datePage="+date,"_self");
}

// Redirecciona al controller para restar un dia
function removeDays(){
	date = $('#idDate').attr("class");
	window.open("/restar_fecha?datePage="+date,"_self");
}