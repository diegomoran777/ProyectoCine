package ar.edu.ub.progiii.mvc.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.codehaus.groovy.runtime.metaclass.NewMetaMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import ar.edu.ub.progiii.mvc.dto.ClientDTO;
import ar.edu.ub.progiii.mvc.dto.FilmDTO;
import ar.edu.ub.progiii.mvc.mapping.MappingTool;
import ar.edu.ub.progiii.mvc.repository.Connection;
import ar.edu.ub.progiii.mvc.repository.Data;
import ar.edu.ub.progiii.mvc.service.ClientService;

@Controller
public class OnSiteSaleController {
	@Autowired
	ClientService clientService;
	Data data = new Data();
	Connection connection = new Connection();
	MappingTool map = new MappingTool();
		
	/**
	 * Llama a la vista venta presencial, sus peliculas y funciones
	 * @return
	 */
	@GetMapping("/venta_presencial")
	public ModelAndView GetOnSiteSaleView(HttpServletRequest request) {
		if(request.getSession() == null || request.getSession().getAttribute("EmployeeId") == null) {
	           return BaseController.RedirectToMenu();
	        }
		if(clientService.IsEmployeeAlowed((int)request.getSession().getAttribute("EmployeeId"))) {
			ModelAndView model = new ModelAndView("OnSiteSale");
			model.addObject("films",clientService.GetAllFilms());
			model.addObject("shows" ,clientService.GetShowsByHour());
			model.addObject("date",clientService.GetServerDate());
			return model;
		}
		ModelAndView modelError = new ModelAndView("ErrorPage");
 		modelError.addObject("Contenido", Arrays.asList("Error","El usuario no tiene acceso a esta pagina, redireccionando a login!","/"));
		return modelError;
	}
	
	/**
	 * Suma a la fecha de la pagina un dia, de no poderse activa un mensaje de aviso
	 * @param datePage Trae la fecha de el html
	 * @return 
	 */
	@GetMapping("/sumar_fecha")
	public ModelAndView GetAddDate(@RequestParam("datePage") String datePage, HttpServletRequest request) {
		if(request.getSession() == null || request.getSession().getAttribute("EmployeeId") == null) {
	           return BaseController.RedirectToMenu();
	        }
		ModelAndView model = new ModelAndView("OnSiteSale");
		if(clientService.CanDaysBeAdded(datePage)) {
			model.addObject("films",clientService.GetAllFilms());
			model.addObject("shows" ,clientService.GetAllShows());
	        model.addObject("date",clientService.AddDays(datePage, 1));
	        return model;
		}
		if(!clientService.CanDaysBeRemoved(datePage)) {
			model.addObject("films",clientService.GetAllFilms());
			model.addObject("shows" ,clientService.GetShowsByHour());
			model.addObject("date",clientService.GetServerDate());
			model.addObject("Content", Arrays.asList("Aviso","No hay mas fechas disponibles!","1"));
			return model;
		}
		model.addObject("films",clientService.GetAllFilms());
		model.addObject("shows" ,clientService.GetAllShows());
		model.addObject("date",datePage);
		model.addObject("Content", Arrays.asList("Aviso","No hay mas fechas disponibles!","1"));
		return model;
	}
	
	/**
	 * Resta a la fecha de la pagina un dia, de no poderse activa un mensaje de aviso
	 * @param datePage datePage Trae la fecha de el html
	 * @return
	 */
	@GetMapping("/restar_fecha")
	public ModelAndView GetRemoveDate(@RequestParam("datePage") String datePage, HttpServletRequest request) {
		if(request.getSession() == null || request.getSession().getAttribute("EmployeeId") == null) {
	           return BaseController.RedirectToMenu();
	        }
		ModelAndView model = new ModelAndView("OnSiteSale");
		if(clientService.CanDaysBeRemoved(datePage) && !clientService.RedirectToBeginning(datePage)) {
			model.addObject("films",clientService.GetAllFilms());
			model.addObject("shows" ,clientService.GetAllShows());
			model.addObject("date",clientService.RemoveDays(datePage, 1));
			return model;
		}
		if(clientService.RedirectToBeginning(datePage)) {
			model.addObject("films",clientService.GetAllFilms());
			model.addObject("shows" ,clientService.GetShowsByHour());
			model.addObject("date",clientService.RemoveDays(datePage, 1));
			return model;
		}
		model.addObject("films",clientService.GetAllFilms());
		model.addObject("shows" ,clientService.GetShowsByHour());
		model.addObject("date",clientService.GetServerDate());
		model.addObject("Content", Arrays.asList("Aviso","No hay mas fechas disponibles!","1"));
		return model;
	}
	
