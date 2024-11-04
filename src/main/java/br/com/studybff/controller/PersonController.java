package br.com.studybff.controller;

import br.com.studybff.entity.Person;
import br.com.studybff.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    protected PersonRepository personRepository;

    @GetMapping(path = "/bff", produces = "application/json")
    public List<Person> getPersonBFF() {
        return personRepository.getPerson();
    }
}