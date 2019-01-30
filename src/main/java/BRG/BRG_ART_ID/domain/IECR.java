package BRG.BRG_ART_ID.domain;

import java.sql.SQLException;

import BRG.BRG_ART_ID.dao.BusinessRuleDaoImpl;

public class IECR extends BusinessRule {
	private BusinessRuleDaoImpl ruleImpl = new BusinessRuleDaoImpl();
	
	public IECR(String businessName, String businessTable1, String businessColumn1, 
		String businessRuleCompare, String businessTable2, String businessColumn2 , String businessError) throws SQLException {
		super();
		
		ruleImpl.saveBusinessRuleIECR(businessName, businessTable1, businessColumn1, businessRuleCompare, businessTable2, businessColumn2 , businessError);

		
	}
}
