package ar.edu.ub.progiii.mvc.mapping;
import ar.edu.ub.progiii.mvc.dto.*;
import ar.edu.ub.progiii.mvc.model.*;

import java.sql.Date;

public class MappingTool implements IMapping{

    /**
     * Mapeo de cliente a dto
     *
     * @param cliente cliente que ingresa
     * @return dto de cliente
     */
    @Override
    public ClientDTO MapDTOClient(Client cliente) {
        return null;
    }

    /**
     * Mapeo de cleinte dto a cliente
     *
     * @param clientDTO cliente dto que ingresa
     * @return retorno de cliente mapeado
     */
    @Override
    public Client MapClientDTO(ClientDTO clientDTO) {
        return null;
    }

    /**
     * Mape de emplado de modle a empleado dto
     *
     * @param employee empleado de model entrante
     * @return employee de dto
     */
    @Override
    public EmployeeDTO MapDTOEmployee(Employee employee) {
        EmployeeDTO aux = new EmployeeDTO();
        aux.setAddress(employee.getAddress());
        aux.setDateOfBirth(employee.getDateOfBirth());
        aux.setEmail(employee.getEmail());
        aux.setFullName(employee.getFullName());
        aux.setPhoneNumber(employee.getPhoneNumber());
        aux.setEmployeeNumber(employee.getEmployeeNumber());
        return aux;
    }

    /**
     * Mapeo de empleado dto a empleado de model
     *
     * @param eployeeDTO emplado dto entrante
     * @return empleado de model
     */
    @Override
    public Employee MapEmployeeDTO(EmployeeDTO eployeeDTO) {
        return null;
    }
    
    /**
     * Mapea booking a booking dto
     * @param SQLData data sql
     * @return BookingDTO
     */
    public BookingDTO MapSQLBookingDTO(String SQLData) {
    	String [] aux = SQLData.split("_");
        BookingDTO bookingDTO = new BookingDTO();
        //si ocurre un error en el mapeo vuelve el cliente null
        try {
        	String [] splitDate = aux[3].split(" ");
        	bookingDTO.setBookingCode(aux[0]);
        	bookingDTO.setBookingDate((splitDate[0]));
        	bookingDTO.setMovieName(aux[1]);
        	bookingDTO.setShow(aux[2]);
        	bookingDTO.setTheatreNumber(Integer.parseInt(aux[4]));
        	bookingDTO.setTicketAmount(Integer.parseInt(aux[5]));
        	bookingDTO.setClientNumber(Integer.parseInt(aux[6]));
        	bookingDTO.setBookingStatus(Integer.parseInt(aux[7]));
        	bookingDTO.setChannelCode(Integer.parseInt(aux[8]));
        	bookingDTO.setTotalValue(Double.parseDouble(aux[10]));
        }catch (Exception ex){
            bookingDTO=null;
            System.out.println("Ocurrio un error en el mapeo");
        }
        return bookingDTO;
    }

    /**
     * Mapea booking a booking dto
     *
     * @param booking reserva entrante
     * @return reserva dto saliente
     */
    @Override
    public OnlineBookingDTO MapDTOBooking(OnlineBooking booking) {
        return null;
    }

    /**
     * Mapea booking dto a booking de model.
     *
     * @param bookingDTO booknig dto entrante
     * @return booking de model saliente.
     */
    @Override
    public OnSiteBooking MapBookingDTO(OnSiteBookingDTO bookingDTO) {
        return null;
    }

    /**
     * Mapea booking a booking dto
     *
     * @param booking reserva entrante
     * @return reserva dto saliente
     */
    @Override
    public OnSiteBookingDTO MapDTOBooking(OnSiteBooking booking) {
        return null;
    }

    /**
     * Mapea booking dto a booking de model.
     *
     * @param bookingDTO booknig dto entrante
     * @return booking de model saliente.
     */
    @Override
    public OnlineBooking MapBookingDTO(OnlineBookingDTO bookingDTO) {
        return null;
    }

    /**
     * Mapeo de respuesta sql a dto
     *
     * @param SQLData data proveniente del sql
     * @return modelo de dto
     */
    @Override
    public OnlineBookingDTO MapOBookingDTOSql(String SQLData) {
        return null;
    }

