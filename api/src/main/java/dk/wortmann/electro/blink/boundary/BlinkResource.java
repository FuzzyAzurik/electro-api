package dk.wortmann.electro.blink.boundary;

import dk.wortmann.electro.blink.enitity.Blink;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;

public class BlinkResource {
    private long id;
    private BlinkManager manager;

    public BlinkResource(long id, BlinkManager manager) {
        this.id = id;
        this.manager = manager;
    }

    @PUT
    @RolesAllowed({"admin", "producers"})
    public Blink save(Blink blink) {
        blink.setId(id);
        return manager.save(blink);
    }

    @GET
    public Blink find() {
        return manager.findById(id);
    }

    @DELETE
    @RolesAllowed({"admin"})
    public void delete() {
        manager.delete(id);
    }
}
