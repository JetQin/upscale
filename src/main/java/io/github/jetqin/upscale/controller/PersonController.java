package io.github.jetqin.upscale.controller;

import io.github.jetqin.upscale.domain.Person;
import io.github.jetqin.upscale.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/person")
@Api(value="person manager", tags={"Operations for person"})
public class PersonController {

    @Autowired
    PersonService service;

    @GetMapping(value="/list",produces = "application/json")
    @ApiOperation(value = "View a list of available person", response = Person.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successfully retrieve person"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })

    public List<Person> listPerson(){
        return service.listPerson();
    }
}
