package ec.edu.ups.demo.helloworld.health;

import java.util.concurrent.ThreadLocalRandom;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

@Liveness
public class LivenessCheck implements HealthCheck {

	@Override
	public HealthCheckResponse call() {
		return HealthCheckResponse.builder().name("liveness").state(ThreadLocalRandom.current().nextBoolean()).build();
	}

}
