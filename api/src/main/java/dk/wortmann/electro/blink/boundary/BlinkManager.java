package dk.wortmann.electro.blink.boundary;

import dk.wortmann.electro.blink.enitity.Blink;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class BlinkManager {
    private static final Logger LOG = LogManager.getLogger(BlinkManager.class);

    @PersistenceContext(unitName = "ELECTRO-PU")
    EntityManager em;


    /**
     * Default constructor
     */
    public BlinkManager() {}

    public BlinkManager(EntityManager em) {
        this.em = em;
    }

    public Blink findById(long id) {
        return this.em.find(Blink.class, id);
    }

    public void delete(long id) {
        try {
            Blink blink = this.em.getReference(Blink.class, id);
            em.remove(blink);
        } catch (EntityNotFoundException e) {
            LOG.error("Unable to find blink with id: {}", id);
        }
    }

    public List<Blink> all(int limit) {
        TypedQuery<Blink> query = this.em.createNamedQuery(Blink.findAll, Blink.class);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    public Blink save(Blink blink) {
        return this.em.merge(blink);
    }
}
