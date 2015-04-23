package be.ordina.threeSixty.timeline.model;

import java.util.List;

/**
 * Created by stevedezitter on 22/04/15.
 */
public class FollowUpMeeting extends TimelineEvent {

    public List<String> attendees;

    public String meetingMinutes;

    public List<String> getAttendees() {
        return attendees;
    }

    public void setAttendees(List<String> attendees) {
        this.attendees = attendees;
    }

    public String getMeetingMinutes() {
        return meetingMinutes;
    }

    public void setMeetingMinutes(String meetingMinutes) {
        this.meetingMinutes = meetingMinutes;
    }
}
