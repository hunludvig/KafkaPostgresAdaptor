package fi.hunludvig.aiven.consumer;

import fi.hunludvig.aiven.model.Diagnostic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface DiagnosticRepository extends CrudRepository<Diagnostic, Long> { }