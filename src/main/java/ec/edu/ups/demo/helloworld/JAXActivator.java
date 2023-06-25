package ec.edu.ups.demo.helloworld;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;

@ApplicationPath("rest")
@OpenAPIDefinition(info = @Info(
		title = "Hello World MicroProfile",
		description = "API de muestra Hello World MicroProfile", version = "1.0.0"))
public class JAXActivator extends Application {

}
