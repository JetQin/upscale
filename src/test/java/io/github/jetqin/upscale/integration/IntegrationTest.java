package io.github.jetqin.upscale.integration;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.assertTrue;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class IntegrationTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().port(8089).httpsPort(8443));

    RestTemplate restTemplate;

    ResponseEntity response;

    @Before
    public void setup(){
        restTemplate = new RestTemplate();
        response = null;
    }

    @Test
    public void testListPerson(){
        stubFor(get(urlEqualTo("/person/list"))
                .withHeader("Accept", equalTo("application/json"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("json/person.json")));


        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8089/person/list", HttpMethod.GET, entity, String.class);
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
        verify(getRequestedFor(urlMatching("/person/list"))
                .withHeader("Content-Type", matching("application/json")));
    }

}

