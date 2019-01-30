package BRG.BRG_ART_ID.domain;
import java.sql.Date;

public class User {
	private int id;
	private String name;
	private String email;
	private String password;
	private String type;
	private Date created_date;
	
	public User(int id, String name, String email, String type) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.type = type;
	}

	public User(int id, String name, String email, String password, String type, Date created_date) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.type = type;
		this.created_date = created_date;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}

	
}