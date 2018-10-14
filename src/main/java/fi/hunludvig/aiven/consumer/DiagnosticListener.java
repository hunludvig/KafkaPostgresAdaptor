package fi.hunludvig.aiven.consumer;

import fi.hunludvig.aiven.model.Diagnostic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class DiagnosticListener {
	private static final Logger LOGGER = LoggerFactory.getLogger(DiagnosticListener.class);
	
	@Autowired
	private DiagnosticAdaptor adaptor;
	
	@KafkaListener(topics = "${fi.hunludvig.aiven.topic}")
	public void receive(Diagnostic payload) {
		LOGGER.info("received payload='{}'", payload);
		adaptor.saveToDatabase(payload);
	}
}