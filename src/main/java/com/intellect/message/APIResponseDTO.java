package com.intellect.message;

import java.util.ArrayList;
import java.util.List;

public class APIResponseDTO {

	private String resMsg;
	private String userId;
	private List<ValidationErrorDTO> valErrors;
	
	public String getResMsg() {
		return resMsg;
	}
	public void setResMsg(String resMsg) {
		this.resMsg = resMsg;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<ValidationErrorDTO> getValErrors() {
		return valErrors;
	}
	public void addValErrors(ValidationErrorDTO valError) {
		if(this.valErrors == null) {
			this.valErrors = new ArrayList<>();
		}
		this.valErrors.add(valError);
	}
}
