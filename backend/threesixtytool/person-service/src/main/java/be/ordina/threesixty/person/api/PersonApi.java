package be.ordina.threesixty.person.api;

import be.ordina.threesixty.person.api.resourceAssemblers.PersonResourceAssembler;
import be.ordina.threesixty.person.business.PasswordHasher;
import be.ordina.threesixty.person.model.Person;
import be.ordina.threesixty.person.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
    public void createEmployee(@RequestBody Person person) {
        byte[] salt = passwordHasher.generateRandomSalt();
//        byte[] passwordBytes = passwordHasher.getBytesForCharArrayPassword(person.getCredentials().getPassword());
        byte[] passwordBytes = passwordHasher.getBytesForPassword(person.getCredentials().getPassword());

        String base64Password = passwordHasher.generateBase64HashedPasswordForPasswordAndSalt(passwordBytes, salt);
        String base64Salt = passwordHasher.generateBase64SaltForBytes(salt);

        person.getCredentials().setPassword(base64Password);
        person.getCredentials().setSalt(base64Salt);

        personRepository.save(person);
    }

    //Consider using PATCH -> See REST in practice page 114
    @RequestMapping(value="", method = RequestMethod.PUT)
    public void updateEmployee(@RequestBody Person person) {
        personRepository.save(person);
    }

}
