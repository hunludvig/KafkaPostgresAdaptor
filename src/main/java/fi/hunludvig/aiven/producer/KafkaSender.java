package fi.hunludvig.aiven.producer;

import fi.hunludvig.aiven.model.Diagnostic;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Component;

@Component
public class KafkaSender {
	private static final Logger LOGGER = getLogger(KafkaSender.class);
	
	@Value("${fi.hunludvig.aiven.producer.topic}")
	private String TOPIC;
	
	@Autowired
	private KafkaTemplate<String, Diagnostic> template;
	
	private JsonSerializer<Diagnostic> serializer = new JsonSerializer<>();
	
	public void send(Diagnostic diagnostic) {
		template.send(TOPIC, diagnostic);
	}
}