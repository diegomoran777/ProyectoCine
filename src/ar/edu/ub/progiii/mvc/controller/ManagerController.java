package ar.edu.ub.progiii.mvc.controller;

import ar.edu.ub.progiii.mvc.dto.ClientDTO;
import ar.edu.ub.progiii.mvc.dto.EmployeeDTO;
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

import java.util.Arrays;

@Controller
public class ManagerController {

    @Autowired
    ClientService clientService;
    /**
    Metodo que te lleva a la vista del gerente principal
     */
    @GetMapping("/admin_main")
    public ModelAndView GetMain(HttpServletRequest request){
    	if(request.getSession() == null || request.getSession().getAttribute("EmployeeId") == null) {
            return BaseController.RedirectToMenu();
         }
        ModelAndView model = new ModelAndView("ManagerPage");
        return model;
    }
    /**
    Metodo que te permite administrar los empleados
     */
    @GetMapping("/manage_employees")
    public ModelAndView GetEmployeesView(HttpServletRequest request){
    	if(request.getSession() == null || request.getSession().getAttribute("EmployeeId") == null) {
            return BaseController.RedirectToMenu();
         }
        ModelAndView model = new ModelAndView("ManagerEmployees");
        model.addObject("EmployeeList",clientService.GetAllEmployees());
        return model;
    }
    /**
    Metodo que te permite banear empleado
    luego te redirige o a la vista de gerente principal
    o a la vista para administrar empleados
     */
    @GetMapping("/ban_employee")
    public ModelAndView BanEmployee(@RequestParam("employeeid")int EmployeeNumber, HttpServletRequest request){
    	if(request.getSession() == null || request.getSession().getAttribute("EmployeeId") == null) {
            return BaseController.RedirectToMenu();
         }
        if(clientService.BanEmployee(EmployeeNumber) == 1 ){
            RedirectView redirectView = new RedirectView("/manage_employees");
            redirectView.setExposePathVariables(false);
            return new ModelAndView(redirectView);
        }else{
            RedirectView redirectView = new RedirectView("/admin_main");
            redirectView.setExposePathVariables(false);
            return new ModelAndView(redirectView);
        }
    }
    /**
    Metodo que te lleva a la vista para eliminar empleados
     */
    @GetMapping("/delete_employee")
    public ModelAndView DeleteEmployee(@RequestParam("employeeid")int EmployeeNumber, HttpServletRequest request){
    	if(request.getSession() == null || request.getSession().getAttribute("EmployeeId") == null) {
            return BaseController.RedirectToMenu();
         }
        if(clientService.DeleteEmployee(EmployeeNumber) == 1 ){
            RedirectView redirectView = new RedirectView("/manage_employees");
            redirectView.setExposePathVariables(false);
            return new ModelAndView(redirectView);
        }else{
            RedirectView redirectView = new RedirectView("/admin_main");
            redirectView.setExposePathVariables(false);
            return new ModelAndView(redirectView);
        }
    }
    /**
    Metodo que te lleva a la vista para editar empleados
     */
    @GetMapping("/edit_employee")
    public ModelAndView GetEditPage(@RequestParam("employeeid")int EmployeeNumber, HttpServletRequest request){
    	if(request.getSession() == null || request.getSession().getAttribute("EmployeeId") == null) {
            return BaseController.RedirectToMenu();
         }
        ModelAndView model = new ModelAndView("EditEmployee");
        EmployeeDTO employeeDTO = clientService.GetEmployee(EmployeeNumber);
        model.addObject("empleado",employeeDTO);
        return model;
    }
    /**
    Metodo que te lleva a la vista para actualizar empleados
     */
    @PostMapping("/update_employee")
    public ModelAndView UpdateEmployee(EmployeeDTO employee, HttpServletRequest request){
    	if(request.getSession() == null || request.getSession().getAttribute("EmployeeId") == null) {
            return BaseController.RedirectToMenu();
         }
        clientService.UpdateEmployee(employee);
        RedirectView redirectView = new RedirectView("/manage_employees");
        redirectView.setExposePathVariables(false);
        return new ModelAndView(redirectView);
    }
    /**
    Metodo que te lleva a la vista para administrar clientes
     */
     @GetMapping("/manage_clients")
    public ModelAndView GetClients(HttpServletRequest request){
    	 if(request.getSession() == null || request.getSession().getAttribute("EmployeeId") == null) {
             return BaseController.RedirectToMenu();
          }
        ModelAndView model = new ModelAndView("ManagerClients");
        model.addObject("ClientList",clientService.GetAllClients());
        return model;
    }
    /**
    Metodo que te lleva a la vista para banear clientes
     */
    @GetMapping("/ban_client")
    public ModelAndView BanClient(@RequestParam("clientid")int ClientNumber, HttpServletRequest request){
    	if(request.getSession() == null || request.getSession().getAttribute("EmployeeId") == null) {
            return BaseController.RedirectToMenu();
         }
        if(clientService.BanClient(ClientNumber) == 1 ){
            RedirectView redirectView = new RedirectView("/manage_clients");
            redirectView.setExposePathVariables(false);
            return new ModelAndView(redirectView);
        }else{
            ModelAndView model = new ModelAndView("ErrorPage");
            model.addObject("Content", Arrays.asList("Error","Ocurrio un error al Banear el cliente", "/admin_main"));
            return model;
        }
    }
    /**
    Metodo que te lleva a la vista para eliminar clientes
     */
    @GetMapping("/delete_client")
    public ModelAndView DeleteClient(@RequestParam("clientid")int ClientNumber, HttpServletRequest request){
    	if(request.getSession() == null || request.getSession().getAttribute("EmployeeId") == null) {
            return BaseController.RedirectToMenu();
         }
        if(clientService.DeleteClient(ClientNumber) == 1 ){
            RedirectView redirectView = new RedirectView("/manage_clients");
            redirectView.setExposePathVariables(false);
            return new ModelAndView(redirectView);
        }else{
            ModelAndView model = new ModelAndView("ErrorPage");
            model.addObject("Content", Arrays.asList("Error","Ocurrio un error al borrar el cliente", "/admin_main"));
            return model;
        }
    }

    @GetMapping("/edit_client")
    public ModelAndView EditClient(@RequestParam("clientid")int ClientNumber, HttpServletRequest request){
    	if(request.getSession() == null || request.getSession().getAttribute("EmployeeId") == null) {
            return BaseController.RedirectToMenu();
         }
        ModelAndView model = new ModelAndView("EditClient");
        model.addObject("client",clientService.GetClientByUID(String.valueOf(ClientNumber)));
        return model;
    }

    @PostMapping("/update_client")
    public ModelAndView UpdateClient(ClientDTO clientDTO, HttpServletRequest request){
    	if(request.getSession() == null || request.getSession().getAttribute("EmployeeId") == null) {
            return BaseController.RedirectToMenu();
         }
        if(clientService.UpdateClient(clientDTO.getClientNumber(),clientDTO) != -1 ){
            RedirectView redirectView = new RedirectView("/manage_clients");
            redirectView.setExposePathVariables(false);
            return new ModelAndView(redirectView);
        }
        ModelAndView model = new ModelAndView("ErrorPage");
        model.addObject("Content", Arrays.asList("Error","Ocurrio un error al borrar el cliente", "/admin_main"));
        return model;

    }
}
