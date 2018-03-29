package dk.wortmann.electro.blink.boundary;

import dk.wortmann.electro.blink.enitity.BlinkGroup;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class BlinkGroupsResource {

    @Inject
    private BlinkGroupManager manager;

    @GET
    public List<BlinkGroup> all(@QueryParam("limit") @DefaultValue("200") int limit,
                                @QueryParam("group") @DefaultValue("300") int groupInSeconds) {
        return this.manager.all(limit, groupInSeconds);
    }
}
