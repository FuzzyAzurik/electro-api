package dk.wortmann.electro.business.kwhspan.boundary;

import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class KwhSpanManagerIT {

    private static final String ENDPOINT = "http://localhost:9090/electro/api/blinks";
    private Client client;

    @Before
    public void setUp() {
        client = ClientBuilder.newClient();
    }

    @Test
    public void getBlink() {
        WebTarget target = client.target(ENDPOINT);
        Invocation invocation = target.request(MediaType.APPLICATION_JSON_TYPE).buildGet();
        Response response = invocation.invoke();

        System.out.println(response.readEntity(String.class));
    }
}
