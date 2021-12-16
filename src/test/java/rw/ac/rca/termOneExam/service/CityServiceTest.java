package rw.ac.rca.termOneExam.service;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;
import rw.ac.rca.termOneExam.domain.City;
import rw.ac.rca.termOneExam.dto.CreateCityDTO;
import rw.ac.rca.termOneExam.repository.ICityRepository;
import rw.ac.rca.termOneExam.utils.APICustomResponse;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class CityServiceTest {

    @Mock
    ICityRepository cityRepositoryMock;

    @InjectMocks
    CityService cityService;

    @Test
    public void getById_success() {
        when(cityRepositoryMock.findById(any())).thenReturn(
                Optional.of(new City(106,"Karongi",30,90)
                ));
        assertEquals("Karongi", cityService.getById(106).get().getName());
    }

    @Test
    public void getById_notfound() {
        when(cityRepositoryMock.findById(any())).thenReturn(Optional.empty());
        assertFalse(cityService.getById(90).isPresent());
    }

    @Test
    public void getAll_success() {
        when(cityRepositoryMock.findAll()).thenReturn(
                Arrays.asList(
                        new City(105,"Kigali",34, 23),
                        new City(105,"Musanze",56,23)
                )
        );
        assertEquals("Kigali", cityService.getAll().get(0).getName());
    }

    @Test
    public void existsByName_success() {
            when(cityRepositoryMock.existsByName(anyString())).thenReturn(true);
            assertEquals(true, cityService.existsByName("Musanze"));
    }

    @Test
    public void existsByName_notfound() {
        when(cityRepositoryMock.existsByName(anyString())).thenReturn(false);
        assertEquals(false, cityService.existsByName("Musanze"));
    }

    @Test
    public void save_success() {
        CreateCityDTO dto = new CreateCityDTO("Rusizi",40);
        City city = new City(105,"Karongi",43,23);
        when(cityRepositoryMock.save(any(City.class))).thenReturn(city);
        assertEquals(43, cityService.save(dto).getWeather(), 0);
    }

    @Test
    public void save_fail(){
        City city = new City ();
        CreateCityDTO dto = new CreateCityDTO ("Kayonza",23);
        when(cityRepositoryMock.save(city)).thenReturn(city);
        assertEquals(null, cityService.save(dto));
    }

}
