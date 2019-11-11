package ar.edu.ub.progiii.mvc.dto;

public class RateCategoryDTO {
	
	private String rateCode;
	private String rateName;
	private  String value;

	public RateCategoryDTO(String rateCode, String rateName, String value) {
		this.rateCode = rateCode;
		this.rateName = rateName;
		this.value = value;
	}

	public RateCategoryDTO() {
		
	}

	public String getRateCode() {
		return rateCode;
	}

	public void setRateCode(String rateCode) {
		this.rateCode = rateCode;
	}

	public String getRateName() {
		return rateName;
	}

	public void setRateName(String rateName) {
		this.rateName = rateName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
