package ec.edu.ups.demo.helloworld;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
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
import org.eclipse.microprofile.opentracing.Traced;

@Path("/")
public class HelloWorld {
	@Inject
	HelloService helloService;

	/**
	@GET
	@Path("/json")
	@Traced(operationName = "hello-json")
	@Counted(description = "Contador saludo 1", absolute = true)
	@Timed(name = "saludo1-time", description = "Tiempo de procesamiento de saludo 1", unit = MetricUnits.MILLISECONDS, absolute = true)
	@Produces({ MediaType.APPLICATION_JSON })
	@Operation(description = "Invocar a endpoint HelloWorld con respuesta en JSON", summary = "call getHelloWorldJSON")
	@APIResponse(responseCode = "200", description = "Saludo respuesta",
				content = @Content(mediaType = MediaType.APPLICATION_JSON,
					schema = @Schema(implementation = String.class)))
	public Response getHelloWorldJSON() {
		JsonObject resp = Json.createObjectBuilder().add("resultado", helloService.createHelloMessage("World")).build();
		return Response.ok(resp).build();
	}

	@GET
	@Path("/xml")
	@Traced(operationName = "hello-xml")
	@Counted(description = "Contador saludo 2", absolute = true)
	@Timed(name = "saludo2-time", description = "Tiempo de procesamiento de saludo 2", unit = MetricUnits.MILLISECONDS, absolute = true)
	@Produces({ MediaType.APPLICATION_XML })
	@Operation(description = "Invocar a endpoint HelloWorld con respuesta en XML", summary = "call getHelloWorldXML")
	@APIResponse(responseCode = "200", description = "Saludo respuesta",
				content = @Content(mediaType = MediaType.APPLICATION_XML,
					schema = @Schema(implementation = String.class)))
	public String getHelloWorldXML() {
		return "<xml><result>" + helloService.createHelloMessage("World") + "</result></xml>";
	}

	@GET
	@Path("/echo/{texto}")
	@Traced(operationName = "echo-operation")
	@Counted(description = "Contador echo", absolute = true)
	@Timed(name = "echo-time", description = "Tiempo de procesamiento de echo", unit = MetricUnits.MILLISECONDS, absolute = true)
	@Produces({ MediaType.APPLICATION_JSON })
	@Operation(description = "Simple echo con respuesta en formato JSON", summary = "echo jaxrs")
	@APIResponse(responseCode = "200", description = "Echo respuesta",
			content = @Content(mediaType = MediaType.APPLICATION_JSON,
			schema = @Schema(implementation = String.class)))
	public Response replyEcho(
			@Parameter(description = "Texto para respuesta de echo", required = true)
			@PathParam("texto") String texto) {
		JsonObject resp = Json.createObjectBuilder().add("echo",  texto ).build();
		return Response.ok(resp).build();
	}
	
	@GET
	@Path("/cliente1")
	@Traced(operationName = "cliente-simple-operation")
	@Produces(MediaType.TEXT_PLAIN)
	@Operation(description = "Cliente simple JAX RS", summary = "cliente jaxrs")
	@APIResponse(responseCode = "200", description = "Cliente respuesta",
			content = @Content(mediaType = MediaType.TEXT_PLAIN,
			schema = @Schema(implementation = String.class)))
	public String clienteServicio() {
		Client client = ClientBuilder.newClient();
		String response = client.target("http://localhost:8080/helloworld-mp/rest/json").request().get(String.class);
		return response;
	}**/

}
