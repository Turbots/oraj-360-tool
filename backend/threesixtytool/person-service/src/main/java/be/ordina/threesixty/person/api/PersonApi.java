package be.ordina.threesixty.person.api;

import be.ordina.threesixty.person.assemblers.PersonResourceAssembler;
import be.ordina.threesixty.person.business.PasswordHasher;
import be.ordina.threesixty.person.model.Person;
import be.ordina.threesixty.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;

/**
 * API for CRUD operations on {@link Person}s.
 * Created by stevedezitter on 14/04/15.
 */
@RestController
@RequestMapping("/persons")
public class PersonApi {

    final private PersonRepository personRepository;
    final private PersonResourceAssembler personResourceAssembler;
    final private PasswordHasher passwordHasher;

    @Autowired
    public PersonApi(PersonRepository personRepository, PersonResourceAssembler personResourceAssembler, PasswordHasher passwordHasher) {
        this.personRepository = personRepository;
        this.personResourceAssembler = personResourceAssembler;
        this.passwordHasher = passwordHasher;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Resource<Person>> getAllPersons() {
        Iterable<Person> allPersons = personRepository.findAll();
        return StreamSupport.stream(allPersons.spliterator(), true)
                .map(personResourceAssembler::toResource)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Person getPersonById(@PathVariable String id) {
        return personRepository.findOne(id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Void> createPerson(@RequestBody Person person) throws URISyntaxException {
        byte[] salt = passwordHasher.generateRandomSalt();
        byte[] passwordBytes = passwordHasher.getBytesForPassword(person.getCredentials().getPassword());

        String base64Password = passwordHasher.generateBase64HashedPasswordForPasswordAndSalt(passwordBytes, salt);
        String base64Salt = passwordHasher.generateBase64SaltForBytes(salt);

        person.getCredentials().setPassword(base64Password);
        person.getCredentials().setSalt(base64Salt);

        Person storedPerson = personRepository.save(person);
        return created(new URI("/persons/" + storedPerson.getId())).build();
    }

    //Consider using PATCH -> See REST in practice page 114
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public ResponseEntity<Void> updatePerson(@RequestBody Person person) {
        personRepository.save(person);
        return ok().build();
    }

}
