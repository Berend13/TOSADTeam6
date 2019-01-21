package BRG.BRG_ART_ID.domain;

public class BusinessRule {
	private int ID;
	private String name;
	private String code;
	
	
	public BusinessRule(int ID, String name, String code) {
		ID = ID;
		this.name = name;
		this.code = code;
	}
	
	
	public BusinessRule(String name, String code) {
		this.name = name;
		this.code = code;
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
	
	
}