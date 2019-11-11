function SendMoney(){
    var amount1 = document.getElementById("inCash").value;
    var amount2 = document.getElementById("amountTotal").innerText;
    var payMethod = document.getElementById("payMethod").value;
    if(amount1 >= amount2 && amount1 != "")
    {
        swal({
               title: "Exito",
               text: "Venta efectuada con éxito!",
               icon: "success",
               buttons: true
             });
        setTimeout(function(){ window.open("/metodo_pago?payParam="+payMethod,"_self"); }, 1000);
    }else{
    	swal("Aviso!", "Monto incorrecto!", "warning");
    }

}

function autoSub(){
    var amount1= document.getElementById("inCash").value;
    var amount2= document.getElementById("amountTotal").innerText;
    var change= document.getElementById("change");
    var changeResult= (amount1-amount2);
    change.disabled="false";
    change.value= changeResult;
}

function cashValue(){
	var amount1= document.getElementById("inCash");
    var moneyVal = document.getElementById("payMethod");
    var cash = document.getElementById("cashPay");
    if(cash.onclick){
        moneyVal.value = 1;
        amount1.disabled = false;
     }
 }

function creditValue(){
	var amount1= document.getElementById("inCash");
    var moneyVal = document.getElementById("payMethod");
    var credit = document.getElementById("ml");
    if(credit.onclick){
        moneyVal.value = 2;
        amount1.disabled = false;
     }
     window.open("/mercado_pago","_blank");
}