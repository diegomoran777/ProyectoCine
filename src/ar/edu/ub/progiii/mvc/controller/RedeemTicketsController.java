package ar.edu.ub.progiii.mvc.controller;

import ar.edu.ub.progiii.mvc.dto.BookingDTO;
import ar.edu.ub.progiii.mvc.dto.FilmDTO;
import ar.edu.ub.progiii.mvc.dto.OnlineBookingDTO;
import ar.edu.ub.progiii.mvc.repository.Data;
import ar.edu.ub.progiii.mvc.service.ClientService;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

@Controller
public class RedeemTicketsController {

    @Autowired
    ClientService clientService;
    Data data = new Data();

    /**
    Metodo que te lleva a la vista para retirar entradas
     */
    @GetMapping("/retiroentradas")
    public ModelAndView GetRedeemTickets(HttpServletRequest request){
    	if(request.getSession() == null || request.getSession().getAttribute("EmployeeId") == null) {
            return BaseController.RedirectToMenu();
         }
        ModelAndView model = new ModelAndView("RedeemTickets");
        return model;
    }
    /**
    Metodo que te lleva a la vista para byscar reservas
     */
    @GetMapping("/buscar_reserva")
    public ModelAndView GetBuscarReserva(@RequestParam("bookingId")String BookingNumber, HttpServletRequest request){
    	if(request.getSession() == null || request.getSession().getAttribute("EmployeeId") == null) {
            return BaseController.RedirectToMenu();
         }
        if(clientService.GetBookingById(BookingNumber) == null){
            ModelAndView modelError = new ModelAndView("RedeemTickets");
            modelError.addObject("Content", Arrays.asList("Error","No se ha encontrado la reserva","1"));
            return modelError;
        }
        ModelAndView modelOk = new ModelAndView("RedeemTickets");
        OnlineBookingDTO booking = new OnlineBookingDTO(clientService.GetBookingById(BookingNumber));
        FilmDTO film = clientService.GetFilmById(Integer.parseInt(booking.getMovieName()));
        modelOk.addObject("Content", null);
        modelOk.addObject("Booking",booking);
        modelOk.addObject("pelicula",film);
        modelOk.addObject("funcion",clientService.GetCinemaShow(booking.getShow()).getStartTime());
        modelOk.addObject("msj","yes");
        return modelOk;

    }
    @GetMapping("/imprimir_reserva")
    public ModelAndView getImprimirReserva(@RequestParam("bookingId")String BookingNumber, HttpServletRequest request){
    	if(request.getSession() == null || request.getSession().getAttribute("EmployeeId") == null) {
            return BaseController.RedirectToMenu();
         }
        clientService.RedeemBooking(BookingNumber);
        ModelAndView model = new ModelAndView("PrintBooking");

        return model;
    }

}
