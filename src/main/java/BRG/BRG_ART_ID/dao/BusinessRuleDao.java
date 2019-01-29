package BRG.BRG_ART_ID.dao;

import java.sql.SQLException;
import java.util.*;

import BRG.BRG_ART_ID.domain.BusinessRule;

public interface BusinessRuleDao {

    public List<BusinessRule> findAll() throws SQLException;

    public boolean saveBusinessRuleARR(String BusinessName, String BusinessTable1, String BusinessColumn1, int BusinessValue1, String BusinessRuleBetween, int BusinessValue2, String BusinessError) throws SQLException;

    public boolean saveBusinessRuleACR(String BusinessName, String BusinessTable1, String BusinessColumn1, String BusinessRuleCompare, int BusinessValue2, String BusinessError) throws SQLException;

    public boolean saveBusinessRuleAOR(String BusinessName, String BusinessTable1, String BusinessTrigger, String BusinessSQL, 
		String BusinessError) throws SQLException;

    public boolean saveBusinessRuleTCR(String BusinessName, String BusinessTable1, String BusinessColumn1, 
		String BusinessRuleCompare, String BusinessColumn2 , String BusinessError) throws SQLException;

    public List getAllTables() throws SQLException;
    public  List getAllColumns(String table, String BusinessRuleType) throws SQLException;
}