package be.ordina.threeSixty.person.api.resourceAssemblers;

import be.ordina.threeSixty.person.api.PersonApi;
import be.ordina.threeSixty.person.model.Person;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by stevedezitter on 22/04/15.
 */
@Component
public class PersonResourceAssembler implements ResourceAssembler<Person, Resource<Person>>{

    @Override
    public Resource<Person> toResource(Person person) {
        Resource<Person> personResource = new Resource<Person>(person);

        if(person.getManager()!=null) {
            person.getManager().forEach(managerId -> personResource.add(linkTo(PersonApi.class).slash(managerId).withRel("manager")));
        }
        personResource.add(linkTo(PersonApi.class).slash(person.getId()).withSelfRel());

        return personResource;
    }
}
