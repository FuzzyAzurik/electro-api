package dk.wortmann.electro.blink.boundary;

import dk.wortmann.electro.blink.enitity.Blink;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("blinks")
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class BlinksResource {

    @Inject
    private BlinkManager manager;

    @Path("{id}")
    public BlinkResource find(@PathParam("id") long id) {
        return new BlinkResource(id, manager);
    }

    @GET
    public List<Blink> all() {
        return this.manager.all();
    }

    @POST
    public Response save(Blink blink, @Context UriInfo uriInfo) {
        Blink saved = this.manager.save(blink);
        URI uri = uriInfo.getAbsolutePathBuilder().path("/" + saved.getId()).build();
        return Response.created(uri).build();
    }
}
