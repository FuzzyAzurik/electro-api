package dk.wortmann.electro.kwhspan.boundary;

import dk.wortmann.electro.Authentication;
import dk.wortmann.electro.blink.boundary.BlinksResourceIT;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import javax.json.Json;
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

public class kwhSpansResourceIT {
    private static final Logger LOG = LogManager.getLogger(BlinksResourceIT.class);
    private static final String ENDPOINT = "http://localhost:9090/electro-api/api/kwhSpans";
    private Client client;

    @Before
    public void setUp() {
        client = ClientBuilder.newClient().register(new Authentication("jacob", "password"));
    }

    @Test
    public void all() {
        // GIVEN
        WebTarget target = this.client.target("http://localhost:9090/electro-api/api/blinks");
        JsonObject blink = Json.createObjectBuilder()
                .add("lightValue", 10)
                .add("lightRatio", 1.3)
                .add("insertedTime", LocalDateTime.now().toString())
                .add("kwhValue", 0.0001)
                .add("meterId", 99806)
                .build();
        Entity<String> entity = Entity.json(blink.toString());
        target.request(MediaType.APPLICATION_JSON).buildPost(entity).invoke();

        // WHEN
        target = this.client.target(ENDPOINT).queryParam("limit", 10).queryParam("span", 300);
        Response readResponse = target.request(MediaType.APPLICATION_JSON).buildGet().invoke();

        // THEN
        assertThat(readResponse.getStatusInfo().getFamily()).isEqualByComparingTo(Response.Status.Family.SUCCESSFUL);
        JsonReader reader = Json.createReader(IOUtils.toInputStream(readResponse.readEntity(String.class), Charset.forName("utf8")));
    }
}
