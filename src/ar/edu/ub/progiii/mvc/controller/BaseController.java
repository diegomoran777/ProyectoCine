package ar.edu.ub.progiii.mvc.controller;

import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

public class BaseController {

    public static ModelAndView RedirectToMenu(){
        ModelAndView errorModel = new ModelAndView("ErrorPage");
        errorModel.addObject("Contenido", Arrays.asList("Error","No hay ningun usuario logeado.", "/"));
        return errorModel;
    }

}
