package BRG.BRG_ART_ID.dao;

import java.sql.SQLException;
import java.util.*;

import BRG.BRG_ART_ID.domain.BusinessRule;

public interface BusinessRuleDao {

    public List<BusinessRule> findAll() throws SQLException;
    public boolean saveBusinessRule(String BusinessFunction, String BusinessName, String BusinessTable, String BusinessColumn, int BusinessValue1, String BusinessRule, int BusinessValue2, String BusinessError) throws SQLException;
    public boolean save(BusinessRule rule) throws SQLException;
    public boolean update(BusinessRule rule) throws SQLException;
    public boolean delete(BusinessRule rule) throws SQLException;
}