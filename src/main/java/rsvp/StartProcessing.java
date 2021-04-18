package rsvp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value=2)
@Component
public class StartProcessing implements CommandLineRunner {

	@Autowired
	private ProcessingService ProcessingServicece;

	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("PROCESSING STARTED #########################################################");
		ProcessingServicece.run();

	}

}
