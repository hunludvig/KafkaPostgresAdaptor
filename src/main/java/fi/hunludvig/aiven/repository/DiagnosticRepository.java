package fi.hunludvig.aiven.repository;

import fi.hunludvig.aiven.model.Diagnostic;
import org.springframework.data.repository.CrudRepository;

public interface DiagnosticRepository extends CrudRepository<Diagnostic, Long> { }