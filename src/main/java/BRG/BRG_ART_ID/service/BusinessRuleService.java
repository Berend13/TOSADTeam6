package BRG.BRG_ART_ID.service;

import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.*;

import BRG.BRG_ART_ID.dao.BusinessRuleDaoImpl;

@Path("/businessrule")
public class BusinessRuleService {

	@POST
	@Path("/new")
	@Produces("application/json")
	public Response newBusinessRule(@FormParam("BusinessFunction") String BusinessFunction, @FormParam("BusinessName") String BusinessName, @FormParam("BusinessTable") String BusinessTable,
		@FormParam("BusinessColumn") String BusinessColumn, @FormParam("BusinessValue1") String BusinessValue1, @FormParam("BusinessRule") String BusinessRule, @FormParam("BusinessValue2") String BusinessValue2, @FormParam("BusinessError") String BusinessError) throws SQLException {

		BusinessRuleDaoImpl BusinessRuleServiceInst = new BusinessRuleDaoImpl();
		Boolean result = null;
		

		result = BusinessRuleServiceInst.saveBusinessRule(BusinessFunction, BusinessName, BusinessTable, BusinessColumn, BusinessValue1, BusinessRule, BusinessValue2, BusinessError);

		if (result == true) {
			return Response.ok().build();
		}else {
			return null;
		}
	}
}