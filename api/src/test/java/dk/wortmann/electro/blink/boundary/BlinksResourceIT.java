package dk.wortmann.electro.blink.boundary;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class BlinksResourceIT {
//    private static final Logger LOG = LogManager.getLogger(BlinksResourceIT.class);
    private static final String ENDPOINT = "http://localhost:9090/electro/api/blinks";
    private Client client;

    @Before
    public void setUp() {
        client = ClientBuilder.newClient();
    }

    @Test
    public void find() {
    }

    @Test
    public void all() {
        WebTarget target = client.target(ENDPOINT);
        Invocation invocation = target.request(MediaType.APPLICATION_JSON_TYPE).buildGet();
        Response response = invocation.invoke();

//        LOG.info(response.readEntity(String.class));
    }

    @Test
    public void save() {
    }
}
