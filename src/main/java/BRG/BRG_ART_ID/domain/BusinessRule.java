package BRG.BRG_ART_ID.domain;

public class BusinessRule {
	private int ID;
	private String name;
	private String code;
	private String type;
	
	public BusinessRule() {
		
	}
	
	
	public BusinessRule(int ID, String name, String code, String type) {
		this.ID = ID;
		this.name = name;
		this.code = code;
		this.type = type;
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
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
}