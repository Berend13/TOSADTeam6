package BRG.BRG_ART_ID.domain;

import java.sql.SQLException;
import java.util.List;

public class RuleFactory {

	public void createBusinessRule(String BusinessFunction, String BusinessName, String BusinessTable1, String BusinessColumn1, int BusinessValue1, String BusinessRuleBetween, String BusinessRuleCompare, int BusinessValue2, String BusinessTable2, String BusinessColumn2, String BusinessError) throws SQLException{

		if (BusinessFunction.equals("ARR")) {
			ARR arr = new ARR(BusinessName, BusinessTable1, BusinessColumn1, BusinessValue1, BusinessRuleBetween, BusinessValue2, BusinessError);
			
		}

	}
}