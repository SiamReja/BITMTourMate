package com.example.siam.bitm_tourmate.ModelClass;

public class Event_Pojo {
    String event_name;
    String start_Location;
    String destination;
    String journey_Date;
    String budget;
    public Event_Pojo(String event_name, String start_Location, String destination, String journey_Date, String budget) {
        this.event_name = event_name;
        this.start_Location = start_Location;
        this.destination = destination;
        this.journey_Date = journey_Date;
        this.budget = budget;
    }

    public Event_Pojo() {
    }

    public String getEvent_name() {
        return event_name;
    }

    public String getStart_Location() {
        return start_Location;
    }

    public String getDestination() {
        return destination;
    }

    public String getJourney_Date() {
        return journey_Date;
    }

    public String getBudget() {
        return budget;
    }
}
