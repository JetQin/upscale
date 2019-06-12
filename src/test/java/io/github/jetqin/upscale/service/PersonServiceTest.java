package io.github.jetqin.upscale.service;

import io.github.jetqin.upscale.domain.Person;
import io.github.jetqin.upscale.repository.PersonRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;

import static junit.framework.TestCase.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTest {

    @Mock
    PersonRepository repository;

    @InjectMocks
    PersonService personService;

    @Before
    public void init(){

    }

    @Test
    public void test_get_person_by_name(){

        when(repository.findByName("test")).thenReturn(new Person(1L, "TEST", Collections.emptyList()));
        Person person =  personService.getPersonByName("test");
        assertEquals(1L, person.getId().longValue());
        assertTrue(person.getName().equals("TEST"));

    }
}
