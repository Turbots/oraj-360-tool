package be.ordina.threesixty.timeline.api;

import be.ordina.threesixty.timeline.model.*;
import be.ordina.threesixty.timeline.repository.TimelineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by stevedezitter on 21/04/15.
 */
@RestController
@RequestMapping("/timeline")
public class TimelineApi {

    private TimelineRepository timelineRepository;

    @Autowired
    public TimelineApi(TimelineRepository timelineRepository) {
        this.timelineRepository = timelineRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<TimelineEvent> getAllTimelineEvents() {
//        List<TimelineEvent> events = new ArrayList<>();
//
//        FollowUpMeeting event1 = new FollowUpMeeting();
//        Course event2 = new Course();
//        Satisfaction event3 = new Satisfaction();
//
//        event1.setOwner("idOfUser");
//        event1.setDescription("follow up meeting with unit management");
//        event1.setEventDate(LocalDate.now());
//        event1.setAttendees(Arrays.asList("personBUM1", "personRM1"));
//        event1.setMeetingMinutes("Evaluation talk with unit management for past year (2014)");
//        event1.setTimelineEventType(TimelineEventType.FOLLOW_UP_MEETING);
//
//        event2.setOwner("idOfUser2");
//        event2.setDescription("coursera android course");
//        event2.setEventDate(LocalDate.of(2015, 03, 28));
//        event2.setCourseName("Android programming");
//        event2.setCourseDescription("Introductory MOOC on android programming by Vanderbilt University");
//        event2.setCourseStatus(TimelineEventStatus.ONGOING);
//        event2.setWithCertificate(true);
//        event2.setCertificateEarned(false);
//        event2.setTimelineEventType(TimelineEventType.COURSE);
//
//        event3.setOwner("idOfUser3");
//        event3.setDescription("satisfaction event");
//        event3.setEventDate(LocalDate.of(2015,03,31));
//        event3.setSatisfaction("Unhappy");
//        event3.setTimelineEventType(TimelineEventType.SATISFACTION);
//
//        events.add(event1);
//        events.add(event2);
//        events.add(event3);
//
//        return events;
        Iterable<TimelineEvent> allTimelineEvents = timelineRepository.findAll();
        return StreamSupport.stream(allTimelineEvents.spliterator(), true).collect(Collectors.toCollection(ArrayList::new));
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public void createTimelineEvent(@RequestBody TimelineEvent timelineEvent) {
        timelineRepository.save(timelineEvent);
    }

}
