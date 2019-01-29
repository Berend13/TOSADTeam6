package BRG.BRG_ART_ID.domain;

import java.sql.SQLException;

import BRG.BRG_ART_ID.dao.BusinessRuleDaoImpl;

public class TCR extends BusinessRule {
	private BusinessRuleDaoImpl ruleImpl = new BusinessRuleDaoImpl();
	
	public TCR(String businessName, String businessTable1, String businessColumn1, 
			String BusinessRuleCompare, String BusinessColumn2 , String businessError) throws SQLException {
			super();
			
			ruleImpl.saveBusinessRuleTCR(businessName, businessTable1, businessColumn1, BusinessRuleCompare, BusinessColumn2 , businessError);

			
}
}
