package BRG.BRG_ART_ID.domain;

import java.sql.SQLException;
import java.util.List;

public class RuleFactory {

	public void createBusinessRule(String BusinessFunction, String BusinessName, String BusinessTable1, String BusinessColumn1, int BusinessValue1, String BusinessRuleBetween, String BusinessRuleCompare, int BusinessValue2, String BusinessTable2, String BusinessColumn2, String BusinessError, String BusinessTrigger, String BusinessList, String BusinessSQL, String BusinessEvent) throws SQLException{

		if (BusinessFunction.equals("ARR")) {
			ARR arr = new ARR(BusinessName, BusinessTable1, BusinessColumn1, BusinessValue1, BusinessRuleBetween, BusinessValue2, BusinessError);	
		}

		if (BusinessFunction.equals("ACR")) {
			ACR acr = new ACR(BusinessName, BusinessTable1, BusinessColumn1, BusinessRuleCompare, BusinessValue2, BusinessError);
		}


		if (BusinessFunction.equals("TCR")) {
			TCR tcr = new TCR(BusinessName, BusinessTable1, BusinessColumn1, BusinessRuleCompare, BusinessColumn2, BusinessError);
		}

		if (BusinessFunction.equals("IECR")) {
			IECR iecr = new IECR(BusinessName, BusinessTable1, BusinessColumn1, BusinessRuleCompare, BusinessTable2, BusinessColumn2, BusinessError);
		}

		if (BusinessFunction.equals("ALR")) {
			ALR alr = new ALR(BusinessName, BusinessTable1, BusinessColumn1, BusinessList, BusinessError);	
		}

		if (BusinessFunction.equals("AOR")) {
			AOR aor = new AOR(BusinessName, BusinessTable1, BusinessTrigger, BusinessEvent, BusinessSQL);
		}

		if (BusinessFunction.equals("TOR")) {
			TOR tor = new TOR(BusinessName, BusinessTable1, BusinessTrigger, BusinessEvent, BusinessSQL);
		}

		if (BusinessFunction.equals("EOR")) {
			EOR eor = new EOR(BusinessName, BusinessTable1, BusinessTrigger, BusinessEvent, BusinessSQL);
		}
	}
}