    /**
     * Mapea la rta del sql a un cliente dto
     *
     * @param SQLData
     * @return
     */
    @Override
    public ClientDTO MapDTOClientSQL(String SQLData) {
        String [] aux = SQLData.split("_");
        ClientDTO clientDTO;
        //si ocurre un error en el mapeo vuelve el cliente null
        try {
            String [] splitDate = aux[5].split(" ");
            String [] splitCreationDate = aux[7].split(" ");
            clientDTO = new ClientDTO();
            clientDTO.setFullName(aux[0]);
            clientDTO.setClientNumber(Integer.parseInt(aux[1]));
            clientDTO.setPhoneNumber(aux[2]);
            clientDTO.setEmail(aux[3]);
            clientDTO.setAddress(aux[4]);
            clientDTO.setDateOfBirth(splitDate[0]);
            clientDTO.setDocumentNumber(aux[6]);
            clientDTO.setCreationDate(splitCreationDate[0]);
        }catch (Exception ex){
            clientDTO=null;
            System.out.println("Ocurrio un error en el mapeo");
        }
        return clientDTO;
    }

    /**
     * Mapeo de string de sql a film dto
     *
     * @param SQLData
     * @return
     */
    @Override
    public FilmDTO MapDTOFilmSQL(String SQLData) {
    	String [] aux = SQLData.split("_");
    	FilmDTO filmDTO;
    	//si ocurre un error en el mapeo vuelve el cliente null
    	try {
            filmDTO = new FilmDTO();
            filmDTO.setCode(Integer.parseInt(aux[0]));
            filmDTO.setFilmName(aux[1]);
            filmDTO.setDuration(aux[2]);
            filmDTO.setSummary(aux[3]);
        }catch (Exception ex){
            filmDTO=null;
            System.out.println("Ocurrio un error en el mapeo");
        }
    	return filmDTO;
    }

    /**
     * Mapea string de sql a employee DTO
     *
     * @param SQLData
     * @return Employee DTO
     */
    @Override
    public EmployeeDTO MapDTOEmployeeSQL(String SQLData) {
        String [] aux = SQLData.split("_");
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setFullName(aux[0]);
        employeeDTO.setPhoneNumber(aux[1]);
        employeeDTO.setEmail(aux[2]);
        employeeDTO.setAddress(aux[3]);
        String [] auxDOB = aux[4].split(" ");
        employeeDTO.setDateOfBirth(auxDOB[0]);
        employeeDTO.setEmployeeNumber(Integer.parseInt(aux[5]));
        employeeDTO.setRank(aux[6]);
        return employeeDTO;
    }

    /**
     * Mapea data proveniente del sql como ticket dto
     *
     * @param SQLData
     * @return
     */
    @Override
    public TicketDTO MapDTOTicketSQL(String SQLData) {
        String [] aux = SQLData.split("_");
        TicketDTO ticketDTO = new TicketDTO();
        ticketDTO.setTicketID(aux[0]);
        ticketDTO.setTicketTitle(aux[1]);
        ticketDTO.setTicketAuthor(aux[2]);
        ticketDTO.setTicketContent(aux[3]);
        ticketDTO.setTicketDate(aux[4]);
        ticketDTO.setTicketStatus(aux[5]);
        return ticketDTO;
    }

    /**
     * Convierte el input string que viene de sql a un dto de branch
     *
     * @param SQLData
     * @return
     */
    @Override
    public BranchDTO MapDTOBranchSQL(String SQLData) {
        String [] aux = SQLData.split("_");
        BranchDTO branchDTO = new BranchDTO();
        branchDTO.setBranchNumber(Integer.parseInt(aux[0]));
        branchDTO.setBranchName(aux[1]);
        branchDTO.setBranchAddress(aux[2]);
        branchDTO.setBranchPhonenumber(aux[3]);
        return branchDTO;
    }

