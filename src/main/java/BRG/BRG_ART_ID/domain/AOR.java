package BRG.BRG_ART_ID.domain;

import java.sql.SQLException;
import java.util.List;

import BRG.BRG_ART_ID.dao.BusinessRuleDaoImpl;

public class AOR extends RuleFactory{
	private BusinessRuleDaoImpl ruleImpl = new BusinessRuleDaoImpl();


	public AOR(String businessName, String businessTable1, String businessTrigger, String businessEvent, String businessSQL) throws SQLException {
		super();
		
		ruleImpl.saveBusinessRuleAOR(businessName, businessTable1, businessTrigger, businessEvent, businessSQL);
	}
}