package io.github.jetqin.upscale.service;

import io.github.jetqin.upscale.domain.Address;
import io.github.jetqin.upscale.domain.Person;
import io.github.jetqin.upscale.repository.AddressRepository;
import io.github.jetqin.upscale.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    PersonRepository repository;

    @Autowired
    AddressRepository addressRepository;

    public List<Person> listPerson(){
        return repository.findAll();
    }

    public Person getPersonByName(String name){
        return repository.findByName(name);
    }

    public Person update(Long personId){
        Optional<Person> person = repository.findById(personId);
        Address address = getDefaultAddress();
        Person current = person.get();
        current.setAddresses(Collections.singletonList(address));
        address.setPerson(current);
        return repository.save(current);
    }

    public void savePersonWithAddress(Long personId){
        Optional<Person> person = repository.findById(personId);
        Address address = getDefaultAddress();
        Person current = person.get();
        address.setPerson(current);
        current.setAddresses(Collections.singletonList(address));
        repository.saveAll(Collections.singleton(current));
    }


    public Person savePerson(Person person){
        Assert.notNull(person,"Person should not be null");
        return repository.save(person);
    }

    private Address getDefaultAddress(){

        Address address = new Address();
        address.setCity("Xi");
        address.setProvince("SiChuan");
        address.setStreet("Tian Fu 5 Jie");
        return address;
}
}
