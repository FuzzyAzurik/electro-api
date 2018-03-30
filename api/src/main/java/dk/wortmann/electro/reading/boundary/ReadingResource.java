package dk.wortmann.electro.reading.boundary;

import dk.wortmann.electro.reading.entity.Reading;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;

public class ReadingResource {
    private final ReadingManager manager;
    private final long id;

    public ReadingResource(long id, ReadingManager manager) {
        this.id = id;
        this.manager = manager;
    }

    @GET
    public Reading find() {
        return this.manager.findById(id);
    }

    @PUT
    @RolesAllowed({"admin", "producers"})
    public Reading save(Reading reading) {
        reading.setId(id);
        return this.manager.save(reading);
    }

    @DELETE
    @RolesAllowed({"admin"})
    public void delete() {
        manager.delete(id);
    }
}
