package dk.wortmann.electro.kwhspan.boundary;

import dk.wortmann.electro.kwhspan.entity.KwhSpan;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("kwhSpans")
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class kwhSpansResource {

    @Inject
    private KwhSpanManager manager;

    @GET
    public List<KwhSpan> all() {
        return this.manager.all();
    }
}
