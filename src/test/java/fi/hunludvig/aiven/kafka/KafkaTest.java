package fi.hunludvig.aiven.kafka;

import org.junit.ClassRule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@DirtiesContext
@TestPropertySource(properties = "spring.kafka.bootstrap-servers=${spring.embedded.kafka.brokers}")
public class KafkaTest {
	@Value("${fi.hunludvig.aiven.topic}")
	protected String TOPIC;

	@ClassRule
	public static KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, true);
}
