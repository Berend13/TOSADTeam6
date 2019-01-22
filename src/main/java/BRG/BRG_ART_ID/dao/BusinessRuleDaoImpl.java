package BRG.BRG_ART_ID.dao;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import BRG.BRG_ART_ID.domain.BusinessRule;

import javax.print.DocFlavor;

public class BusinessRuleDaoImpl extends BaseDao implements BusinessRuleDao{
	private static Connection conn;
	private List<BusinessRule> rules = new ArrayList<BusinessRule>();
	
	// get all business rules
	public List<BusinessRule> findAll() throws SQLException {
		List<BusinessRule> rules = new ArrayList<BusinessRule>();
		conn = BaseDao.getConnection();

		String query = "SELECT * FROM BUSINESS_RULE ORDER BY id DESC";
		Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(query);
		
		while (result.next()) {
			int ID = result.getInt("ID");
			String name = result.getString("NAME");
			String code = result.getString("CODE");
			
			BusinessRule rule = new BusinessRule(ID, name, code);
			rules.add(rule);
		}
		conn.close();
		result.close();
		return rules;
	}
	// get all business rules

	// create new business rule
	public boolean saveBusinessRule(String BusinessFunction, String BusinessName, String BusinessTable, String BusinessColumn, int BusinessValue1, String BusinessRule, int BusinessValue2, String BusinessError) throws SQLException {
		conn = BaseDao.getConnection();

		String query = "{? = call ARR(?, ?, ?, ?, ?, ?, ?)}";
		CallableStatement statement = conn.prepareCall(query);
		statement.setString(2, BusinessName);
		statement.setString(3, BusinessTable);
		statement.setString(4, BusinessColumn);
		statement.setInt(5, BusinessValue1);
		statement.setString(6, BusinessRule);
		statement.setInt(7, BusinessValue2);
		statement.setString(8, BusinessError);
		statement.registerOutParameter(1,Types.VARCHAR);


		System.out.println(statement.toString());
		int executeFunction = statement.executeUpdate();
		System.out.println(executeFunction);
		if (executeFunction > 0) {
			System.out.println("Business rule aangemaakt");
		}
		conn.close();		
		return true;
	}
	// create new business rule
	
	public boolean update(BusinessRule rule) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean delete(BusinessRule rule) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}	
}