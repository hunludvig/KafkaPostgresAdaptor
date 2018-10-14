package fi.hunludvig.aiven.consumer;

import fi.hunludvig.aiven.model.Diagnostic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DiagnosticAdaptor {
	private static final Logger LOGGER = LoggerFactory.getLogger(DiagnosticAdaptor.class);
	
	@Autowired
	private DiagnosticRepository repository;
	
	public void saveToDatabase(Diagnostic diagnostic) {
		repository.save(diagnostic);
	}
}