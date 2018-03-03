package dk.wortmann.electro.blink.boundary;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.nio.charset.Charset;
import java.time.LocalDateTime;

import static org.junit.Assert.assertTrue;

public class BlinksResourceIT {
    private static final Logger LOG = LogManager.getLogger(BlinksResourceIT.class);
    private static final String ENDPOINT = "http://localhost:9090/electro/api/blinks";
    private Client client;

    @Before
    public void setUp() {
        client = ClientBuilder.newClient();
    }

    @Test
    public void all() {
        WebTarget target = client.target(ENDPOINT);
        JsonObject blink = Json.createObjectBuilder()
                .add("lightValue", 10)
                .add("lightRatio", 1.3)
                .add("insertedTime", LocalDateTime.now().toString())
                .add("kwhValue", 0.0001)
                .add("meterId", 99806)
                .build();
        LOG.debug(blink);
        Entity<JsonObject> entity = Entity.json(blink);
        Response saveResponse = target.request(MediaType.APPLICATION_JSON_TYPE).buildPost(entity).invoke();
        LOG.info("response status for save " + saveResponse.getStatusInfo().getFamily());
        assertTrue(saveResponse.getStatusInfo().getFamily().equals(Response.Status.Family.SUCCESSFUL));


        Response readResponse = target.request(MediaType.APPLICATION_JSON_TYPE).buildGet().invoke();
        JsonReader reader = Json.createReader(IOUtils.toInputStream(readResponse.readEntity(String.class), Charset.forName("utf8")));
        JsonArray jsonArray = reader.readArray();
        LOG.info("response status for read " + saveResponse.getStatusInfo().getFamily());
        assertTrue(jsonArray.size() > 0);
    }

    @Test
    public void save() {
        WebTarget target = client.target(ENDPOINT);
        JsonObject blink = Json.createObjectBuilder()
                .add("lightValue", 10)
                .add("lightRatio", 1.3)
                .add("insertedTime", LocalDateTime.now().toString())
                .add("kwhValue", 0.0001)
                .add("meterId", 99806)
                .build();
        Entity<JsonObject> entity = Entity.json(blink);
        Response response = target.request(MediaType.APPLICATION_JSON_TYPE).buildPost(entity).invoke();
        assertTrue(response.getStatusInfo().getFamily().equals(Response.Status.Family.SUCCESSFUL));
    }
}
