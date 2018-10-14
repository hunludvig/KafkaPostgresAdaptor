package fi.hunludvig.aiven.producer;

import fi.hunludvig.aiven.model.Diagnostic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StreamerTest {
	@Autowired
	private DiagnosticsStreamer streamer;
	
	@MockBean
	private KafkaSender sender;
	
	@Test
	public void testRecurringSending() throws InterruptedException {
		Thread.sleep(1000);
		verify(sender, atLeast(8)).send(any(Diagnostic.class));
	}
}