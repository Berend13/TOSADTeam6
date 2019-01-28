package BRG.BRG_ART_ID.domain;

import java.sql.SQLException;
import java.util.List;

import BRG.BRG_ART_ID.dao.BusinessRuleDaoImpl;

public class ARR extends BusinessRule{
	private BusinessRuleDaoImpl ruleImpl = new BusinessRuleDaoImpl();

	public ARR(String businessName, String businessTable1, String businessColumn1, int businessValue1,
		String businessRuleBetween, int businessValue2, String businessError) throws SQLException {
		super();
		
		ruleImpl.saveBusinessRuleARR(businessName, businessTable1, businessColumn1, businessValue1, businessRuleBetween , businessValue2, businessError);
	}


	
}