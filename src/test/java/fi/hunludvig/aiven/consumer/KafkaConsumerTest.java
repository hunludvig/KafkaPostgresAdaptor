package fi.hunludvig.aiven.consumer;

import fi.hunludvig.aiven.kafka.KafkaTest;
import fi.hunludvig.aiven.model.Diagnostic;

import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@RunWith(SpringRunner.class)
public class KafkaConsumerTest extends KafkaTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerTest.class);
	
	@Autowired
	private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;
	
	@Autowired
	private DiagnosticListener listener;
	
	@MockBean
	private DiagnosticAdaptor mockAdaptor;
	
	private KafkaTemplate<String, Diagnostic> template;

	@Before
	public void setUp() throws Exception {
		Map<String, Object> properties
			= KafkaTestUtils.producerProps(embeddedKafka);
		properties.put("value.serializer",
			"org.springframework.kafka.support.serializer.JsonSerializer");
		ProducerFactory<String, Diagnostic> producerFactory
			= new DefaultKafkaProducerFactory<>(properties);
		template = new KafkaTemplate<>(producerFactory);

		for (MessageListenerContainer messageListenerContainer :
			kafkaListenerEndpointRegistry.getListenerContainers()) {
			ContainerTestUtils.waitForAssignment(messageListenerContainer, 1);
		}
	}
	
	@Test
	public void testSend() throws InterruptedException {
		template.send(TOPIC, new Diagnostic());
		LOGGER.debug("Diagnostic object sent");
		Thread.sleep(1000);
		verify(mockAdaptor, times(1)).saveToDatabase(any(Diagnostic.class));
	}
}