package dk.wortmann.electro.blink.boundary;

import dk.wortmann.electro.blink.enitity.BlinkGroup;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class BlinkGroupManager {

    @PersistenceContext(unitName = "ELECTRO-PU")
    private EntityManager em;

    public BlinkGroupManager() {}

    public BlinkGroupManager(EntityManager em) {
        this.em = em;
    }

    public List<BlinkGroup> all(int limit, int groupInSeconds) {
        TypedQuery<BlinkGroup> query = this.em.createNamedQuery(BlinkGroup.findAll, BlinkGroup.class);
        query.setMaxResults(limit);
        query.setParameter(1, groupInSeconds);
        return query.getResultList();
    }
}
