package be.ordina.threesixty.timeline.api;

import static org.springframework.http.ResponseEntity.ok;

import java.net.URISyntaxException;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexApi {

	private static final String EVENTS_REL = "events";
	private static final String CREATE_EVENT_REL = "createEvent";

	@RequestMapping("/")
	public HttpEntity<ResourceSupport> getIndex() throws URISyntaxException {
		ResourceSupport resource = new ResourceSupport();
		resource.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(TimelineApi.class).getAllTimelineEvents()).withRel(EVENTS_REL));
		resource.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(TimelineApi.class).createTimelineEvent(null)).withRel(CREATE_EVENT_REL));
		return ok().body(resource);
	}
}
