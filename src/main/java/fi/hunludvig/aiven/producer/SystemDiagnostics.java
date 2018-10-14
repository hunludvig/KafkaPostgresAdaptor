package fi.hunludvig.aiven.producer;

import fi.hunludvig.aiven.model.Diagnostic;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SystemDiagnostics {
	private static final Logger LOGGER = LoggerFactory.getLogger(SystemDiagnostics.class);
	
	public Diagnostic getSystemStatistics() {
		OperatingSystemMXBean myOsBean
			= ManagementFactory.getOperatingSystemMXBean();
		
		return new Diagnostic();
	}
}