package ar.edu.ub.progiii.mvc.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DashboardController {
    /**
     * Metodo que carga al dashboard
     * @return
     */
    @GetMapping("/admin")
    public ModelAndView GetDashboard(HttpServletRequest request){
    	if(request.getSession() == null || request.getSession().getAttribute("EmployeeId") == null) {
            return BaseController.RedirectToMenu();
         }
        ModelAndView model = new ModelAndView("Dashboard");
        return model;
    }
}