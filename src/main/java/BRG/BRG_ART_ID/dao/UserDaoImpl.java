package BRG.BRG_ART_ID.dao;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import BRG.BRG_ART_ID.domain.User;

public class UserDaoImpl extends BaseDao implements UserDao{
	private static Connection conn;
	private List<User> Users = new ArrayList<User>();
	
	// haalt alle users
	// wordt niet gebruikt in deze versie
	public List<User> findAll() throws SQLException {
		List<User> Useren = new ArrayList<User>();
		conn = BaseDao.getConnection();

		String query = "SELECT * FROM \"user\"";
		Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(query);
		
		while (result.next()) {
			int ID = result.getInt("id");
			String name = result.getString("name");
			String type = result.getString("type");
			String email = result.getString("email");
			String password = result.getString("password");
			Date created_date = result.getDate("created_date");
			
			User User = new User(ID, name, email, password, type, created_date);
			Users.add(User);
		}
		conn.close();
		result.close();
		return Users;
	}
	
	// haalt user by id
	// wordt gebruikt om de user_ids om te zetten in namen in de json respoonses en views client en cms
	public User findByID(int ID) throws SQLException {
		conn = BaseDao.getConnection();

		PreparedStatement statement = conn.prepareStatement("SELECT * FROM \"user\" WHERE \"id\" = ?");    
		statement.setInt(1, ID);    
		ResultSet result = statement.executeQuery();
		
		// Iets doen met de resultaten
		User user = null;
		
		while (result.next()) {
			ID = result.getInt("id");
			String name = result.getString("name");
			String type = result.getString("type");
			String email = result.getString("email");
			String password = result.getString("password");
			Date created_date = result.getDate("created_date");
			
			user = new User(ID, name, email, password, type, created_date);
		}
		conn.close();
		result.close();
		return user;
	}

	@Override
	public User save(User User) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public User update(User User) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean delete(User User) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void closeConnection() {
		// TODO Auto-generated method stub
		
	}

	
	public User login(String email, String password) throws SQLException {
		conn = BaseDao.getConnection();

		PreparedStatement statement = conn.prepareStatement("SELECT * FROM \"user\" WHERE \"email\" = ? AND \"password\" = ?");    
		statement.setString(1, email);
		statement.setString(2, password);  
		ResultSet result = statement.executeQuery();
		
		// Iets doen met de resultaten
		User user = null;
		
		while (result.next()) {
			int ID = result.getInt("id");
			String name = result.getString("name");
			String type = result.getString("type");
			email = result.getString("email");
		
			user = new User(ID, name, email,  type);
		}
		conn.close();
		result.close();
		return user;
	}
	
}