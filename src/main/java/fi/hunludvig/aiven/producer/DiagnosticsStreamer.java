package fi.hunludvig.aiven.producer;

import fi.hunludvig.aiven.model.Diagnostic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class DiagnosticsStreamer {
	@Autowired
	private SystemDiagnostics diagnostics;
	
	@Autowired
	KafkaSender sender;
	
	@Scheduled(fixedRate = 1000)
	public void sendDiagnostics() {
		Diagnostic diagnostic = diagnostics.getSystemStatistics();
		sender.send(diagnostic);
	}
}