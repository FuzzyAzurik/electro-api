package dk.wortmann.electro.blink.boundary;

import dk.wortmann.electro.blink.enitity.Blink;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class BlinkManager {

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
            // TODO: 11/27/17 we can log it!
        }
    }

    public List<Blink> all() {
        return this.em.createNamedQuery(Blink.findAll, Blink.class).getResultList();
    }

    public Blink save(Blink blink) {
        return this.em.merge(blink);
    }


}
