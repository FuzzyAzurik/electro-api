package dk.wortmann.electro.business.readings.boundary;

import dk.wortmann.electro.business.readings.enitity.Reading;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;

public class ReadingResource {
    private long id;
    private ReadingManager manager;

    public ReadingResource(long id, ReadingManager manager) {
        this.id = id;
        this.manager = manager;
    }

    @PUT
    public Reading save(Reading reading) {
        reading.setId(id);
        return manager.save(reading);
    }

    @GET
    public Reading find() {
        return manager.findById(id);
    }

    @DELETE
    public void delete() {
        manager.delete(id);
    }

}
