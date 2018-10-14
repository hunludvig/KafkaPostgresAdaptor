package fi.hunludvig.aiven.consumer;

import fi.hunludvig.aiven.model.Diagnostic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdaptorTest {
	@Autowired
	private DiagnosticAdaptor adaptor;
	
	@MockBean
	private DiagnosticRepository repository;
	
	@Test
	public void testAdaptor() {
		adaptor.saveToDatabase(new Diagnostic());
		verify(repository, times(1)).save(any(Diagnostic.class));
	}
}