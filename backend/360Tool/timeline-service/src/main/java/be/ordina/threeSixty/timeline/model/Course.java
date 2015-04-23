package be.ordina.threeSixty.timeline.model;

/**
 * Created by stevedezitter on 22/04/15.
 */
public class Course extends TimelineEvent{

    private String courseName;
    private String courseDescription;

    private TimelineEventStatus courseStatus;
    private boolean withCertificate;
    private boolean certificateEarned;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public TimelineEventStatus getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(TimelineEventStatus courseStatus) {
        this.courseStatus = courseStatus;
    }

    public boolean isWithCertificate() {
        return withCertificate;
    }

    public void setWithCertificate(boolean withCertificate) {
        this.withCertificate = withCertificate;
    }

    public boolean isCertificateEarned() {
        return certificateEarned;
    }

    public void setCertificateEarned(boolean certificateEarned) {
        this.certificateEarned = certificateEarned;
    }
}
