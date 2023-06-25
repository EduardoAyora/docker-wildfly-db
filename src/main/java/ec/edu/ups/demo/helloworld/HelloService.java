package ec.edu.ups.demo.helloworld;

import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.opentracing.Traced;

import io.opentracing.Span;
import io.opentracing.Tracer;

public class HelloService {

	@Inject
	@ConfigProperty(name="hello")
	private String saludo;
	
	@Inject
    private Tracer tracer;
	
	@Traced
    public String createHelloMessage(String name) {
		Span prepareHelloSpan = tracer.buildSpan("prepare-hello-service").start();
		String result = saludo + " " + name.toUpperCase() + "!";
		prepareHelloSpan.finish();
        return result;
    }

}
