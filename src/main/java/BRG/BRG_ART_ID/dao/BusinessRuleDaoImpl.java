package BRG.BRG_ART_ID.dao;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import javax.print.DocFlavor;

import BRG.BRG_ART_ID.domain.BusinessRule;
import BRG.BRG_ART_ID.domain.ACR;
import BRG.BRG_ART_ID.domain.ALR;
import BRG.BRG_ART_ID.domain.AOR;
import BRG.BRG_ART_ID.domain.ARR;

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
	public boolean saveBusinessRuleARR(String BusinessName, String BusinessTable1, String BusinessColumn1, int BusinessValue1, String BusinessRuleBetween, int BusinessValue2, String BusinessError) throws SQLException {
		conn = BaseDao.getConnection();

		String query = "{? = call ARR(?, ?, ?, ?, ?, ?, ?)}";
		CallableStatement statement = conn.prepareCall(query);
		statement.setString(2, BusinessName);
		statement.setString(3, BusinessTable1);
		statement.setString(4, BusinessColumn1);
		statement.setInt(5, BusinessValue1);
		statement.setString(6, BusinessRuleBetween);
		statement.setInt(7, BusinessValue2);
		statement.setString(8, BusinessError);
		statement.registerOutParameter(1,Types.VARCHAR);

		int executeFunction = statement.executeUpdate();
		System.out.println(executeFunction);
		if (executeFunction > 0) {
			System.out.println("Business rule aangemaakt");
		}
		conn.close();		
		return true;
	}
	// create new business rule

		// create new business rule
	public boolean saveBusinessRuleACR(String BusinessName, String BusinessTable1, String BusinessColumn1, String BusinessRuleCompare, int BusinessValue2, String BusinessError) throws SQLException {
		conn = BaseDao.getConnection();

		String query = "{? = call ACR(?, ?, ?, ?, ?, ?)}";
		CallableStatement statement = conn.prepareCall(query);
		statement.setString(2, BusinessName);
		statement.setString(3, BusinessTable1);
		statement.setString(4, BusinessColumn1);
		statement.setString(5, BusinessRuleCompare);
		statement.setInt(6, BusinessValue2);
		statement.setString(7, BusinessError);
		statement.registerOutParameter(1,Types.VARCHAR);

		int executeFunction = statement.executeUpdate();
		System.out.println(executeFunction);
		if (executeFunction > 0) {
			System.out.println("Business rule aangemaakt");
		}
		conn.close();		
		return true;
	}
	// create new business rule
	
	
	public List<String> getAllTables() throws SQLException {
		List<String> tables = new ArrayList<String>();
		conn = BaseDao.getConnection();

		String query = "SELECT table_name  from all_tables where owner = 'CURSIST'";
		Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(query);
		
		while (result.next()) {
			String tableName = result.getString("table_name");

			if (!tableName.equals("B_RULE_TYPE") && !tableName.equals("BUSINESS_RULE") && !tableName.equals("USERS")) {
				tables.add(tableName);
			}
		}

		conn.close();
		result.close();
		return tables;
	}

	public List<String> getAllColumns(String table, String BusinessRuleType) throws SQLException {
		List<String> columns = new ArrayList<String>();
		conn = BaseDao.getConnection();

		PreparedStatement statement = conn.prepareStatement("SELECT COLUMN_NAME, DATA_TYPE FROM ALL_TAB_COLUMNS WHERE TABLE_NAME = ? AND OWNER = 'CURSIST'");
		statement.setString(1, table);  
		ResultSet result = statement.executeQuery();
		
		while (result.next()) {
			String columnName = result.getString("COLUMN_NAME");
			String DataType = result.getString("DATA_TYPE");

			if (BusinessRuleType.equals("ARR") || BusinessRuleType.equals("ACR") || BusinessRuleType.equals("TCR") || BusinessRuleType.equals("IECR")){
				if (DataType.equals("NUMBER")) {
					columns.add(columnName);
				}
			}else{
				columns.add(columnName);
			}
			
			
		}
		
		conn.close();
		result.close();
		return columns;
	}
}