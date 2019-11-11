package ar.edu.ub.progiii.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

@Controller
public class ErrorController {
    /**
    Metodo que te lleva a la pagina de error
     */
    @GetMapping("/errorpage")
    public ModelAndView GetError(String s){
         ModelAndView model = new ModelAndView("ErrorPage");
         model.addObject("Contenido", Arrays.asList("Error","Este es un mensaje de error"));
         return model;
     }
}
