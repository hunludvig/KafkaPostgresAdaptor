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

	public Long getId() {
		return id;
	}
}
