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


import BRG.BRG_ART_ID.dao.UserDaoImpl;
import BRG.BRG_ART_ID.domain.User;

@Path("/auth")
public class LoginService {

	// endpoint voor het inloggen in de cms
	// returned user of null
	@POST
	@Path("/login")
	@Produces("application/json")
	public Response checkLogin(@FormParam("email") String email, @FormParam("password") String password) throws SQLException {
		UserDaoImpl userService = new UserDaoImpl();
		User user = userService.login(email, password);

		if(user == null) {
			return Response.ok("null").build();
		}else {
			return Response.ok(user).build();
		}
	}
}