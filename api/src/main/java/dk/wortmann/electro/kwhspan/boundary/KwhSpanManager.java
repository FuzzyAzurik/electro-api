package dk.wortmann.electro.kwhspan.boundary;

import dk.wortmann.electro.kwhspan.entity.KwhSpan;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class KwhSpanManager {

    @PersistenceContext(unitName = "ELECTRO-PU")
    private EntityManager em;

    public KwhSpanManager() {}

    public KwhSpanManager(EntityManager em) {
        this.em = em;
    }

    public List<KwhSpan> all(int limit, int spanInSeconds) {
        TypedQuery<KwhSpan> query = this.em.createNamedQuery(KwhSpan.findAll, KwhSpan.class);
        query.setMaxResults(limit);
        query.setParameter(1, spanInSeconds);
        return query.getResultList();
    }
}
