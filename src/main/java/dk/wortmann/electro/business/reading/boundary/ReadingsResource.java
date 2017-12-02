package dk.wortmann.electro.business.reading.boundary;

import dk.wortmann.electro.business.reading.entity.Reading;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("readings")
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class ReadingsResource {

    @Inject
    ReadingManager manager;

    @Path("{id}")
    public ReadingResource find(@PathParam("id") long id) {
        return new ReadingResource(id, manager);
    }

    @GET
    public List<Reading> all() {
        return this.manager.all();
    }

    @POST
    public Response save(Reading reading, @Context UriInfo uriInfo) {
        Reading saved = this.manager.save(reading);
        URI uri = uriInfo.getAbsolutePathBuilder().path("/" + saved.getId()).build();
        return Response.created(uri).build();
    }
}
