package dk.wortmann.electro.reading.boundary;

import dk.wortmann.electro.reading.entity.Reading;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ReadingManager {
    private static final Logger LOG = LogManager.getLogger(ReadingManager.class);

    @PersistenceContext(unitName = "ELECTRO-PU")
    EntityManager em;

    public ReadingManager() {}

    public ReadingManager(EntityManager em) {
        this.em = em;
    }

    public List<Reading> all() {
        return this.em.createNamedQuery(Reading.findAll, Reading.class).getResultList();
    }

    public Reading save(Reading reading) {
        return em.merge(reading);
    }

    public Reading findById(long id) {
        return em.find(Reading.class, id);
    }

    public void delete(long id) {
        try {
            Reading reading = em.getReference(Reading.class, id);
            em.remove(reading);
        } catch (EntityNotFoundException e) {
            LOG.error("Unable to find Reading for id: {}", id);
        }
    }
}
