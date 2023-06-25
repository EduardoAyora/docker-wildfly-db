package ec.edu.ups.demo.helloworld.client;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(baseUri = "http://localhost:8080/helloworld-mp/rest")
@Path("/")
public interface SimpleHelloWorldIntf {

	@GET
	@Path("/json")
	public String getHello();
	
}
