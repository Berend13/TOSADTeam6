package BRG.BRG_ART_ID.domain;

import java.sql.SQLException;

import BRG.BRG_ART_ID.dao.BusinessRuleDaoImpl;

public class EOR extends RuleFactory{
	private BusinessRuleDaoImpl ruleImpl = new BusinessRuleDaoImpl();


	public EOR(String businessName, String businessTable1, String businessTrigger, String businessEvent, String businessSQL) throws SQLException {
		super();
		
		ruleImpl.saveBusinessRuleEOR(businessName, businessTable1, businessTrigger, businessEvent, businessSQL);
	}
}