package ar.edu.ub.progiii.mvc.controller;

import ar.edu.ub.progiii.mvc.dto.TicketDTO;
import ar.edu.ub.progiii.mvc.service.ClientService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import sun.misc.Request;

import java.util.Arrays;

@Controller
public class TicketsController {

    @Autowired
    ClientService clientService;
    /**
    Metodo que te lleva a la vista de Tickets activos
     */
    @GetMapping("/tickets")
    public ModelAndView GetTickets(HttpServletRequest request){
    	if(request.getSession() == null || request.getSession().getAttribute("EmployeeId") == null) {
            return BaseController.RedirectToMenu();
         }
    	ModelAndView model = new ModelAndView("Tickets");
        model.addObject("Tickets",clientService.GetActiveTickets());
        return model;
    }
    /**
    Metodo que te lleva a la vista de todos los Tickets
     */
    @GetMapping("/tickets_all")
    public ModelAndView GetTicketsAll(HttpServletRequest request){
    	if(request.getSession() == null || request.getSession().getAttribute("EmployeeId") == null) {
            return BaseController.RedirectToMenu();
         }
    	ModelAndView model = new ModelAndView("TicketsAll");
        model.addObject("Tickets",clientService.GetAllTickets());
        return model;
    }
    /**
    Metodo que te lleva a la vista para añadir Tickets
     */
    @GetMapping("/add_ticket")
    public ModelAndView AddTicket(HttpServletRequest request){
    	if(request.getSession() == null || request.getSession().getAttribute("EmployeeId") == null) {
            return BaseController.RedirectToMenu();
         }
    	ModelAndView model = new ModelAndView("TicketAdd");
        model.addObject("Valor","Hola");
        return model;
    }
    /**
    Metodo que te lleva a la vista de crear Tickets
     */
    @PostMapping("/create_ticket")
    public ModelAndView CreateTicket(TicketDTO ticketDTO, HttpServletRequest request){
    	if(request.getSession() == null || request.getSession().getAttribute("EmployeeId") == null) {
            return BaseController.RedirectToMenu();
         }
    	RedirectView redirectView = new RedirectView("/tickets");
        redirectView.setExposePathVariables(false);
        int result = clientService.CreateTicket(ticketDTO);
        if(result == 1) {
            return new ModelAndView(redirectView);
        }
        ModelAndView model = new ModelAndView("ErrorPage");
        model.addObject("Content",Arrays.asList("Error al crear Ticket","Ocurrio un error al crear el " +
                    "ticket, por facor consulte con un supervisor", "/admin_main"));
        return model;
    }
    /**
    Metodo que te lleva a la vista de cerrar Tickets
     */
    @GetMapping("/close_ticket")
    public ModelAndView CloseTicket(@RequestParam("ticket")int TicketNumber, HttpServletRequest request){
    	if(request.getSession() == null || request.getSession().getAttribute("EmployeeId") == null) {
            return BaseController.RedirectToMenu();
         }
    	RedirectView redirectView = new RedirectView("/tickets");
        if(clientService.CloseTicket(TicketNumber,(int)request.getSession().getAttribute("EmployeeId"))){
            return new ModelAndView(redirectView);
        }else{
            ModelAndView errorModel = new ModelAndView("ErrorPage");
            errorModel.addObject("Contenido", Arrays.asList("Error","Ocurrio un error al cerrar el ticket," +
                    "no hay empleado logeado o el empleado actual no tiene los permisos requeridos.", "/admin_main"));
            return errorModel;
        }

    }
}