	/**
	 * Realiza una reserva inicial con los valores de los parametros 
	 * y devuelve la pagna cantidad de enetradas, cargandole los datos
	 * @param showId id de la funcion
	 * @param movieId id de la pelicula
	 * @param dateShow fecha de la funcion
	 * @return
	 */
	@GetMapping("/presencial_cantentradas")
	public ModelAndView GetOnsiteAmountTicketsView(@RequestParam("functionId") String showId, @RequestParam("movieId") String movieId, @RequestParam("dateShow") String dateShow,  HttpServletRequest request) {
		if(request.getSession() == null || request.getSession().getAttribute("EmployeeId") == null) {
	           return BaseController.RedirectToMenu();
	        }
		if (clientService.InsertInitialBooking(movieId, showId, dateShow, (int) request.getSession().getAttribute("EmployeeId"))) {
			ModelAndView model = new ModelAndView("AmountTickets");
			model.addObject("categories", clientService.GetAllRateCategories());
			return model;
		}
		ModelAndView modelError = new ModelAndView("OnSiteSale");
 		modelError.addObject("Contenido", Arrays.asList("Error","Se ha producido un error!","1"));
		return modelError;
		
	}
	
	/**
	 * Realiza una reserva inicial con los valores de los parametros 
	 * y devuelve la pagna cantidad de enetradas, cargandole los datos
	 * @param clientDNI dni del cliente
	 * @return
	 */
	@PostMapping("/buscarCliente_traerInfo")
	public ModelAndView GetClientDataById(@RequestParam("clientDNI") String clientDNI, HttpServletRequest request) {
		if(request.getSession() == null || request.getSession().getAttribute("EmployeeId") == null) {
	           return BaseController.RedirectToMenu();
	        }
		if (clientService.GetClientByDNI(clientDNI) != null) {
			ModelAndView model = new ModelAndView("AmountTickets");
			data.UpdateLastBooking("NroCliente", clientService.GetClientByDNI(clientDNI).getClientNumber(), data.GetLastBookingByEmployeeId((int)request.getSession().getAttribute("EmployeeId")));
			model.addObject("categories", clientService.GetAllRateCategories());
			model.addObject("msj",clientService.GetClientByDNI(clientDNI));
			model.addObject("clientInfo", clientService.GetClientByDNI(clientDNI));
			return model;
		}
		ModelAndView modelError = new ModelAndView("AmountTickets");
 		modelError.addObject("Content", Arrays.asList("Error","No se ha encontrado el cliente!","1"));
 		modelError.addObject("categories", clientService.GetAllRateCategories());
		return modelError;
	}
	
