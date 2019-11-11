package ar.edu.ub.progiii.mvc.controller;

import ar.edu.ub.progiii.mvc.service.ClientService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.util.Arrays;

@Controller
public class ReportController{

    @Autowired
    ClientService clientService;
    /**
    Metodo que te lleva a la vista de reportes de ventas del dia
     */
    @GetMapping("/reporte_dia")
    public ModelAndView GetReport(HttpServletRequest request){
        if(request.getSession() == null || request.getSession().getAttribute("EmployeeId") == null) {
           return BaseController.RedirectToMenu();
        }
        ModelAndView model = new ModelAndView("ReportTemplate");
        if(clientService.ActiveEmployees().equals("0") || clientService.ActiveEmployees()==null
                || clientService.ActiveEmployees().equals("")){
            ModelAndView errorModel = new ModelAndView("ErrorPage");
            errorModel.addObject("Contenido", Arrays.asList("Error","No hay actividad registrada para el este dia,por" +
                    " favor dirijase cree un ticket si considera que el resultado obtenido es erroneo.", "/admin_main"));
            return errorModel;
        }
        model.addObject("employeeAmount",clientService.ActiveEmployees());
        model.addObject("employeeRevDaily","AR$ "+clientService.DailySales());
        model.addObject("onlineTickets",clientService.AmountOnlineTickets());
        model.addObject("mostViewed",clientService.DayFilmMostWatched());
        model.addObject("categoryDay",clientService.CategoryDay()[0]+" AR$"+clientService.CategoryDay()[1]);
        model.addObject("supervisorsActive",clientService.SupervisorsActiveDay());
        model.addObject("serverDate",clientService.GetServerDate());
        return model;
    }

    @GetMapping("/reporte_mes")
    public ModelAndView GetMonthlyReport(HttpServletRequest request) throws ParseException {
        if(request.getSession() == null || request.getSession().getAttribute("EmployeeId") == null) {
            return BaseController.RedirectToMenu();
        }
        ModelAndView model = new ModelAndView("ReportTemplate");
        if(clientService.ActiveEmployees().equals("0") || clientService.ActiveEmployees()==null
                || clientService.ActiveEmployees().equals("")){
            ModelAndView errorModel = new ModelAndView("ErrorPage");
            errorModel.addObject("Contenido", Arrays.asList("Error","No hay actividad registrada para el este dia,por" +
                    " favor dirijase cree un ticket si considera que el resultado obtenido es erroneo.", "/admin_main"));
            return errorModel;
        }
        model.addObject("employeeAmount",clientService.GetEmployeesActiveMonth());
        model.addObject("employeeRevDaily","AR$ "+clientService.GetMonthlySales());
        model.addObject("onlineTickets",clientService.GetOnlineBookingsMonth());
        model.addObject("mostViewed",clientService.MonthFilmMostWatched());
        model.addObject("categoryDay",clientService.CategoryMonth()[0]+" AR$"+clientService.CategoryMonth()[1]);
        model.addObject("supervisorsActive",clientService.GetSupervisorsOnlineMonth());
        model.addObject("serverDate",clientService.GetServerMonth());
        return model;
    }

    @GetMapping("/get_report")
    public ModelAndView GetEmpReport(@RequestParam("employeeid")int EmployeeId,  HttpServletRequest request){
        if(request.getSession() == null || request.getSession().getAttribute("EmployeeId") == null) {
            return BaseController.RedirectToMenu();
        }
        clientService.dataManager.LogData("INFO","Reporte de empleado pedido "+EmployeeId);
        ModelAndView model = new ModelAndView("EmployeeReport");
        model.addObject("authorName",clientService.GetEmployee((int)request.getSession()
                .getAttribute("EmployeeId")).getFullName());
        model.addObject("reportDate",clientService.GetServerDate());
        model.addObject("employee",clientService.GetEmployee(EmployeeId));
        model.addObject("branch",clientService.branchDTOArrayList.get(0));
        model.addObject("employeeReport",clientService.GetEmployeeReport(String.valueOf(EmployeeId)));
        return model;
    }

}
