package ar.edu.ub.progiii.mvc.dto;

import java.time.LocalDate;

/**
 * Clase que define una funcion DTO
 * @author Diego Moran
 *
 */
public class CinemaShowDTO {

	private String CodeShow;
	private String StartTime;
	private String FinishTime;
	private String Comments;
	
	/**
	 * Constructor
	 * @param codeShow representa el codigo de funcion.
	 * @param startTime representa la hora enla que comienza la funcion.
	 * @param finishTime representa la hora en la que termina la funcion.
	 * @param comments representa un comentario sobre que funcion es.
	 */
	public CinemaShowDTO(String codeShow, String startTime, String finishTime, String comments) {
		CodeShow = codeShow;
		StartTime = startTime;
		FinishTime = finishTime;
		Comments = comments;
	}
	
	public CinemaShowDTO() {
		
	}

	public String getCodeShow() {
		return CodeShow;
	}

	public void setCodeShow(String codeShow) {
		CodeShow = codeShow;
	}

	public String getStartTime() {
		return StartTime;
	}

	public void setStartTime(String startTime) {
		StartTime = startTime;
	}

	public String getFinishTime() {
		return FinishTime;
	}

	public void setFinishTime(String finishTime) {
		FinishTime = finishTime;
	}

	public String getComments() {
		return Comments;
	}

	public void setComments(String comments) {
		Comments = comments;
	}
	
	public String toString() {
		return getStartTime() + "hs" + " "+ getComments();
	}
}
