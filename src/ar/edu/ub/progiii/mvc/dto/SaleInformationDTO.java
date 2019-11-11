package ar.edu.ub.progiii.mvc.dto;

public class SaleInformationDTO {

    private String Month;
    private String Year;
    private double AverageRate;
    private double ExpectedRate;
    private long TicketsSold;
    private double OnlineSales;
    private double OnsiteSales;
    private String OnlinePercentage;
    private String ExpectedOnlinePercentage;
    private String OnsitePercetnage;
    private String ExpectedOnsitePercetnage;
    private int SeasonCode;
    private int BranchNumber;
    private String CalculatedDate;
    private int MonthNumber;
    private double RateSuggested;

    /**
     * Constructor para informacion de ventas de analytics
     * @param month
     * @param year
     * @param averageRate
     * @param expectedRate
     * @param ticketsSold
     * @param onlineSales
     * @param onsiteSales
     * @param onlinePercentage
     * @param expectedOnlinePercentage
     * @param onsitePercetnage
     * @param expectedOnsitePercetnage
     * @param seasonCode
     * @param branchNumber
     * @param calculatedDate
     * @param monthNumber
     * @param rateSuggested
     */
    public SaleInformationDTO(String month, String year, double averageRate, double expectedRate, long ticketsSold,
                              double onlineSales, double onsiteSales, String onlinePercentage,
                              String expectedOnlinePercentage, String onsitePercetnage, String expectedOnsitePercetnage,
                              int seasonCode, int branchNumber, String calculatedDate, int monthNumber, double rateSuggested) {
        Month = month;
        Year = year;
        AverageRate = averageRate;
        ExpectedRate = expectedRate;
        TicketsSold = ticketsSold;
        OnlineSales = onlineSales;
        OnsiteSales = onsiteSales;
        OnlinePercentage = onlinePercentage;
        ExpectedOnlinePercentage = expectedOnlinePercentage;
        OnsitePercetnage = onsitePercetnage;
        ExpectedOnsitePercetnage = expectedOnsitePercetnage;
        SeasonCode = seasonCode;
        BranchNumber = branchNumber;
        CalculatedDate = calculatedDate;
        MonthNumber = monthNumber;
        RateSuggested  = rateSuggested;
    }

    /**
     * Constructor default para la informacion de ventas de analytics
     */
    public SaleInformationDTO() {
    }

    public String getMonth() {
        return Month;
    }

    public void setMonth(String month) {
        Month = month;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public double getAverageRate() {
        return AverageRate;
    }

    public void setAverageRate(double averageRate) {
        AverageRate = averageRate;
    }

    public double getExpectedRate() {
        return ExpectedRate;
    }

    public void setExpectedRate(double expectedRate) {
        ExpectedRate = expectedRate;
    }

    public long getTicketsSold() {
        return TicketsSold;
    }

    public void setTicketsSold(long ticketsSold) {
        TicketsSold = ticketsSold;
    }

    public double getOnlineSales() {
        return OnlineSales;
    }

    public void setOnlineSales(double onlineSales) {
        OnlineSales = onlineSales;
    }

    public double getOnsiteSales() {
        return OnsiteSales;
    }

    public void setOnsiteSales(double onsiteSales) {
        OnsiteSales = onsiteSales;
    }

    public String getOnlinePercentage() {
        return OnlinePercentage;
    }

    public void setOnlinePercentage(String onlinePercentage) {
        OnlinePercentage = onlinePercentage;
    }

    public String getExpectedOnlinePercentage() {
        return ExpectedOnlinePercentage;
    }

    public void setExpectedOnlinePercentage(String expectedOnlinePercentage) {
        ExpectedOnlinePercentage = expectedOnlinePercentage;
    }

    public String getOnsitePercetnage() {
        return OnsitePercetnage;
    }

    public void setOnsitePercetnage(String onsitePercetnage) {
        OnsitePercetnage = onsitePercetnage;
    }

    public String getExpectedOnsitePercetnage() {
        return ExpectedOnsitePercetnage;
    }

    public void setExpectedOnsitePercetnage(String expectedOnsitePercetnage) {
        ExpectedOnsitePercetnage = expectedOnsitePercetnage;
    }

    public int getSeasonCode() {
        return SeasonCode;
    }

    public void setSeasonCode(int seasonCode) {
        SeasonCode = seasonCode;
    }

    public int getBranchNumber() {
        return BranchNumber;
    }

    public void setBranchNumber(int branchNumber) {
        BranchNumber = branchNumber;
    }

    public String getCalculatedDate() {
        return CalculatedDate;
    }

    public void setCalculatedDate(String calculatedDate) {
        CalculatedDate = calculatedDate;
    }

    public int getMonthNumber() {
        return MonthNumber;
    }

    public void setMonthNumber(int monthNumber) {
        MonthNumber = monthNumber;
    }

	public double getRateSuggested() {
		return RateSuggested;
	}

	public void setRateSuggested(double rateSuggested) {
		RateSuggested = rateSuggested;
	}
    
    
}

