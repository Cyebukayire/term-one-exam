package rw.ac.rca.termOneExam.integration;

//import org.junit.jupiter.api.Test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import rw.ac.rca.termOneExam.domain.City;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CityIntegrationTest {

    private TestRestTemplate restTemplate;

    @Test
    public void getById_success() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/cities/id/104", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void findById_testNotFound() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/cities/id/1", String.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void getAll_testSuccess() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/cities/all", String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void existsByName_success() {

    }

//    @Test
//    public void create_testDuplicateMobilePhone() {
//        Contact theContact = new Contact(1000L, "Kalisa", "0788868881");
//        ResponseEntity<String> response = restTemplate.postForEntity("/api/contacts", theContact, String.class);
//
//        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//        assertEquals("Mobile phone already taken", response.getBody());
//    }

}
