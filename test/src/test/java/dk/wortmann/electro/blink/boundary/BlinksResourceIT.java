package dk.wortmann.electro.blink.boundary;

import dk.wortmann.electro.Authentication;
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

import static org.assertj.core.api.Assertions.assertThat;

public class BlinksResourceIT {
    private static final Logger LOG = LogManager.getLogger(BlinksResourceIT.class);
    private static final String ENDPOINT = "http://localhost:9090/electro-api/api/blinks";
    private Client client;

    @Before
    public void setUp() {
        client = ClientBuilder.newClient().register(new Authentication("jacob", "password"));
    }

    @Test
    public void all() {
        // GIVEN
        WebTarget target = this.client.target(ENDPOINT);
        JsonObject blink = Json.createObjectBuilder()
                .add("lightValue", 10)
                .add("lightRatio", 1.3)
                .add("insertedTime", LocalDateTime.now().toString())
                .add("kwhValue", 0.0001)
                .add("meterId", 99806)
                .build();
        Entity<String> entity = Entity.json(blink.toString());
        Response saveResponse = target.request(MediaType.APPLICATION_JSON).buildPost(entity).invoke();

        // WHEN
        Response readResponse = target.request(MediaType.APPLICATION_JSON).buildGet().invoke();
        JsonReader reader = Json.createReader(IOUtils.toInputStream(readResponse.readEntity(String.class), Charset.forName("utf8")));
        JsonArray jsonArray = reader.readArray();
        LOG.info("response status for read " + readResponse.getStatusInfo().getFamily());

        // THEN
        assertThat(readResponse.getStatusInfo().getFamily()).isEqualByComparingTo(Response.Status.Family.SUCCESSFUL);
        assertThat(jsonArray.size()).isGreaterThan(0);
    }



    @Test
    public void save() {
        WebTarget target = this.client.target(ENDPOINT);
        JsonObject blink = Json.createObjectBuilder()
                .add("lightValue", 10)
                .add("lightRatio", 1.3)
                .add("insertedTime", LocalDateTime.now().toString())
                .add("kwhValue", 0.0001)
                .add("meterId", 99806)
                .build();
        Entity<String> entity = Entity.json(blink.toString());
        Response response = target.request(MediaType.APPLICATION_JSON).buildPost(entity).invoke();
        assertThat(response.getStatusInfo().getFamily()).isEqualByComparingTo(Response.Status.Family.SUCCESSFUL);
        assertThat(response.getLocation()).isNotNull();
    }

    @Test
    public void delete() {
        // GIVEN
        WebTarget target = this.client.target(ENDPOINT);
        JsonObject blink = Json.createObjectBuilder()
                .add("lightValue", 10)
                .add("lightRatio", 1.3)
                .add("insertedTime", LocalDateTime.now().toString())
                .add("kwhValue", 0.0001)
                .add("meterId", 99806)
                .build();
        Entity<String> entity = Entity.json(blink.toString());
        Response saveResponse = target.request(MediaType.APPLICATION_JSON).buildPost(entity).invoke();

        // WHEN
        target = this.client.target(saveResponse.getLocation());
        Response deleteResponse = target.request().buildDelete().invoke();

        // THEN
        assertThat(deleteResponse.getStatusInfo().getFamily()).isEqualByComparingTo(Response.Status.Family.SUCCESSFUL);
    }
}