    /**
     * Mapea data proveniente del sql como Employee
     *
     * @param SQLData
     * @return Employee
     */
  @Override
	public Employee MapEmployeeSQL(String SQLData) {
		String [] aux = SQLData.replaceAll("/","").split("_");
        Employee Employee;
        //si ocurre un error en el mapeo vuelve el cliente null
        try {
            String [] splitDate = aux[4].split(" ");
            Employee = new Employee();
            Employee.setFullName(aux[0]);
            Employee.setPhoneNumber(aux[1]);
            Employee.setEmail(aux[2]);
            Employee.setAddress(aux[3]);
            Employee.setDateOfBirth(splitDate[0]);
            Employee.setEmployeeNumber(Integer.parseInt(aux[5]));
            Employee.setHashedPassword(aux[6]);
            Employee.setRank(aux[7]);
        }catch (Exception ex){
            Employee=null;
            System.out.println("Ocurrio un error en el mapeo");
        }
        return Employee;
	}
  
  /**
   * Mapeo de string de sql a film dto
   * @param SQLData
   * @return
   */
  @Override
  public CinemaShowDTO MapDTOShowsSQL(String SQLData) {
  	String [] aux = SQLData.split("_");
  	CinemaShowDTO cinemaShowDTO;
  	//si ocurre un error en el mapeo vuelve el cliente null
  	try {
  		cinemaShowDTO = new CinemaShowDTO();
  		cinemaShowDTO.setCodeShow(aux[0]);
  		cinemaShowDTO.setComments(aux[3]);
  		cinemaShowDTO.setFinishTime(aux[2]);
  		cinemaShowDTO.setStartTime(aux[1]);
      }catch (Exception ex){
    	  cinemaShowDTO=null;
          System.out.println("Ocurrio un error en el mapeo");
      }
  	return cinemaShowDTO;
  }
  
  /**
   * Mapeo de string de sql a categoria de tarifa dto
   * @param SQLData
   * @return
   */
  @Override
  public RateCategoryDTO MapDTORateCategoriesSQL(String SQLData) {
  	String [] aux = SQLData.split("_");
  	RateCategoryDTO rateDTO;
  	//si ocurre un error en el mapeo vuelve el cliente null
  	try {
  		rateDTO = new RateCategoryDTO();
  		rateDTO.setRateCode(aux[0]);
  		rateDTO.setRateName(aux[1]);
  		rateDTO.setValue(aux[2]);
      }catch (Exception ex){
    	  rateDTO=null;
          System.out.println("Ocurrio un error en el mapeo");
      }
  	return rateDTO;
  }

    /**
     * Mapea un string proveniente de sql a objeto dto manipulable
     *
     * @param SQLData
     * @return
     */
    @Override
    public SaleInformationDTO MapDTOSalesInformationSQL(String SQLData) {

        SaleInformationDTO saleInformationDTO = new SaleInformationDTO();
        String [] unFormattedInfo = SQLData.split("_");
        try {
            saleInformationDTO.setMonth(unFormattedInfo[0]);
            saleInformationDTO.setYear(unFormattedInfo[1]);
            saleInformationDTO.setAverageRate(Double.parseDouble(unFormattedInfo[2]));
            saleInformationDTO.setExpectedRate(Double.parseDouble(unFormattedInfo[3]));
            saleInformationDTO.setTicketsSold(Long.parseLong(unFormattedInfo[4]));
            saleInformationDTO.setOnlineSales(Double.parseDouble(unFormattedInfo[5]));
            saleInformationDTO.setOnsiteSales(Double.parseDouble(unFormattedInfo[6]));
            saleInformationDTO.setOnlinePercentage(unFormattedInfo[7]);
            saleInformationDTO.setExpectedOnlinePercentage(unFormattedInfo[8]);
            saleInformationDTO.setOnsitePercetnage(unFormattedInfo[9]);
            saleInformationDTO.setExpectedOnsitePercetnage(unFormattedInfo[10]);
            saleInformationDTO.setSeasonCode(Integer.parseInt(unFormattedInfo[11]));
            saleInformationDTO.setBranchNumber(Integer.parseInt(unFormattedInfo[12]));
            saleInformationDTO.setCalculatedDate(unFormattedInfo[13]);
            saleInformationDTO.setMonthNumber(Integer.parseInt(unFormattedInfo[14]));
            saleInformationDTO.setRateSuggested(Double.parseDouble(unFormattedInfo[15]));
        }catch(Exception ex){
            saleInformationDTO = null;
        }
        return saleInformationDTO;
    }

}
