package ar.edu.ub.progiii.mvc.service;

import ar.edu.ub.progiii.mvc.dto.*;
import ar.edu.ub.progiii.mvc.mapping.MappingTool;
import ar.edu.ub.progiii.mvc.model.Employee;
import ar.edu.ub.progiii.mvc.repository.Data;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.codehaus.groovy.runtime.ConvertedClosure;
import org.springframework.stereotype.Service;
import sun.security.krb5.internal.Ticket;

import java.time.LocalDate;
import java.time.Period;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ClientService {

    public Data dataManager = new Data();
    MappingTool mappingTool = new MappingTool();
    public static EmployeeDTO currentEmployee = new EmployeeDTO();
    public ArrayList<BranchDTO> branchDTOArrayList = new ArrayList<>();
    public static ArrayList<Integer> activeUsers = new ArrayList<>();

    public ClientService(){
        FillAllBranches();
    }

    /**
     * Metodo booleano que checkea si el empleado que este logeado en la sesion de trabajo
     * Esta autorizado o esta baneado del sistema.
     * @param EmployeeNumber
     * @return
     */
    public boolean IsEmployeeAlowed(int EmployeeNumber){
        if(GetEmployeeCategory(EmployeeNumber)==4 || GetEmployeeCategory(EmployeeNumber)==5)return false;
        return true;
    }
    
    /**
     * Verifica que el empleado exista, valida su clave
     * y si su rango es valido 
     * @param EmployeeId
     * @param EmployeePass
     * @return
     */
    public boolean verifyEmployeeLogin(String EmployeeId, String EmployeePass, boolean onOff) {
    	String response = dataManager.GetEmployeeByID(EmployeeId);
    	Employee Employee = mappingTool.MapEmployeeSQL(response);
    	//Retorna true o false si se cumple la condicion dentro del return
    	if((IsEmployeeAlowed(Integer.parseInt(EmployeeId)) && (Employee.getHashedPassword().equals(EmployeePass)))) {
    	    if(!InsertIfNotActive(Integer.parseInt(EmployeeId))) return false;
    		currentEmployee = mappingTool.MapDTOEmployee(Employee);
    		if(onOff) {
    			dataManager.RegistrarLog(EmployeeId,Employee.getRank());
    			return true;
    		}
    		return true;
    	}
    	return false;
    }
    
    /**
     * Verifica que la clave sea correcta , valida su clave, de lo contrario banea al empleado
     * @param employeeNewPass
     * @param employeeId
     * @param employeePass
     * @return boolean
     */
    @SuppressWarnings("static-access")
	public int changePass(String employeeId, String employeePass, String employeeNewPass, HttpServletRequest request) {
    	String response = dataManager.GetEmployeeByID(employeeId);
    	Employee Employee = mappingTool.MapEmployeeSQL(response);
    	//Retorna true o false si se cumple la condicion dentro del return
    	if(Employee.getHashedPassword().equals(employeePass)) {
    		request.getSession().setAttribute("Failed", 0);
    		dataManager.ChangePassEmployee(Employee.getEmployeeNumber(), employeeNewPass);
    		return 1;
    	}
    	else {
    		if((int) request.getSession().getAttribute("Failed") == 2) {
        		dataManager.BanEmployee(Employee.getEmployeeNumber());
        		return 3;
        	}
        	else {
        		request.getSession().setAttribute("Failed",(int)request.getSession().getAttribute("Failed") +1);
        		return 2;
        	}
    	}
    }

    /**
     * Busqueda de cliente por id de usuario
     * @param UID
     * @return
     */
    public ClientDTO GetClientByUID(String UID){
            String response = dataManager.GetClientByUId(UID);
            if(response==null){
                //No se encontro el usuario
                System.out.println("No se encontro el usuario");
                return null;
            }
            ClientDTO clientDTO = mappingTool.MapDTOClientSQL(response);
            return clientDTO;
    }

    /**
     * Busqueda de todas las peliculas
     */
    public ArrayList<FilmDTO> GetAllFilms(){
        String response = dataManager.GetAllFilms();
        String [] aux = response.split("/");
        ArrayList<FilmDTO> filmList = new ArrayList<>();
        for (String item:aux) {
            filmList.add(mappingTool.MapDTOFilmSQL(item));
        }
        return filmList;
    }

    /**
     * Busqueda de reserva por id
     * @param id
     */
    public BookingDTO GetBookingById(String id){
        BookingDTO bookingDTO = new BookingDTO();
        String response = dataManager.GetBookingById(id);
        if(response==null){
            //No se encontro el usuario
            System.out.println("No se encontro la reserva");
            return null;
        }
        String [] aux = response.split("/");
        bookingDTO = mappingTool.MapSQLBookingDTO(aux[0]);
        return bookingDTO;
    }

    /**
     * Agreagar un nuevo cliente dado de alta en la pagina a la base de datos
     * @param clientDTO
     * @return string dataManager
     */
    public boolean CreateNewClient(ClientDTO clientDTO){
        return dataManager.PostNewClient(clientDTO);
    }

    /**
     * Get list of all clients
     * @return arrayList de ClientDTO
     */
    public ArrayList<ClientDTO> GetAllClients(){
        String response = dataManager.GetAllClients();
        ArrayList<ClientDTO> clientList = new ArrayList<>();
        String [] aux = response.split("/");
        for (String item:aux) {
            clientList.add(mappingTool.MapDTOClientSQL(item));
        }
        return clientList;
    }

    /**
     * Obtiene la categoria de un empleado
     * @return int categoria
     */
    public int GetEmployeeCategory(int EmployeeNumber){
        String response = dataManager.CheckEmployeeCategory(EmployeeNumber);
        return Integer.parseInt(response);
    }

    /**
     * Trae todos los empleados que esten activos
     * @return arrayList EmployeeDTO
     */
    public ArrayList<EmployeeDTO> GetAllEmployees(){
        String response = dataManager.GetAllEmployees();
        ArrayList<EmployeeDTO> list = new ArrayList<>();
        String [] aux = response.split("/");
        for (String item : aux) {
            list.add(mappingTool.MapDTOEmployeeSQL(item));
        }
        return list;
    }

    /**
     * Banea a un empleado por id
     * @return int result
     */
    public int BanEmployee(int EmployeeNumber){
        int result = dataManager.BanEmployee(EmployeeNumber);
        return result;
    }

    /**
     * Borra a un empleado por id
     * @return int result
     */
    public int DeleteEmployee(int EmployeeNumber){
        int result = dataManager.DeleteEmployee(EmployeeNumber);
        return result;
    }

    /**
     * Traea un empleado por id
     * @return EmployeeDTO
     */
    public EmployeeDTO GetEmployee(int EmployeeNumber){
        ArrayList<EmployeeDTO> list = GetAllEmployees();
        for (EmployeeDTO item : list) {
            if(item.getEmployeeNumber() == EmployeeNumber){
            	return item;
            }
        }
        return null;
    }

    /**
     * Actualiza un empeado
     */
    public void UpdateEmployee(EmployeeDTO employee) {
        ArrayList<EmployeeDTO> list = GetAllEmployees();
        for (EmployeeDTO emp:list) {
            if(emp.equals(employee)){
                emp.setAddress(employee.getAddress());
                emp.setEmail(employee.getEmail());
                emp.setPhoneNumber(employee.getPhoneNumber());
                dataManager.UpdateProfile(emp);
            }
        }
    }

    /**
     * Traea todos los tickets
     * @return arrayLIst list
     */
    public ArrayList<TicketDTO> GetAllTickets(){
        ArrayList<TicketDTO> list = new ArrayList<>();
        String [] response = dataManager.GetAllTickets().split("/");
        for (String item: response) {
            list.add(mappingTool.MapDTOTicketSQL(item));
        }
        return list;
    }

    /**
     * Traea todoslos tickets activos
     * @return arrayList list
     */
    public ArrayList<TicketDTO> GetActiveTickets(){
        ArrayList<TicketDTO> list = new ArrayList<>();
        String [] response = dataManager.GetActiveTickets().split("/");
        for (String item: response) {
            list.add(mappingTool.MapDTOTicketSQL(item));
        }
        return list;
    }

    /**
     * Verificar si hay un usuario cargado como current
     * @return boolean
     */
    public boolean IsActiveUser(){
        if(currentEmployee.getEmployeeNumber()<0 || currentEmployee.getPhoneNumber() == null || currentEmployee.getFullName() == null
        || currentEmployee.getFullName().equals("") || currentEmployee.getEmail().isEmpty() || currentEmployee.getEmail() == null){
            return false;
        }
        return true;
    }

    /**
     * agregar el ticket a la base
     * @param ticketDTO
     * @return int result
     */
    public int CreateTicket(TicketDTO ticketDTO){
        int result = dataManager.AddTicket(ticketDTO);
        return result;
    }

    /**
     * Banear cliente en base de datos
     * @param ClientNumber
     * @return
     */
    public int BanClient(int ClientNumber){
        int result = dataManager.BanClient(ClientNumber);
        return result;
    }

    /**
     * Borrado logico del cliente
     * @param ClientNumber
     * @return
     */
    public int DeleteClient(int ClientNumber){
        int result = dataManager.DeleteClient(ClientNumber);
        return result;
    }

    /**
     * Retorna las ventas del dia de un empleado
     * @param EmployeeNumber
     * @return
     */
    public String EmployeeDailySales(int EmployeeNumber){
        return dataManager.GetEmployeeDailySales(EmployeeNumber);
    }

    /**
     * Retorna las ventas totales del dia
     * @return
     */
    public String DailySales(){
        return dataManager.GetGeneralDailySales();
    }

    /**
     * Retorna los empelados activos
     * @return
     */
    public String ActiveEmployees(){
        return dataManager.GetEployeesActive();
    }

    /**
     * Retorna la cantidad de tickets online
     * @return
     */
    public String AmountOnlineTickets(){
        return dataManager.GetOnlineBooQuantity();
    }

    /**
     * Retorna la pelicula mas vista
     * @return
     */
    public FilmDTO DayFilmMostWatched(){
        FilmDTO filmDTO = mappingTool.MapDTOFilmSQL(dataManager.GetDayMostViewed());
        return filmDTO;
    }

    /**
     * Retorna la pelicula mas vista del mes
     * @return
     */
    public FilmDTO MonthFilmMostWatched(){
        FilmDTO filmDTO = mappingTool.MapDTOFilmSQL(dataManager.GetMonthMostViewed());
        return filmDTO;
    }

    /**
     * Retorna la categoria del dia
     * @return
     */
    public String[] CategoryDay(){
        return dataManager.GetCategoryDay().split("_");
    }

    /**
     * Retorna los supervisores activos del dia
     * @return
     */
    public String SupervisorsActiveDay(){
        return dataManager.GetSupervisorsActive();
    }

    /**
     * Cierra un ticket si el empleado esta autorizado
     * @return
     */
    public boolean CloseTicket(int TicketNumber,int EmployeeNumber){
        int CurrentEmployeeCategory=0;
        try {
            CurrentEmployeeCategory = GetEmployeeCategory(EmployeeNumber);
        }catch(Exception e){
            System.out.println("Empleado no autorizado");
        }
        if(CurrentEmployeeCategory ==1){
            dataManager.CloseTicket(TicketNumber,EmployeeNumber);
            return true;
        }
        return false;
    }
    
    /**
     * Retorna la fecha del dia actual
     * @return
     */
    public String GetDateToday(){
        return dataManager.GetServerDate();
    }
    
    /**
     * Retorna la actual actual
     * @return
     */
    public String GetHourToday(){
        return dataManager.GetHourNow();
    }
    
    /**
     * Busqueda de todas las funciones
     * @return array
     */
    public ArrayList<CinemaShowDTO> GetAllShows(){
        String response = dataManager.GetAllShows();
        String [] aux = response.split("/");
        ArrayList<CinemaShowDTO> showList = new ArrayList<>();
        for (String item:aux) {
            showList.add(mappingTool.MapDTOShowsSQL(item));
        }
        return showList;
    }
    
    /**
     * Busqueda de las funciones por hora de comienzo
     * @return array
     */
    public ArrayList<CinemaShowDTO> GetShowsByHour(){
    	ArrayList<CinemaShowDTO> showsList = new ArrayList<>();
    	for (CinemaShowDTO show:GetAllShows()) {
    		if(Integer.parseInt(show.getStartTime()) >= Integer.parseInt(GetHourToday())) {
    			showsList.add(show);
    		}
    	}
    	return showsList;
    }
    
    /**
     * Agrega dias a una fecha
     * @param date
     * @param days
     * @return LocalDate
     */
    public LocalDate AddDays(String date, int days){
       return LocalDate.parse(date).plusDays(days); 
    }
    
    /**
     * Saca dias a una fecha
     * @param date
     * @param days
     * @return LocalDate
     */
    public LocalDate RemoveDays(String date, int days){
       return LocalDate.parse(date).minusDays(days); 
    }
    
    /**
     * Devuelve true si puede seguir agregado dias dado que la fecha por parametro
     * no es igual a la fecha actual pasado 5 dias
     * @param date
     * @return boolean
     */
    public boolean CanDaysBeAdded(String date){
    	return LocalDate.parse(date).getDayOfWeek().toString().equalsIgnoreCase("WEDNESDAY")?false:true;
    }
    
    /**
     * Devuelve true si puede seguir sacando dias dado que la fecha por 
     * parametro es igual a la fecha actual
     * @param date
     * @return boolean
     */
    public boolean CanDaysBeRemoved(String date){
        return Period.between(LocalDate.parse(date), LocalDate.parse(GetDateToday())).getDays() == 0?false:true;
    }
    
    /**
     * Si la fecha por parametro menos un dia es igual a la actual
     * actual devuelve true
     * @param date
     * @return boolean
     */
    public boolean RedirectToBeginning(String date){
        return Period.between(RemoveDays(date, 1), LocalDate.parse(GetDateToday())).getDays() == 0;
    }

    /**
     * Trae las ventas del mes
     * @return 
     */
    public String GetMonthlySales(){
        return dataManager.GetGeneralMonthlySales();
    }

    /**
     * Trae los empleados activos del mes
     * @return 
     */
    public String GetEmployeesActiveMonth(){
        return dataManager.GetEployeesActiveMonth();
    }

    /**
     * Retorna la cantidad de reservas online del mes
     * @return 
     */
    public String GetOnlineBookingsMonth(){
        if(dataManager.GetOnlineBooQuantityMonth()!=null || dataManager.GetOnlineBooQuantityMonth()!="")return dataManager.GetOnlineBooQuantityMonth();
        return "0";
    }

    /**
     * La categoria del mes
     * @return 
     */
    public String[] CategoryMonth(){
        return dataManager.GetCategoryMonth().split("_");
    }

    /**
     * Trae los supervisores que estuvieron en linea en el mes
     * @return 
     */
    public String GetSupervisorsOnlineMonth(){
        return dataManager.GetSupervisorsActiveMonth();
    }
    
    /**
     * Trae la fecha del dia desde el servidor
     * @return 
     */
    public String GetServerDate(){
        return dataManager.GetServerDate();
    }

    /**
     * Trae el mes actual desde el servidor
     * @return 
     */
    public String GetServerMonth() throws ParseException {
        String currentDate = dataManager.GetServerDate();
        Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(currentDate);
        DateFormat out = new SimpleDateFormat("MMMMM yyyy");
        return out.format(date1);
    }

    /**
     * Trae la lista de todas las sucursales.
     * @return 
     */
    public void FillAllBranches(){
        String [] sqlResponse = dataManager.GetAllBranches().split("/");
        for (String item:sqlResponse) {
            branchDTOArrayList.add(mappingTool.MapDTOBranchSQL(item));
        }
    }

    /**
     * Trae el reporte del empleado
     * @return 
     */
    public EmployeeReportDTO GetEmployeeReport(String EmployeeNumber){
        EmployeeReportDTO report = new EmployeeReportDTO();
        report.setEmployeeDaySales(dataManager.EmployeeDaySales(EmployeeNumber));
        report.setEmployeeDayBookings(dataManager.EmployeeDayBookings(EmployeeNumber));
        report.setEmployeeDayOnlineBookings(dataManager.EmployeeDayOnlineBookings(EmployeeNumber));
        report.setEmployeeDayWithdraw(dataManager.EmployeeDayWithdraw(EmployeeNumber));
        return report;
    }

    /**
     * Actualiza el status de logueo de un empleado
     * @return 
     */
    public void UpdateLoginStatus(int employeeId){
        if(employeeId!=-1){
            dataManager.UpdateLoginStatus(String.valueOf(employeeId));
            activeUsers.remove(activeUsers.indexOf(employeeId));
        }
    }

    /**
     * Actualiza un cliente
     * @param clientDTO
     * @param ClientId
     * @return 
     */
    public int UpdateClient(int ClientId,ClientDTO clientDTO){
        ArrayList<ClientDTO> list = GetAllClients();
        int result =0;
        for (ClientDTO client:list) {
            if(client.getClientNumber() == ClientId){
                client.setAddress(clientDTO.getAddress());
                client.setPhoneNumber(clientDTO.getPhoneNumber());
                client.setEmail(clientDTO.getEmail());
                dataManager.UpdateClient(ClientId, client);
            }
        }
        return result;
    }

    /**
     * Encuentra un cliente, realiza una nueva instancia
     * @param clientDTO
     * @return 
     */
    public ClientDTO FindClient(ClientDTO clientDTO){
        ClientDTO clientDTOFinal = new ClientDTO();
        return clientDTOFinal;
    }
    
    /**
     * Retorna un booleano sea si realizo la reserva o no
     * @param movieId
     * @param showId
     * @param dateShow
     * @return 
     */
    public boolean InsertInitialBooking(String movieId, String showId, String dateShow, int employeeId){
    	String [] aux = dateShow.split("-");
    	String date = aux[0]+aux[1]+aux[2];
    	int theatreNumber = (int) Math.floor(Math.random()*(12-1+1)+1);
    	return dataManager.InsertInitialBooking(movieId, showId, theatreNumber, String.valueOf(employeeId), date) == 1?true:false;
    }
    
    /**
     * Busqueda de todas las categorias de tarifa
     * menos la categoria 5 que es online
     * @return
     */
    public ArrayList<RateCategoryDTO> GetAllRateCategories(){
        String response = dataManager.GetAllRateCategories();
        String [] aux = response.split("/");
        ArrayList<RateCategoryDTO> rateList = new ArrayList<>();
        for (String item:aux) {
        	if(!mappingTool.MapDTORateCategoriesSQL(item).getRateCode().equals("5")) {
        		rateList.add(mappingTool.MapDTORateCategoriesSQL(item));
        	}
        }
        return rateList;
    }
    
    /**
     * Busqueda de cliente por dni 
     * @param DNI
     * @return 
     */
    public ClientDTO GetClientByDNI(String DNI){
            String response = dataManager.GetClientByDNI(DNI);
            if(response==null){
                //No se encontro el usuario
                System.out.println("No se encontro el usuario");
                return null;
            }
            ClientDTO clientDTO = mappingTool.MapDTOClientSQL(response);
            return clientDTO;
    }
    
    /**
     * Registro de cliente, devuelve a la vez el cliente 
     * @param fullName 
     * @param email
     * @param birthDate
     * @param documentNumber
     * @param phoneNumber
     * @param adress
     * @return 
     */
    public ClientDTO RegisterClient(String fullName, String email, String birthDate, String documentNumber, String phoneNumber, String adress){
            String response = dataManager.RegisterClient(fullName, email, birthDate, documentNumber, phoneNumber, adress);
            if(response==null){
                //No se encontro el usuario
                System.out.println("No se encontro el usuario");
                return null;
            }
            ClientDTO clientDTO = mappingTool.MapDTOClientSQL(response);
            return clientDTO;
    }
    
    /**
     * Devuelve true o false si la cantidad de dias entre uno y otro es 0
     * @param date
     * @return 
     */
    public boolean IsTheSameDay(String date){
        return Period.between(LocalDate.parse(date), LocalDate.parse(GetDateToday())).getDays() == 0;
    }
    
    /**
     * Trae una reserva por id
     * @param id 
     * @return 
     */
    public RateCategoryDTO GetRateById(String id) {
        for (RateCategoryDTO rate : GetAllRateCategories()) {
            if (rate.getRateCode().equals(id)) {
                return rate;
            }
        }
        return null;
    }

    /**
     * Método que trae una película
     * @param idFilm
     * @return
     * Trae una pelicula por id
     * @param idFilm id de pelicula 
     * @return 
     */
    public FilmDTO GetFilmById(int idFilm){
        for(FilmDTO Film: GetAllFilms()){
            if(Film.getCode() == idFilm){
                return Film;
            }
        }
        return null;
    }

    /**
     * Método que trae una función
     * @param idCinemaShow
     * @return

     * Trae una funcion por id
     * @param idCinemaShow id de funcion 
     * @return 

     */
    public CinemaShowDTO GetCinemaShow(String idCinemaShow){
        for(CinemaShowDTO CinemaShow: GetAllShows()){
            if(CinemaShow.getCodeShow().equals(idCinemaShow)){
                return CinemaShow;
            }
        }
        return null;
    }

    /**
     * Recive un numero de empleado, y lo agrega a la lista de usuarios activos, si no esta activo ya.
     * @param EmployeeId
     * @return
     */
    public Boolean InsertIfNotActive(int EmployeeId){
        if( activeUsers.indexOf(EmployeeId)== -1){
            activeUsers.add(EmployeeId);
            return true;
        }
        return false;
    }
    
    /**
     * Trae todas las reservas de un cliente desde la fecha actual en adelante
     * @param idClient
     * @return 
     */
    public ArrayList<BookingDTO> GetAllBookingsByIdClient(String idClient){
        String response = dataManager.GetAllBookingsByClientId(idClient);
        if(response != null && response != "") {
        	ArrayList<BookingDTO> bookingList = new ArrayList<>();
            String [] aux = response.split("/");
            for (String item:aux) {
                bookingList.add(mappingTool.MapSQLBookingDTO(item));
            }
            return bookingList;
        }
        return null;
    }

    /**
     *Recibe una reserva y le cambia el estado a activa
     * @param bookingNumber
     */
    public void RedeemBooking(String bookingNumber) {
        dataManager.RedeemBooking(bookingNumber);
    }

    /**
     * A partir de un empleado, devuelve su total virtual de ventas
     * @param employeeId
     */
    public String GetTotalVirtual(int employeeId){
        return dataManager.GetEmployeeTotalVirtual(employeeId);
    }

    /**
     * Verifica que el empleado exista, valida su clave
     * y si su rango es valido
     * @param EmployeeId
     * @param EmployeePass
     * @return
     */
    public boolean ValidateCredentials(String EmployeeId, String EmployeePass) {
        String response = dataManager.GetEmployeeByID(EmployeeId);
        Employee Employee = mappingTool.MapEmployeeSQL(response);
        //Retorna true o false si se cumple la condicion dentro del return
        if((IsEmployeeAlowed(Integer.parseInt(EmployeeId)) && (Employee.getHashedPassword().equals(EmployeePass)))) {
            currentEmployee = mappingTool.MapDTOEmployee(Employee);
            return true;
        }
        return false;
    }

    /**
     * Recive el año del cual se quiere saber los valores de la tabla analytics
     * @param Year
     * @return
     */
    public ArrayList<SaleInformationDTO> YearSalesInformation(String Year) {
        String [] resultSQL = dataManager.YearSalesInformation(Year).split("/");
        ArrayList<SaleInformationDTO> yearSales = new ArrayList<>();
        for (String info : resultSQL) {
            yearSales.add(mappingTool.MapDTOSalesInformationSQL(info));
        }
        return yearSales;
    }

    public List<Integer> YearUserMovements(String Year){
        if (dataManager.YearUserMovements(Year) != null){
            String [] aux = dataManager.YearUserMovements(Year).split("/");
            return Arrays.asList(Integer.parseInt(aux[0]),Integer.parseInt(aux[1]),Integer.parseInt(aux[2]));
        }
        return null;
    }

}
