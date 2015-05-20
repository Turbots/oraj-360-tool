package be.ordina.threesixty.person.api;

import static org.springframework.http.ResponseEntity.ok;

import java.net.URISyntaxException;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexApi {

	private static final String PERSONS_REL = "persons";
	private static final String PERSON_REL = "person";
	private static final String CREATE_PERSON_REL = "createPerson";
	private static final String UPDATE_PERSON_REL = "updatePerson";

	@RequestMapping("/")
	public HttpEntity<ResourceSupport> getIndex() throws URISyntaxException {
		ResourceSupport resource = new ResourceSupport();
		resource.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(PersonApi.class).getAllPersons()).withRel(PERSONS_REL));
		resource.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(PersonApi.class).getPersonById("testId")).withRel(PERSON_REL));
		resource.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(PersonApi.class).createPerson(null)).withRel(CREATE_PERSON_REL));
		resource.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(PersonApi.class).updatePerson(null)).withRel(UPDATE_PERSON_REL));
		return ok().body(resource);
	}
}
