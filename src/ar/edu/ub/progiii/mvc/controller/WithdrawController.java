package ar.edu.ub.progiii.mvc.controller;

import ar.edu.ub.progiii.mvc.dto.ClientDTO;
import ar.edu.ub.progiii.mvc.repository.Data;
import ar.edu.ub.progiii.mvc.service.ClientService;

import java.util.Arrays;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class WithdrawController {

	@Autowired
	ClientService clientService;
	Data data = new Data();
	
	/**
       Metodo que te lleva a la vista de Alivio
       @param request session 
       @return model
     */
	@GetMapping("/withdraw")
	public ModelAndView GetView(HttpServletRequest request) {
		if(request.getSession() == null || request.getSession().getAttribute("EmployeeId") == null) {
	           return BaseController.RedirectToMenu();
	        }
		ModelAndView model = new ModelAndView("Withdraw");
		model.addObject("totalVirtual",clientService.GetTotalVirtual((int)request.getSession().getAttribute("EmployeeId")));
		return model;
	}

	/**
    Metodo que te lleva a la vista de Alivio
    @param request session
    @param adminId
    @param passAdmin
    @param withdrawDoneSent monto de la diferencia entre total y lo que se quiere aiviar
    @return model 
  */
	@PostMapping("/withdrawValidate")
	public ModelAndView ValidateWithdraw(@RequestParam("adminId") String adminId, @RequestParam("passAdmin") String passAdmin, 
			@RequestParam("withdrawDoneSent") String withdrawDoneSent, HttpServletRequest request) {
		if(request.getSession() == null || request.getSession().getAttribute("EmployeeId") == null) {
	           return BaseController.RedirectToMenu();
	        }
		if((clientService.GetEmployeeCategory(Integer.parseInt(adminId)) == 3  && clientService.verifyEmployeeLogin(adminId, passAdmin, false))) {
			double virtualTotal = Double.parseDouble(withdrawDoneSent);
			data.UpdateVirtualTotal((int)request.getSession().getAttribute("EmployeeId"), virtualTotal);
			ModelAndView model = new ModelAndView("Withdraw");
			model.addObject("Content", Arrays.asList("success", "El alivio de caja fue realizado!", "1"));
			return model;
		}
		ModelAndView model = new ModelAndView("Withdraw");
		model.addObject("totalVirtual",clientService.GetTotalVirtual((int)request.getSession().getAttribute("EmployeeId")));
		model.addObject("Content", Arrays.asList("Aviso", "Los datos del supervisor son incorrectos!", "2"));
		return model;
	}

}
