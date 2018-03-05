package dk.wortmann.electro.kwhspan.boundary;

import dk.wortmann.electro.kwhspan.entity.KwhSpan;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("kwhSpans")
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class kwhSpansResource {

    @Inject
    private KwhSpanManager manager;

    @GET
    public List<KwhSpan> all(@QueryParam("limit") @DefaultValue("200") int limit,
                             @QueryParam("span") @DefaultValue("300") int spanInSeconds) {
        return this.manager.all(limit, spanInSeconds);
    }
}
