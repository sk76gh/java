package org.saravana.jaxrs.jaxrs.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorMessage {
	private String errorMessage;
	private int errroCode;
	private String documentation;
	
	
	public ErrorMessage() {
		// TODO Auto-generated constructor stub
	}
	public ErrorMessage(String errorMessagse, int errroCode, String documentation) {
		super();
		this.errorMessage = errorMessagse;
		this.errroCode = errroCode;
		this.documentation = documentation;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public int getErrroCode() {
		return errroCode;
	}
	public void setErrroCode(int errroCode) {
		this.errroCode = errroCode;
	}
	public String getDocumentation() {
		return documentation;
	}
	public void setDocumentation(String documentation) {
		this.documentation = documentation;
	}

}
