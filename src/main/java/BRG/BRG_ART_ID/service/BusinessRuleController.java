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
	public Response saveBusinessRule(@FormParam("BusinessFunction") String BusinessFunction, @FormParam("BusinessName") String BusinessName, @FormParam("BusinessTable") String BusinessTable,
		@FormParam("BusinessColumn") String BusinessColumn, @FormParam("BusinessValue1") int BusinessValue1, @FormParam("BusinessRule") String BusinessRule, @FormParam("BusinessValue2") int BusinessValue2, @FormParam("BusinessError") String BusinessError) throws SQLException {

		BusinessRuleDaoImpl BusinessRuleServiceInst = new BusinessRuleDaoImpl();
		Boolean result = null;
		

		result = BusinessRuleServiceInst.saveBusinessRule(BusinessFunction, BusinessName, BusinessTable, BusinessColumn, BusinessValue1, BusinessRule, BusinessValue2, BusinessError);

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
			job.add("tableNaam", table);

			jab.add(job);
		}

		JsonArray array = jab.build();
		return array.toString();
	}
	// get all tables

	// get all tables
	@GET
	@Path("/columns/{tableNaam}")
	@Produces("application/json")
	public String getAllColumns(@PathParam("tableNaam") String tableNaam) throws SQLException {
		BusinessRuleDaoImpl BusinessRuleServiceInst = new BusinessRuleDaoImpl();

		JsonArrayBuilder jab = Json.createArrayBuilder();

		for (String column : BusinessRuleServiceInst.getAllColumns(tableNaam)){
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("column", column);
			// for (String datatype : column){
			// 	JsonObjectBuilder job2 = Json.createObjectBuilder();
			// 	job2.add("datatype", datatype);
			// }
			jab.add(job);
		}

		JsonArray array = jab.build();
		return array.toString();
	}
	// get all tables
}