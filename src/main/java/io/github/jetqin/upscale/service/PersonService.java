package io.github.jetqin.upscale.service;

import io.github.jetqin.upscale.domain.Person;
import io.github.jetqin.upscale.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    PersonRepository repository;

    public List<Person> listPerson(){
        return repository.findAll();
    }

    public Person getPersonByName(String name){
        return repository.findByName(name);
    }
}
