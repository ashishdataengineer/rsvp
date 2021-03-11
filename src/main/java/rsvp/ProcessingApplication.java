package rsvp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ProcessingApplication implements CommandLineRunner {

	//private static final Logger LOGGER = LoggerFactory.getLogger(ProcessingApplication.class);

	@Autowired
	private ProcessingService processingService;

	public ProcessingApplication() {

	}

	public static void main(String[] args) {

		SpringApplication.run(ProcessingApplication.class, args);

	}

	@Override
	public void run(String... args) {

		processingService.processing();

	}

}
