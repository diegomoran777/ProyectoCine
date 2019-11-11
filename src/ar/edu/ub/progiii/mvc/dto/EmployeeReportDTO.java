package ar.edu.ub.progiii.mvc.dto;

public class EmployeeReportDTO {

    private String EmployeeDaySales;
    private String EmployeeDayBookings;
    private String EmployeeDayOnlineBookings;
    private String EmployeeDayWithdraw;

    public EmployeeReportDTO(String employeeDaySales, String employeeDayBookings, String employeeDayOnlineBookings, String employeeDayWithdraw) {
        EmployeeDaySales = employeeDaySales;
        EmployeeDayBookings = employeeDayBookings;
        EmployeeDayOnlineBookings = employeeDayOnlineBookings;
        EmployeeDayWithdraw = employeeDayWithdraw;
    }

    public EmployeeReportDTO() {
    }

    public String getEmployeeDaySales() {
        return ParseResponse(EmployeeDaySales);
    }

    public void setEmployeeDaySales(String employeeDaySales) {
        EmployeeDaySales = employeeDaySales;
    }

    public String getEmployeeDayBookings() {
        return ParseResponse(EmployeeDayBookings);
    }

    public void setEmployeeDayBookings(String employeeDayBookings) {
        EmployeeDayBookings = employeeDayBookings;
    }

    public String getEmployeeDayOnlineBookings() {
        return ParseResponse(EmployeeDayOnlineBookings);
    }

    public void setEmployeeDayOnlineBookings(String employeeDayOnlineBookings) {
        EmployeeDayOnlineBookings = employeeDayOnlineBookings;
    }

    public String getEmployeeDayWithdraw() {
        return ParseResponse(EmployeeDayWithdraw);
    }

    public void setEmployeeDayWithdraw(String employeeDayWithdraw) {
        EmployeeDayWithdraw = employeeDayWithdraw;
    }

    public String ParseResponse(String value){
        if(value.equals("")){
            return "No hay informacion";
        }
        return value;
    }
}
