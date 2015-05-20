package be.ordina.threesixty.person;

import static org.hamcrest.Matchers.endsWith;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.RestDocumentation.document;
import static org.springframework.restdocs.hypermedia.HypermediaDocumentation.linkWithRel;
import static org.springframework.restdocs.hypermedia.LinkExtractors.halLinks;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.restdocs.RestDocumentation;
import org.springframework.restdocs.config.RestDocumentationConfigurer;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import be.ordina.threesixty.common.model.Address;
import be.ordina.threesixty.person.config.MongoTestConfiguration;
import be.ordina.threesixty.person.model.Credentials;
import be.ordina.threesixty.person.model.Person;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { PersonServiceApplication.class,
		MongoTestConfiguration.class })
@WebAppConfiguration
@IntegrationTest("server.port=0")
@DirtiesContext
@ActiveProfiles("test")
public class ApplicationDocumentation {

	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext context;
	private Person person;
	private static final ObjectWriter OBJECT_WRITER = new ObjectMapper()
			.setSerializationInclusion(Include.NON_NULL)
			.writer();

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
				.apply(new RestDocumentationConfigurer()).build();
		person = createNewPerson();
	}

	private Person createNewPerson() {
		Person person = new Person();
		person.setFirstName("John");
		person.setLastName("Doe");
		person.setTeam("Ctrl-Alt-Elite");
		Address address = new Address();
		address.setStreet("Doe");
		address.setStreetNumber(11);
		address.setBus("50");
		address.setZipCode("9000");
		address.setCity("Gent");
		address.setProvince("Oost-Vlaanderen");
		address.setCountry("Belgium");
		person.setAddress(address);
		Credentials credentials = new Credentials();
		credentials.setUsername("jodo");
		credentials.setPassword("test123");
		person.setCredentials(credentials);
		// person.setBirthDate;
		// person.setManager;
		// person.setCoach;
		// person.setRole;
		// person.setFocusDomain;
		// person.setCompetenceLeader;
		// person.setTimelineEvents;
		// person.setSatisfactionHistory;
		return person;
	}
	
	@Test
	public void getIndex() throws Exception {
		mockMvc.perform(get("/"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$._links.persons.href", endsWith("persons")))
			.andDo(document("index-get").withLinks(halLinks(),
					linkWithRel("persons").description("The <<resources-persons-get, Persons resource>>"),
					linkWithRel("person").description("The <<resources-person-get, Person resource>>"),
					linkWithRel("createPerson").description("Creating the <<resources-persons-create, Person resource>>"),
					linkWithRel("updatePerson").description("Update the <<resources-persons-get, Person resource>>")));
	}
	
	@Test
	public void postPersonError() throws JsonProcessingException, Exception {
		mockMvc.perform(post("/persons").content("badsyntax}" + OBJECT_WRITER.writeValueAsString(person) + "badsyntax}").contentType(APPLICATION_JSON))
			.andExpect(status().isInternalServerError())
			.andDo(RestDocumentation.document("person-error")
					.withResponseFields(
							fieldWithPath("exceptionType").description("The type of the exception"),
							fieldWithPath("message").description("The message of the exception"),
							fieldWithPath("stackTrace").description("The stacktrace of the exception")
					));
	}

	@Test
	public void postAndGetPerson() throws JsonProcessingException, Exception {
		mockMvc.perform(post("/persons").content(OBJECT_WRITER.writeValueAsString(person)).contentType(APPLICATION_JSON))
			.andExpect(status().isCreated())
			.andDo(RestDocumentation.document("person-create")
					.withRequestFields(
							fieldWithPath("firstName").description("The firstname of the person"),
							fieldWithPath("lastName").description("The lastname of the person"),
							fieldWithPath("credentials").description("Login credentials of the person"),
							fieldWithPath("credentials.username").description("The username of the person"),
							fieldWithPath("credentials.password").description("The password of the person"),
							fieldWithPath("address").description("The address of the person"),
							fieldWithPath("team").description("The team to which the person belongs")
					));
		mockMvc.perform(get("/persons").contentType(APPLICATION_JSON))
			.andExpect(status().isOk())
			.andDo(document("persons-get")
					.withResponseFields(
							fieldWithPath("[].id").description("The id of the person"),
							fieldWithPath("[].firstName").description("The firstname of the person"),
							fieldWithPath("[].lastName").description("The lastname of the person"),
							fieldWithPath("[].credentials").description("Login credentials of the person"),
							fieldWithPath("[].credentials.username").description("The username of the person"),
							fieldWithPath("[].credentials.password").description("The password of the person"),
//							fieldWithPath("[].birthDate").description("The birthdate of the person"),
//							fieldWithPath("[].manager").description("The manager of the person"),
//							fieldWithPath("[].coach").description("The coach of the person"),
//							fieldWithPath("[].role").description("The role of the person"),
//							fieldWithPath("[].focusDomain").description("The focusDomains of the person"),
//							fieldWithPath("[].competenceLeader").description("The competenceLeaders of the person"),
//							fieldWithPath("[].timelineEvents").description("The timelineEvents of the person"),
//							fieldWithPath("[].satisfactionHistory").description("The satisfactionHistory of the person"),
							fieldWithPath("[].address").description("The address of the person"),
							fieldWithPath("[].team").description("The team to which the person belongs"),
							fieldWithPath("[]._links").description("The relations this resource has with other resources")
					));
	}
}
