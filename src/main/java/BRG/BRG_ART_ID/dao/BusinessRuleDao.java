package BRG.BRG_ART_ID.dao;

import java.sql.SQLException;
import java.util.*;

import BRG.BRG_ART_ID.domain.BusinessRule;

public interface BusinessRuleDao {

    public List<BusinessRule> findAll() throws SQLException;
    public boolean saveBusinessRule(String BusinessFunction, String BusinessName, String BusinessTable1, String BusinessColumn1, int BusinessValue1, String BusinessRuleBetween, String BusinessRuleCompare, int BusinessValue2, String BusinessTable2, String BusinessColumn2, String BusinessError) throws SQLException;
    public boolean update(BusinessRule rule) throws SQLException;
    public boolean delete(BusinessRule rule) throws SQLException;
    public List getAllTables() throws SQLException;
    public  List getAllColumns(String table, String BusinessRuleType) throws SQLException;
}