package ar.edu.ub.progiii.mvc.controller;

import ar.edu.ub.progiii.mvc.repository.Data;
import ar.edu.ub.progiii.mvc.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Controller
public class PaymentMethodController {
    @Autowired
    ClientService clientService;
    Data data = new Data();
    @GetMapping("/metodo_pago")
    public ModelAndView GetPaymentMethod(@RequestParam("payParam") String payValue, HttpServletRequest request){
    	if(request.getSession() == null || request.getSession().getAttribute("EmployeeId") == null) {
            return BaseController.RedirectToMenu();
         }
        int employeeId = (int)request.getSession().getAttribute("EmployeeId");
        int payNumber = Integer.parseInt(payValue);
        ModelAndView model = new ModelAndView("PaymentMethod");
        data.UpdateLastBooking("CodEstadoReserva",3, data.GetLastBookingByEmployeeId(employeeId));
        data.UpdateLastBooking("CodMedioPago",payNumber, data.GetLastBookingByEmployeeId(employeeId));
        model.addObject("Content", Arrays.asList("Exito", "El pago se ha realizado con exito, esta siendo redirigido al menu", "1"));
        return model;
    }
    
    @GetMapping("/mercado_pago")
    public ModelAndView GetMp(HttpServletRequest request){
    	if(request.getSession() == null || request.getSession().getAttribute("EmployeeId") == null) {
            return BaseController.RedirectToMenu();
         }
        ModelAndView modelMp = new ModelAndView("MercadoPago");
        return modelMp;
    }
}
