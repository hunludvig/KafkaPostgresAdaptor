package fi.hunludvig.aiven.producer;

import fi.hunludvig.aiven.model.Diagnostic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assumptions.assumeThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SystemDiagnoticTest {
	@Autowired
	private SystemDiagnostics diagnostics;
	
	@Test
	public void testSystemDiagnostics() {
		Diagnostic diagnostic = diagnostics.getSystemStatistics();
		assumeThat(diagnostic).isNotNull();
	}
}
