package fi.hunludvig.aiven.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Diagnostic {
    @Id
    private Long id;

    public Long getId() {
        return id;
    }
}