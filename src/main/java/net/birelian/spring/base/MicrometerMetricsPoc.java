package net.birelian.spring.base;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MicrometerMetricsPoc {

	private final Counter counter;

	public MicrometerMetricsPoc(MeterRegistry meterRegistry) {

		this.counter = meterRegistry.counter("controllers_main_greeting");
	}

	public static void main(String[] args) {

		SpringApplication.run(MicrometerMetricsPoc.class, args);
	}

	@GetMapping
	@Timed(value = "controllers_main_greeting_timed")
	public String sayHello() {

		this.counter.increment();

		return "Hello!";
	}

}
