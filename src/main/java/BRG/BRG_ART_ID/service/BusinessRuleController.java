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
import BRG.BRG_ART_ID.domain.BusinessRule;

@Path("/businessrule")
public class BusinessRuleController {

	// Nieuwe business rule plaatsen
	@POST
	@Path("/new")
	@Produces("application/json")
	public Response saveBusinessRule(@FormParam("BusinessFunction") String BusinessFunction, @FormParam("BusinessName") String BusinessName, @FormParam("BusinessTable1") String BusinessTable1,
		@FormParam("BusinessColumn1") String BusinessColumn1, @FormParam("BusinessValue1") int BusinessValue1, @FormParam("BusinessRuleCompare") String BusinessRuleCompare, @FormParam("BusinessRuleBetween") String BusinessRuleBetween, @FormParam("BusinessValue2") int BusinessValue2, @FormParam("BusinessTable2") String BusinessTable2,
		@FormParam("BusinessColumn2") String BusinessColumn2, @FormParam("BusinessError") String BusinessError) throws SQLException {

		BusinessRuleDaoImpl BusinessRuleServiceInst = new BusinessRuleDaoImpl();
		Boolean result = null;
		

		result = BusinessRuleServiceInst.saveBusinessRule(BusinessFunction, BusinessName, BusinessTable1, BusinessColumn1, BusinessValue1, BusinessRuleBetween, BusinessRuleCompare, BusinessValue2, BusinessTable2, BusinessColumn2, BusinessError);

		if (result == true) {
			return Response.ok().build();
		}else {
			return null;
		}
	}
	// create new business rule

	// get all business rules
	@GET
	@Path("/all")
	@Produces("application/json")
	public String getAllBusinessRules() throws SQLException {
		BusinessRuleDaoImpl BusinessRuleServiceInst = new BusinessRuleDaoImpl();

		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (BusinessRule rule : BusinessRuleServiceInst.findAll()){
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("ID", rule.getID());
			job.add("name", rule.getName());
			job.add("code", rule.getCode());

			jab.add(job);
		}

		JsonArray array = jab.build();
		return array.toString();
	}
	// get all business rules

	// get all tables
	@GET
	@Path("/tables")
	@Produces("application/json")
	public String getAllTables() throws SQLException {
		BusinessRuleDaoImpl BusinessRuleServiceInst = new BusinessRuleDaoImpl();

		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (String table : BusinessRuleServiceInst.getAllTables()){
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("tableName", table);

			jab.add(job);
		}

		JsonArray array = jab.build();
		return array.toString();
	}
	// get all tables

	// get all tables
	@GET
	@Path("/columns/{tableName}/{businessRuleType}")
	@Produces("application/json")
	public String getAllColumns(@PathParam("tableName") String tableName, @PathParam("businessRuleType") String businessRuleType) throws SQLException {
		BusinessRuleDaoImpl BusinessRuleServiceInst = new BusinessRuleDaoImpl();

		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (String column : BusinessRuleServiceInst.getAllColumns(tableName, businessRuleType)){
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("column", column);

			// for (String datatype : BusinessRuleServiceInst.getAllColumns(tableName)){
			// 	JsonObjectBuilder job2 = Json.createObjectBuilder();
			// 	job2.add("datatype", datatype);
			// }

			// job.add("datatype", job);
			jab.add(job);
		}

		JsonArray array = jab.build();
		return array.toString();
	}
	// get all tables
}