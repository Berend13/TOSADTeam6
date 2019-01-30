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
			String type = result.getString("TYPE");
			
			BusinessRule rule = new BusinessRule(ID, name, code, type);
			rules.add(rule);
		}
		conn.close();
		result.close();
		return rules;
	}
	// get all business rules

	// verwijderd business rule
	public boolean delete(String BusinessName) throws SQLException {
		conn = BaseDao.getConnection();
		Statement stmt = null;

		String deleteTrigger = "DELETE FROM BUSINESS_RULE WHERE NAME = ?";
		PreparedStatement statementDelete = conn.prepareStatement(deleteTrigger);
		statementDelete.setString(1, BusinessName);
		
		
        
		// conn dropTrigger = "DROP TRIGGER " + BusinessName; 
		// PreparedStatement statementDrop = conn.prepareStatement(dropTrigger);
		// statementDrop.setString(1, BusinessName.toUpperCase());
		// System.out.println(BusinessName.toUpperCase());

		int rowsDeleted = statementDelete.executeUpdate();
		if (rowsDeleted > 0) {
		    stmt = conn.createStatement();
            stmt.execute("DROP TRIGGER " + BusinessName);
			System.out.println("Business Rule verwijderd");
			conn.close();
			return true;
		}else {
			conn.close();
			return false;
		}
		
		
	}

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
		if (executeFunction > 0) {
			System.out.println("Business rule aangemaakt");
			conn.close();		
			return true;
		}else {
			conn.close();		
			return false;
		}
		
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
		if (executeFunction > 0) {
			System.out.println("Business rule aangemaakt");
			conn.close();		
			return true;
		}else {
			conn.close();		
			return false;
		}
		
	}
	// create new business rule

	// create new business rule
	public boolean saveBusinessRuleTCR(String businessName, String businessTable1, String businessColumn1, 
		String businessRuleCompare, String businessColumn2 , String businessError) throws SQLException {
		conn = BaseDao.getConnection();

		String query = "{? = call TCR(?, ?, ?, ?, ?, ?)}";
		CallableStatement statement = conn.prepareCall(query);
		statement.setString(2, businessName);
		statement.setString(3, businessTable1);
		statement.setString(4, businessColumn1);
		statement.setString(5, businessRuleCompare);
		statement.setString(6, businessColumn2);
		statement.setString(7, businessError);
		statement.registerOutParameter(1,Types.VARCHAR);

		int executeFunction = statement.executeUpdate();
		if (executeFunction > 0) {
			System.out.println("Business rule aangemaakt");
			conn.close();		
			return true;
		}else {
			conn.close();		
			return false;
		}
		
	}
	// create new business rule

	// create new business rule
	public boolean saveBusinessRuleIECR(String BusinessName, String BusinessTable1, String BusinessColumn1, String BusinessRuleCompare, String BusinessTable2, String BusinessColumn2, String BusinessError) throws SQLException {
		conn = BaseDao.getConnection();

		String query = "{? = call IER(?, ?, ?, ?, ?, ?, ?)}";
		CallableStatement statement = conn.prepareCall(query);
		statement.setString(2, BusinessName);
		statement.setString(3, BusinessTable1);
		statement.setString(4, BusinessColumn1);
		statement.setString(5, BusinessRuleCompare);
		statement.setString(6, BusinessTable2);
		statement.setString(7, BusinessColumn2);
		statement.setString(8, BusinessError);
		statement.registerOutParameter(1,Types.VARCHAR);

		int executeFunction = statement.executeUpdate();
		if (executeFunction > 0) {
			System.out.println("Business rule aangemaakt");
			conn.close();		
			return true;
		}else {
			conn.close();		
			return false;
		}
	}
	// create new business rule

		// create new business rule
	public boolean saveBusinessRuleALR(String BusinessName, String BusinessTable1, String BusinessColumn1, String BusinessList, String BusinessError) throws SQLException {
		conn = BaseDao.getConnection();
		

		String query = "{? = call ALR(?, ?, ?, ?, ?)}";
		CallableStatement statement = conn.prepareCall(query);
		statement.setString(2, BusinessName);
		statement.setString(3, BusinessTable1);
		statement.setString(4, BusinessColumn1);
		statement.setString(5, BusinessList);
		statement.setString(6, BusinessError);
		statement.registerOutParameter(1,Types.VARCHAR);

		boolean executeFunction = statement.execute();
		if (executeFunction == true) {
			System.out.println("Business rule aangemaakt");
			conn.close();		
			return true;
		}else {
			conn.close();		
			return false;
		}
	}

	// create new business rule
	public boolean saveBusinessRuleAOR(String businessName, String businessTable1, String businessTrigger, String businessEvent, String businessSQL) throws SQLException {
		conn = BaseDao.getConnection();

		String query = "{? = call OTR(?, ?, ?, ?, ?)}";
		CallableStatement statement = conn.prepareCall(query);
		statement.setString(2, businessName);
		statement.setString(3, businessTable1);
		statement.setString(4, businessTrigger);
		statement.setString(5, businessEvent);
		statement.setString(6, businessSQL);
		statement.registerOutParameter(1,Types.VARCHAR);

		int executeFunction = statement.executeUpdate();
		if (executeFunction > 0) {
			System.out.println("Business rule aangemaakt");
			conn.close();		
			return true;
		}else {
			conn.close();		
			return false;
		}
	}

	// create new business rule
	public boolean saveBusinessRuleTOR(String businessName, String businessTable1, String businessTrigger, String businessEvent, String businessSQL) throws SQLException {
		conn = BaseDao.getConnection();

		String query = "{? = call OTR(?, ?, ?, ?, ?)}";
		CallableStatement statement = conn.prepareCall(query);
		statement.setString(2, businessName);
		statement.setString(3, businessTable1);
		statement.setString(4, businessTrigger);
		statement.setString(5, businessEvent);
		statement.setString(6, businessSQL);
		statement.registerOutParameter(1,Types.VARCHAR);

		int executeFunction = statement.executeUpdate();
		if (executeFunction > 0) {
			System.out.println("Business rule aangemaakt");
			conn.close();		
			return true;
		}else {
			conn.close();		
			return false;
		}
	}


	// create new business rule
	public boolean saveBusinessRuleEOR(String businessName, String businessTable1, String businessTrigger, String businessEvent, String businessSQL) throws SQLException {
		conn = BaseDao.getConnection();

		String query = "{? = call OTR(?, ?, ?, ?, ?)}";
		CallableStatement statement = conn.prepareCall(query);
		statement.setString(2, businessName);
		statement.setString(3, businessTable1);
		statement.setString(4, businessTrigger);
		statement.setString(5, businessEvent);
		statement.setString(6, businessSQL);
		statement.registerOutParameter(1,Types.VARCHAR);

		int executeFunction = statement.executeUpdate();
		if (executeFunction > 0) {
			System.out.println("Business rule aangemaakt");
			conn.close();		
			return true;
		}else {
			conn.close();		
			return false;
		}
	}

	
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