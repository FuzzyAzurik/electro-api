package dk.wortmann.electro.business.readings.boundary;

import dk.wortmann.electro.business.readings.enitity.Reading;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ReadingManager {

    @PersistenceContext
    EntityManager em;

    public ReadingManager(EntityManager em) {
        this.em = em;
    }

    public Reading findById(long id) {
        return this.em.find(Reading.class, id);
    }

    public void delete(long id) {
        try {
            Reading reading = this.em.getReference(Reading.class, id);
            em.remove(reading);
        } catch (EntityNotFoundException e) {
            // TODO: 11/27/17 we can log it!
        }
    }

    public List<Reading> all() {
        return this.em.createNamedQuery(Reading.findAll, Reading.class).getResultList();
    }

    public Reading save(Reading reading) {
        return this.em.merge(reading);
    }


}
