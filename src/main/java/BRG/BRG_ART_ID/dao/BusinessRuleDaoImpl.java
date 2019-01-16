package BRG.BRG_ART_ID.dao;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import BRG.BRG_ART_ID.domain.BusinessRule;

import javax.print.DocFlavor;

public class BusinessRuleDaoImpl extends BaseDao implements BusinessRuleDao{
	private static Connection conn;
	private List<BusinessRule> rules = new ArrayList<BusinessRule>();
	
	public List<BusinessRule> findAll() throws SQLException {
		List<BusinessRule> BusinessRuleen = new ArrayList<BusinessRule>();
		conn = BaseDao.getConnection();

		String query = "SELECT * FROM BusinessRule";
		Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(query);
		
		while (result.next()) {
			int ID = result.getInt("id");
			String name = result.getString("name");
			String code = result.getString("code");
			String errorMessage = result.getString("errorMessage");
			
			BusinessRule rule = new BusinessRule(ID, name, code, errorMessage);
			rules.add(rule);
		}
		conn.close();
		result.close();
		return rules;
	}

	public boolean saveBusinessRule(String BusinessFunction, String BusinessName, String BusinessTable, String BusinessColumn, int BusinessValue1, String BusinessRule, int BusinessValue2, String BusinessError) throws SQLException {
		conn = BaseDao.getConnection();
		System.out.println(BusinessFunction);
		System.out.println(BusinessName);
		System.out.println(BusinessTable);
		System.out.println(BusinessColumn);
		System.out.println(BusinessValue1);
		System.out.println(BusinessRule);
		System.out.println(BusinessValue2);
		System.out.println(BusinessError);

		String query = "begin execute immediate ?(?, ?, ?, ?, ?, ?, ?); end;";
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, BusinessFunction);
		statement.setString(2, BusinessName);
		statement.setString(3, BusinessTable);
		statement.setString(4, BusinessColumn);
		statement.setInt(5, BusinessValue1);
		statement.setString(6, BusinessRule);
		statement.setInt(7, BusinessValue2);
		statement.setString(8, BusinessError);

		System.out.println(statement.toString());

		boolean executeFunction = statement.execute();
		if (executeFunction == true) {
			System.out.println("Business rule aangemaakt");
		}
		conn.close();		
		return true;
	}

	public boolean save(BusinessRule rule) throws SQLException {
//		conn = BaseDao.getConnection();
//		String query = "begin execute immediate ?(?,?,?,?,?,?,?,?) end;";
//
//		PreparedStatement statement = conn.prepareStatement(query);
//		statement.setString(1, rule.getName());
//		statement.setString(2, rule.getCode());
//		statement.setString(3, rule.getErrorMessage());
//
//		int rowsInserted = statement.executeUpdate();
//		if (rowsInserted > 0) {
//			System.out.println("Artikel toegevoegd");
//		}
//		conn.close();
		return true;
	}

	
	public boolean update(BusinessRule rule) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean delete(BusinessRule rule) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}	
}