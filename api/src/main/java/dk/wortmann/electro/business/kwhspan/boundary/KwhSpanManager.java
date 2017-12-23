package dk.wortmann.electro.business.kwhspan.boundary;

import dk.wortmann.electro.business.kwhspan.entity.KwhSpan;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@RequestScoped
public class KwhSpanManager {

    @PersistenceContext(unitName = "ELECTRO-PU")
    private EntityManager em;

    public KwhSpanManager() {}

    public KwhSpanManager(EntityManager em) {
        this.em = em;
    }

    public List<KwhSpan> all() {
        TypedQuery<KwhSpan> query = this.em.createNamedQuery(KwhSpan.findAll, KwhSpan.class);
        return query.getResultList();
    }
}
