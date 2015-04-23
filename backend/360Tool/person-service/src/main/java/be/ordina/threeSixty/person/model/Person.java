package be.ordina.threeSixty.person.model;

import be.ordina.threeSixty.common.model.Address;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by stevedezitter on 14/04/15.
 */
@Document
public class Person {

    @Id
    private String id;
    @Indexed
    private String firstName;
    @Indexed
    private String lastName;

    private Credentials credentials;

    private LocalDate birthDate;
    private Address address;

    //Contains objectId linked to person
    private List<String> manager;
    //Contains objectId linked to person
    private List<String> coach;

    private Role role;
    //Oraj, MS, etc...
    private String team;
    private List<String> focusDomain;
    private List<String> competenceLeader;

    private List<String> timelineEvents;
    private List<String> satisfactionHistory;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<String> getManager() {
        return manager;
    }

    public void setManager(List<String> manager) {
        this.manager = manager;
    }

    public List<String> getCoach() {
        return coach;
    }

    public void setCoach(List<String> coach) {
        this.coach = coach;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public List<String> getFocusDomain() {
        return focusDomain;
    }

    public void setFocusDomain(List<String> focusDomain) {
        this.focusDomain = focusDomain;
    }

    public List<String> getCompetenceLeader() {
        return competenceLeader;
    }

    public void setCompetenceLeader(List<String> competenceLeader) {
        this.competenceLeader = competenceLeader;
    }

    public List<String> getTimelineEvents() {
        return timelineEvents;
    }

    public void setTimelineEvents(List<String> timelineEvents) {
        this.timelineEvents = timelineEvents;
    }

    public List<String> getSatisfactionHistory() {
        return satisfactionHistory;
    }

    public void setSatisfactionHistory(List<String> satisfactionHistory) {
        this.satisfactionHistory = satisfactionHistory;
    }
}
