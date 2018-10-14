package fi.hunludvig.aiven.consumer;

import fi.hunludvig.aiven.model.Diagnostic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assumptions.assumeThat;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoryTest {
	
	@Autowired
	private DiagnosticRepository repository;
	
	@Test
	public void testSaving() throws InterruptedException {
		long oldCount = repository.count();
		repository.save(new Diagnostic());
		assumeThat(repository.count()).isGreaterThan(oldCount);
	}
}