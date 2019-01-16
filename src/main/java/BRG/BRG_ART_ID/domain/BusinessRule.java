package BRG.BRG_ART_ID.domain;

public class BusinessRule {
	private int ID;
	private String name;
	private String code;
	private String errorMessage;
	
	
	public BusinessRule(int ID, String name, String code, String errorMessage) {
		ID = ID;
		this.name = name;
		this.code = code;
		this.errorMessage = errorMessage;
	}
	
	
	public BusinessRule(String name, String code, String errorMessage) {
		this.name = name;
		this.code = code;
		this.errorMessage = errorMessage;
	}
	
	public int getID() {
		return ID;
	}
	
	public void setID(int iD) {
		ID = iD;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}


	public String getErrorMessage() {
		return errorMessage;
	}


	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
}