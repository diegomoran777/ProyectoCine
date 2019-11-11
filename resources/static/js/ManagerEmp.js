//Te lleva a editar la informacion del empleado
function EditEmployee(Component){
    var idEmployee = Component.parentNode.className;
    window.open("/edit_employee?employeeid="+idEmployee,"_self");
}

function GetReport(Component){
    var idEmployee = Component.parentNode.className;
    window.open("/get_report?employeeid="+idEmployee,"_self");
}

//Banea al empleado
function BanEmployee(Component){
    swal({
          title: "Esta seguro?",
          text: "Luego de banear un empleado solo un administrador puede recuperarlo.",
          icon: "warning",
          buttons: true,
          dangerMode: true,
        })
        .then((willDelete) => {
          if (willDelete) {
            swal("Empleado baneado!", {
              icon: "warning",
            });
            setTimeout("MoveToBan()", 2000);

          } else {
            swal("Accion Revertida!", {
              icon: "success",
            });
          }
        });
}
function MoveToBan(){
    window.open("/ban_employee?employeeid="+idEmployee,"_self");
     }

//Elimina un empleado
function DeleteEmployee(Component){
    swal({
      title: "Esta seguro?",
      text: "Luego de borrar un empleado solo un administrador puede recuperarlo.",
      icon: "warning",
      buttons: true,
      dangerMode: true,
    })
    .then((willDelete) => {
      if (willDelete) {
        swal("Empleado eliminado!", {
          icon: "success",
        });
        setTimeout("MoveToDelete()", 2000);
      } else {
        swal("Accion Revertida!", {
            icon: "success",
        });
      }
    });
}
function MoveToDelete(){
    window.open("/delete_employee?employeeid="+idEmployee,"_self");
    }

//Funcion que te devuelve al menu
function ReturnToMenu(){
    document.getElementById('btnLimpiar').click();
    window.open("/admin_main","_self");
}

function ReturnPage(){
    window.history.back();
}