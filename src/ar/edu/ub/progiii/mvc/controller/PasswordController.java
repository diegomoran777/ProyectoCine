package ar.edu.ub.progiii.mvc.controller;

import java.util.Arrays;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import ar.edu.ub.progiii.mvc.mapping.MappingTool;
import ar.edu.ub.progiii.mvc.repository.Connection;
import ar.edu.ub.progiii.mvc.repository.Data;
import ar.edu.ub.progiii.mvc.service.ClientService;

@Controller
public class PasswordController {

	@Autowired
	ClientService clientService;
	Data data = new Data();
	Connection connection = new Connection();
	MappingTool map = new MappingTool();

	/**
	 * Llama a la vista cambio de clave
	 * @param request
	 * @return
	 */
	@GetMapping("/cambio_clave")
	public ModelAndView GetPassView(HttpServletRequest request) {
		if(request.getSession() == null || request.getSession().getAttribute("EmployeeId") == null) {
	           return BaseController.RedirectToMenu();
	        }
		if(clientService.IsEmployeeAlowed((int)request.getSession().getAttribute("EmployeeId"))) {
			ModelAndView model = new ModelAndView("Password");
			model.addObject("currentID",(int)request.getSession().getAttribute("EmployeeId"));
			return model;
		}
		ModelAndView modelError = new ModelAndView("ErrorPage");
 		modelError.addObject("Contenido", Arrays.asList("Error","El usuario no tiene acceso a esta pagina, redireccionando a login!","/"));
		return modelError;
		
	}

	/**
	 * Metodo que te permite modificar la clave
	 *O en caso de ingresar mal la clave actual, no te permite cambiarla
	 * y te notifica que fuiste bloqueado
	 * @param employeeId
	 * @param oldPass
	 * @param newPass
	 * @return
	 */
	@PostMapping("/pass_sent")
	public ModelAndView changePass(@RequestParam("EmployeeId") String employeeId, @RequestParam("oldPass") String oldPass, @RequestParam("newPass") String newPass, HttpServletRequest request) {
		if(request.getSession() == null || request.getSession().getAttribute("EmployeeId") == null) {
	           return BaseController.RedirectToMenu();
	        }
		int aux = clientService.changePass(employeeId, oldPass, newPass, request);
		if(aux == 1) {
			ModelAndView modelSucces = new ModelAndView("Password");
			modelSucces.addObject("Content", Arrays.asList("Exito","La clave ha sido cambiada con exito!","1"));
			return modelSucces;
		}
		if(aux == 2) {
			ModelAndView modelError = new ModelAndView("ErrorPage");
	 		modelError.addObject("Contenido", Arrays.asList("Error","Clave incorrecta!","/cambio_clave"));
			return modelError;
		}
			ModelAndView modelError = new ModelAndView("ErrorPage");
	 		modelError.addObject("Contenido", Arrays.asList("Error","Clave incorrecta, 3 intentos fallidos, usuario baneado!","/"));
			return modelError;
	}
}
