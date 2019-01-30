package BRG.BRG_ART_ID.domain;

import java.sql.SQLException;
import java.util.List;

import BRG.BRG_ART_ID.dao.BusinessRuleDaoImpl;

public class ALR extends BusinessRule{
	private BusinessRuleDaoImpl ruleImpl = new BusinessRuleDaoImpl();

	public ALR(String businessName, String businessTable1, String businessColumn1, String businessList, String businessError) throws SQLException {
		super();
		
		ruleImpl.saveBusinessRuleALR(businessName, businessTable1, businessColumn1, businessList, businessError);
	}	
}