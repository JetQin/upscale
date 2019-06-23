package io.github.jetqin.upscale.integration;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.github.jetqin.upscale.domain.Person;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.junit.Assert.assertTrue;


@RunWith(MockitoJUnitRunner.class)
public class IntegrationTest {

//    @Rule
//    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig()
//            .port(8089)
//            .httpsPort(8443));

    WireMockServer wm = new WireMockServer(options()
            .bindAddress("localhost")
            .port(8089));

    RestTemplate restTemplate;

    ResponseEntity response;

    @Before
    public void setup(){
        restTemplate = new RestTemplate();
        response = null;
        wm.start();
    }

    @Test
    public void testListPerson(){
//        stubFor(get(urlEqualTo("/person/list"))
//                .withHeader("Accept", equalTo("application/json"))
//                .willReturn(aResponse()
//                        .withStatus(200)
//                        .withHeader("Content-Type", "application/json")
//                        .withBodyFile("json/person.json")));


        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>("body", headers);
        Person person = new Person(1L,"Bruce",Collections.emptyList());


        ResponseEntity<String> response = restTemplate.exchange(this.buildApiMethodUrl(), HttpMethod.GET, entity, String.class, person);
        assertTrue(response.getStatusCode().equals(HttpStatus.OK));
        assertTrue(response.getBody().equals("[\n" +
                "  {\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"Jet\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 2,\n" +
                "    \"name\": \"Alex\"\n" +
                "  }\n" +
                "]"));

        wm.verify(getRequestedFor(urlEqualTo("/person/list"))
                .withHeader("Content-Type", matching(MediaType.APPLICATION_JSON_VALUE))
//                .withRequestBody(matchingJsonPath("$.name", equalTo("Bruce")))
        );

    }

    private String buildApiMethodUrl() {
        return String.format("http://localhost:%d/person/list",
                this.wm.port()
        );
    }

}

