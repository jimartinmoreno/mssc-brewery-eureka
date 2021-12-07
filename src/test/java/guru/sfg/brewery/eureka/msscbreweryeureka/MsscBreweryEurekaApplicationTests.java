package guru.sfg.brewery.eureka.msscbreweryeureka;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MsscBreweryEurekaApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Value("${spring.security.user.name:netflix}")
    private String user;

    @Value("${spring.security.user.password:eureka}")
    private String password;

    @Test
    public void shouldStartEurekaServer() {
        //        MultiValueMap<String, String> request = new LinkedMultiValueMap<String, String>();
        //        request.set("username", "netflix");
        //        request.set("password", "eureka");
        //        request.set("grant_type", "password");
        System.out.println("port = " + port);
        System.out.println("user = " + user);
        System.out.println("password = " + password);

        ResponseEntity<String> entity = this.testRestTemplate.withBasicAuth(user, password)
                .getForEntity("http://localhost:" + this.port + "/eureka/apps", String.class);
                // .postForObject(SyntheticsConstants.OAUTH_ENDPOINT, request, Map.class);

        then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}
