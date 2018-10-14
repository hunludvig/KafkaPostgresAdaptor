package fi.hunludvig.aiven.producer;

import fi.hunludvig.aiven.kafka.KafkaTest;
import fi.hunludvig.aiven.model.Diagnostic;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.listener.config.ContainerProperties;
import org.springframework.kafka.test.utils.KafkaTestUtils;

import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class KafkaProducerTest extends KafkaTest{
	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerTest.class);

	private KafkaMessageListenerContainer<String, Diagnostic> container;

	private BlockingQueue<ConsumerRecord<String, Diagnostic>> records;

	@Autowired
	private KafkaSender sender;
	
	@Before
	public void setUp() throws Exception {
		Map<String, Object> consumerProperties
			= KafkaTestUtils.consumerProps("sender", "false", embeddedKafka);

		DefaultKafkaConsumerFactory<String, Diagnostic> consumerFactory
			= new DefaultKafkaConsumerFactory<>(consumerProperties);
		
		ContainerProperties containerProperties = new ContainerProperties(TOPIC);
		container = new KafkaMessageListenerContainer<>(consumerFactory, containerProperties);
		records = new LinkedBlockingQueue<>();
		container.setupMessageListener((MessageListener<String, Diagnostic>) (ConsumerRecord<String, Diagnostic> record) -> {
			LOGGER.debug("test-listener received message='{}'", record.toString());
			records.add(record);
		});
		container.start();
		ContainerTestUtils.waitForAssignment(container, 1);
	}
	
	@After
	public void tearDown() {
		container.stop();
	}

	@Test
	public void testSend() throws InterruptedException {
		sender.send(new Diagnostic());
		ConsumerRecord<String, Diagnostic> received = records.poll(10, TimeUnit.SECONDS);
		Assert.assertNotNull(received);
	}
}