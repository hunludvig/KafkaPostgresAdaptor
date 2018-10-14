package fi.hunludvig.aiven.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Diagnostic {

	@Id
	@SequenceGenerator(name = "diagnostic_id_seq",
		sequenceName = "diagnostic_id_seq",
		allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
		generator = "diagnostic_id_seq")
	@Column(updatable = false)
	private Long id;

	public Diagnostic() {}

	public Diagnostic(String name, String arch, int availableProcessors,
		double systemLoadAverage, long currentTimeMillis) {
		this.osName = name;
		this.arch = arch;
		this.processors = availableProcessors;
		this.systemLoad = systemLoadAverage;
		this.timestamp = currentTimeMillis;
	}

	private String osName;
	private String arch;
	private int processors;
	private double systemLoad;
	@Column(name = "\"timestamp\"")
	private long timestamp;
	
	public Long getId() {
		return id;
	}

	public String getOsName() {
		return osName;
	}

	public String getArch() {
		return arch;
	}

	public int getProcessors() {
		return processors;
	}

	public double getSystemLoad() {
		return systemLoad;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setOsName(String osName) {
		this.osName = osName;
	}

	public void setArch(String arch) {
		this.arch = arch;
	}

	public void setProcessors(int processors) {
		this.processors = processors;
	}

	public void setSystemLoad(double systemLoad) {
		this.systemLoad = systemLoad;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
}
