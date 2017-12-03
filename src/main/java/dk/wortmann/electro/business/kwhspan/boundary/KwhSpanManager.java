package dk.wortmann.electro.business.kwhspan.boundary;

import dk.wortmann.electro.business.kwhspan.entity.KwhSpan;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class KwhSpanManager {

    @PersistenceContext(unitName = "ELECTRO-PU")
    EntityManager em;

    public KwhSpanManager() {}

    public KwhSpanManager(EntityManager em) {
        this.em = em;
    }

    public List<KwhSpan> all() {
        return this.em.createNamedQuery(KwhSpan.findAll, KwhSpan.class).getResultList();
    }
}
