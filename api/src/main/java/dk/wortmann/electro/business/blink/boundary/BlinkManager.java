package dk.wortmann.electro.business.blink.boundary;

import dk.wortmann.electro.business.blink.enitity.Blink;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

@RequestScoped
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
