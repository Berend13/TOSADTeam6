package BRG.BRG_ART_ID.domain;

import java.sql.SQLException;
import java.util.List;

import BRG.BRG_ART_ID.dao.BusinessRuleDaoImpl;

public class ACR extends BusinessRule{
	private BusinessRuleDaoImpl ruleImpl = new BusinessRuleDaoImpl();


	public ACR(String businessName, String businessTable1, String businessColumn1, String businessRuleCompare, int businessValue2, String businessError) throws SQLException {
		super();
		
		ruleImpl.saveBusinessRuleACR(businessName, businessTable1, businessColumn1, businessRuleCompare, businessValue2, businessError);
	}	
}