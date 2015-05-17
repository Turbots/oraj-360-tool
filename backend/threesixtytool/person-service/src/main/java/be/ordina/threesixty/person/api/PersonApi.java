package be.ordina.threesixty.person.api;

import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import be.ordina.threesixty.person.assemblers.PersonResourceAssembler;
import be.ordina.threesixty.person.business.PasswordHasher;
import be.ordina.threesixty.person.model.Person;
import be.ordina.threesixty.person.repository.PersonRepository;

/**
 * Created by stevedezitter on 14/04/15.
 */
@RestController
@RequestMapping("/persons")
public class PersonApi {

    private PersonRepository personRepository;
    private PersonResourceAssembler personResourceAssembler;
    private PasswordHasher passwordHasher;

    @Autowired
    public PersonApi(PersonRepository personRepository, PersonResourceAssembler personResourceAssembler, PasswordHasher passwordHasher) {
        this.personRepository = personRepository;
        this.personResourceAssembler = personResourceAssembler;
        this.passwordHasher = passwordHasher;
    }

    @RequestMapping(value="", method = RequestMethod.GET)
    public List<Resource<Person>> getAllPersons() {
        Iterable<Person> allPersons = personRepository.findAll();
        List<Resource<Person>> personResourceList = StreamSupport.stream(allPersons.spliterator(), true)
                .map(person -> personResourceAssembler.toResource(person))
                .collect(Collectors.toCollection(ArrayList::new));

        return personResourceList;
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public Person getEmployeeById(@PathVariable String id) {
        return personRepository.findOne(id);
    }

    @RequestMapping(value="", method = RequestMethod.POST)
    public ResponseEntity<Void> createEmployee(@RequestBody Person person) throws URISyntaxException {
        byte[] salt = passwordHasher.generateRandomSalt();
//        byte[] passwordBytes = passwordHasher.getBytesForCharArrayPassword(person.getCredentials().getPassword());
        byte[] passwordBytes = passwordHasher.getBytesForPassword(person.getCredentials().getPassword());

        String base64Password = passwordHasher.generateBase64HashedPasswordForPasswordAndSalt(passwordBytes, salt);
        String base64Salt = passwordHasher.generateBase64SaltForBytes(salt);

        person.getCredentials().setPassword(base64Password);
        person.getCredentials().setSalt(base64Salt);

        Person storedPerson = personRepository.save(person);
        return created(new URI("/persons/" + storedPerson.getId())).build();
    }

    //Consider using PATCH -> See REST in practice page 114
    @RequestMapping(value="", method = RequestMethod.PUT)
    public ResponseEntity<Void> updateEmployee(@RequestBody Person person) {
        personRepository.save(person);
        return ok().build();
    }

}
