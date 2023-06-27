package ec.edu.ups.demo.helloworld;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.opentracing.Traced;

import ec.edu.ups.demo.helloworld.bussines.PersonaON;
import ec.edu.ups.demo.helloworld.model.Persona;

@Path("/personas")
public class PersonaApi {
	
	@Inject
	private PersonaON personaOn;
	@Inject
	Logger log;
	
	
	@POST
	@Path("/")
	@Traced(operationName = "crear-persona")
	@Counted(description = "Contador unlock", absolute = true)
	@Timed(name = "crear-persona-time", description = "Tiempo de procesamiento de unlock", unit = MetricUnits.MILLISECONDS, absolute = true)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({ MediaType.APPLICATION_JSON })
	@Operation(description = "Crear una persona", summary = "Crear una persona")
	@APIResponses(value={
	@APIResponse(responseCode = "200", description = "Persona creada",
	             content = @Content(mediaType = MediaType.APPLICATION_JSON ,
	                                schema = @Schema(implementation = String.class))),
	@APIResponse(responseCode = "400", description = "No se pudo crear la persona")}
	)
	public Response createPerson(
			@FormParam("cedula") String cedula,
			@FormParam("nombre") String nombre,
			@FormParam("apellido") String apellido,
			@FormParam("correo") String correo,
			@FormParam("telefono") String telefono
			) {
		try {
			log.info("Se genera un tiempo de espera aleatorio");
			
			Persona persona = new Persona();
			persona.setCedula(cedula);
			persona.setApellido(apellido);
			persona.setNombre(nombre);
			persona.setCorreo(correo);
			persona.setTelefono(telefono);
			personaOn.insertarUsuario(persona);
			
		} catch (Exception e) {
			e.printStackTrace();
			JsonObject resp = Json.createObjectBuilder().add("error", "Error, revise que la cédula no esté repetida").build();
			return Response.status(Response.Status.BAD_REQUEST).entity(resp).build();
		}
		
		JsonObject resp = Json.createObjectBuilder().add("estado", "Ok").build();
		return Response.ok(resp).build();
		
	}
	
	@GET
	@Path("/")
	@Traced(operationName = "listar")
	@Counted(description = "Listar", absolute = true)
	@Timed(name = "listar-time", description = "Listar personas", unit = MetricUnits.MILLISECONDS, absolute = true)
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(description = "Listar personas", summary = "Listar personas")
	@APIResponses(value={
	@APIResponse(responseCode = "200", description = "Personas listadas",
	             content = @Content(mediaType = MediaType.APPLICATION_JSON ,
	                                schema = @Schema(implementation = String.class))),
	@APIResponse(responseCode = "400", description = "No encontradas")}
	)
	public List<Persona> listar() {
		try {
			log.info("Se genera un tiempo de espera aleatorio");
			Thread.sleep((long)(Math.random() * 1000));
			
			return personaOn.getUsuarios();
			//JsonArray.fromOb
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}

}
