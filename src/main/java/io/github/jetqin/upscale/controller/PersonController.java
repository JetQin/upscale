package io.github.jetqin.upscale.controller;

import io.github.jetqin.upscale.annotation.ResponseCommon;
import io.github.jetqin.upscale.domain.Person;
import io.github.jetqin.upscale.service.PersonService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
@Api(value="person manager", tags={"Operations for person"})
public class PersonController {

    @Autowired
    PersonService service;

    @GetMapping(value="/list",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "View a list of available person", response = Person.class, responseContainer = "List")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully retrieve person"),
    })
    @ResponseCommon
    public List<Person> listPerson(){
        return service.listPerson();
    }

    @GetMapping(value="/get/{name}", produces= MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get a person by name", response = Person.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully get person", response = Person.class),
    })
    @ResponseCommon
    @ResponseBody
    public Person getPersonByName(@ApiParam("person name") @PathVariable String name){
       return service.getPersonByName(name);
    }


    @PutMapping(value="/update/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update all person name", response = Void.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully update person", response = Person.class),
    })
    @ResponseCommon
    public Person update(@PathVariable Long id){
         return service.update(id);
    }

    @PostMapping(value="/save/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Save person with address", response = Void.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully save person with address", response = Void.class),
    })
    @ResponseCommon
    public void savePersonWithAddress(@ApiParam("person id") @PathVariable Long id) {
        service.savePersonWithAddress(id);
    }


    @PostMapping(value="/create",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create person", response = Person.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully create person"),
    })
    @ResponseCommon
    public Person createPerson(@RequestBody Person person){
        return service.savePerson(person);
    }

}
