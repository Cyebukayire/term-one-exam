package rw.ac.rca.termOneExam.controller;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import rw.ac.rca.termOneExam.domain.City;
import rw.ac.rca.termOneExam.dto.CreateCityDTO;
import rw.ac.rca.termOneExam.utils.APICustomResponse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CityControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getById_success() throws JSONException {
        ResponseEntity<String> response = this.restTemplate.getForEntity("/api/cities/id/104", String.class);
        assertTrue(response.getStatusCode().is2xxSuccessful());
        JSONAssert.assertEquals("{\"id\":104,\"name\":\"Nyagatare\",\"weather\":28,\"fahrenheit\":0}",
                response.getBody(), false);
    }

    @Test
    public void getById_notfound() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/cities/id/900", String.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void getAll_success() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/cities/all", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }


    @Test
    public void saveItem_success(){
        City city =  new City("Nyanza",25);
        ResponseEntity<City> response = this.restTemplate.postForEntity("/api/cities/add", city, City.class);
        assertEquals(201, response.getStatusCodeValue());
//        assertEquals(21, response.getStatusCodeValue());
    }

    @Test
    public void saveItem_badRequest(){
        CreateCityDTO cityDTO = new CreateCityDTO("Kigali", 20);
        APICustomResponse customResponse = new APICustomResponse(false, "City name Kigali is registered already");
        ResponseEntity<APICustomResponse> response = this.restTemplate.postForEntity("/api/cities/add", cityDTO, APICustomResponse.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(customResponse.getMessage(), response.getBody().getMessage());
    }

}