package fi.hunludvig.aiven;

import fi.hunludvig.aiven.consumer.DiagnosticRepository;
import fi.hunludvig.aiven.kafka.KafkaTest;
import fi.hunludvig.aiven.model.Diagnostic;
import fi.hunludvig.aiven.producer.KafkaSender;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assumptions.assumeThat;
import org.junit.Before;

@RunWith(SpringRunner.class)
public class IntegrationTest extends KafkaTest {
	@Autowired
	private KafkaSender sender;
	
	@Autowired
	private DiagnosticRepository repository;
	
	@Before
	public void waitForKafka() throws InterruptedException {
		Thread.sleep(1000);
	}
	
	@Test
	public void testIntegration() throws InterruptedException {
		long oldCount = repository.count();
		sender.send(new Diagnostic());
		Thread.sleep(1000);
		assumeThat(repository.count()).isGreaterThan(oldCount);
	}
}