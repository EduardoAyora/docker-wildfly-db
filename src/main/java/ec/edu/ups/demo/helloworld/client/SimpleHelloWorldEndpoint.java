package ec.edu.ups.demo.helloworld.client;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.opentracing.Traced;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/proxy")
public class SimpleHelloWorldEndpoint {
	
	@Inject
    @RestClient
    SimpleHelloWorldIntf service;
	
	 @GET
	 @Traced(operationName = "cliente-rest-mp")
	 @Path("/llamarJson")
	 @Operation(description = "Invocar por medio de cliente microprofile a HelloWorld con respuesta en JSON", summary = "client call getHelloWorldJSON")
		@APIResponse(responseCode = "200", description = "Saludo respuesta",
					content = @Content(mediaType = MediaType.APPLICATION_JSON,
						schema = @Schema(implementation = String.class)))
	 public String getHello() {
		 return service.getHello();
	 }

}