	/**
	 * Registra a un cliente 
	 * @param fullName nombre completo cliente
	 * @param phone telefono cliente
	 * @param email email del cliente
	 * @param adress direccion del cliente
	 * @param birthDate fecha de nacimiento del cliente
	 * @param dni dni del cliente
	 * @return
	 */
	@PostMapping("/registrar_cliente")
	public ModelAndView RegisterClient(@RequestParam("Name") String fullName, @RequestParam("Tel") String phone, @RequestParam("Email") String email, @RequestParam("Adress") String adress, @RequestParam("Date") String birthDate, @RequestParam("DNI") String dni, HttpServletRequest request) {
		if(request.getSession() == null || request.getSession().getAttribute("EmployeeId") == null) {
	           return BaseController.RedirectToMenu();
	        }
		if (clientService.GetClientByDNI(dni) == null) {
			ClientDTO clientRegisteredtDTO = clientService.RegisterClient(fullName, email, birthDate, dni, phone, adress);
			if(clientRegisteredtDTO != null && clientService.IsTheSameDay(clientRegisteredtDTO.getCreationDate())) {
				data.UpdateLastBooking("NroCliente", clientRegisteredtDTO.getClientNumber(), data.GetLastBookingByEmployeeId((int)request.getSession().getAttribute("EmployeeId")));
				ModelAndView model = new ModelAndView("AmountTickets");
				model.addObject("categories", clientService.GetAllRateCategories());
				model.addObject("msj",clientRegisteredtDTO);
				model.addObject("clientInfo", clientRegisteredtDTO); 
				return model;
			}
			ModelAndView modelError = new ModelAndView("AmountTickets");
	 		modelError.addObject("Content", Arrays.asList("Error","Ocurrio un error!","1"));
	 		modelError.addObject("categories", clientService.GetAllRateCategories());
			return modelError;
		}
		ModelAndView modelError = new ModelAndView("AmountTickets");
		modelError.addObject("Content", Arrays.asList("Error","El cliente ya existe1!","1"));
	 	modelError.addObject("categories", clientService.GetAllRateCategories());
		return modelError;
	}
	
	/**
	 * Realiza el registro de entradas y envia el precio total a la proxima vista
	 * @param underAge categoria de entrada
	 * @param retired categoria de entrada 
	 * @param adult categoria de entrada
	 * @param promo categoria de entrada
	 * @param registeredAdult categoria de entrada
	 * @param registeredUnderAge categoria de entrada
	 * @param registeredOlder categoria de entrada
     * @param total monto total de compra
	 * @return
	 */
	@GetMapping("/presencial_pagar")
	public ModelAndView GetPayView(@RequestParam("categories") String categories, @RequestParam("total") String total, HttpServletRequest request) {
		if(request.getSession() == null || request.getSession().getAttribute("EmployeeId") == null) {
	           return BaseController.RedirectToMenu();
	        }
		String[] categoriesSplitted = categories.split(",");
		int employeeId = (int)request.getSession().getAttribute("EmployeeId");
		int amountTickets = 0;
		data.UpdateLastBooking("PrecioTotal", Integer.parseInt(total), data.GetLastBookingByEmployeeId(employeeId));
		
		for (int i = 0; i < categoriesSplitted.length; i++) {
			amountTickets += Integer.parseInt(categoriesSplitted[i]);
			if((i+1) < 5) {
				data.RegisterTickets(employeeId, String.valueOf(i+1), clientService.GetRateById(String.valueOf(i+1)).getValue(), categoriesSplitted[i]);
			}else {
				data.RegisterTickets(employeeId, String.valueOf(i+2), clientService.GetRateById(String.valueOf(i+2)).getValue(), categoriesSplitted[i]);
			}
		}
		data.UpdateLastBooking("CantEntradas", amountTickets, data.GetLastBookingByEmployeeId(employeeId));
		ModelAndView modelPay = new ModelAndView("PaymentMethod");
		modelPay.addObject("total", total);
		return modelPay;
	}
	
	/**
	 * En el caso de volver hacia atras cancela la reserva actual
	 * @param request
	 * @return
	 */
	@GetMapping("/presencial_volver")
	public ModelAndView GetBack(HttpServletRequest request) {
		if(request.getSession() == null || request.getSession().getAttribute("EmployeeId") == null) {
	           return BaseController.RedirectToMenu();
	        }
		int employeeId = (int)request.getSession().getAttribute("EmployeeId");
		data.UpdateLastBooking("CodEstadoReserva", 2, data.GetLastBookingByEmployeeId(employeeId));
		if(clientService.IsEmployeeAlowed(employeeId)) {
			ModelAndView model = new ModelAndView("OnSiteSale");
			model.addObject("films", clientService.GetAllFilms());
			model.addObject("shows", clientService.GetShowsByHour());
			model.addObject("date", clientService.GetServerDate());
			return model;
		}
		ModelAndView modelError = new ModelAndView("ErrorPage");
 		modelError.addObject("Contenido", Arrays.asList("Error", "El usuario no tiene acceso a esta pagina, redireccionando a login!", "/"));
		return modelError;
		
	}
}
