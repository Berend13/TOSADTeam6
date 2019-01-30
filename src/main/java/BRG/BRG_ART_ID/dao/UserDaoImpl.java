package BRG.BRG_ART_ID.dao;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import BRG.BRG_ART_ID.domain.User;

public class UserDaoImpl extends BaseDao implements UserDao{
	private static Connection conn;
	
	public User login(String email, String password) throws SQLException {
		conn = BaseDao.getConnection();

		PreparedStatement statement = conn.prepareStatement("SELECT * FROM users WHERE email = ? AND password = ?");    
		statement.setString(1, email);
		statement.setString(2, password);  
		ResultSet result = statement.executeQuery();
		
		// Iets doen met de resultaten
		User user = null;
		
		while (result.next()) {
			int ID = result.getInt("id");
			String name = result.getString("name");
			email = result.getString("email");
		
			user = new User(ID, name, email);
		}
		conn.close();
		result.close();
		return user;
	}
	
}