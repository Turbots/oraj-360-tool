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

	private static final String PERSONS_REL = "employees";
	private static final String EMPLOYEE_REL = "employee";
	private static final String CREATE_EMPLOYEE_REL = "createEmployee";
	private static final String UPDATE_EMPLOYEE_REL = "updateEmployee";

	@RequestMapping("/")
	public HttpEntity<ResourceSupport> getIndex() throws URISyntaxException {
		ResourceSupport resource = new ResourceSupport();
		resource.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(PersonApi.class).getAllPersons()).withRel(PERSONS_REL));
		resource.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(PersonApi.class).getEmployeeById("testId")).withRel(EMPLOYEE_REL));
		resource.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(PersonApi.class).createEmployee(null)).withRel(CREATE_EMPLOYEE_REL));
		resource.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(PersonApi.class).updateEmployee(null)).withRel(UPDATE_EMPLOYEE_REL));
		return ok().body(resource);
	}
}
