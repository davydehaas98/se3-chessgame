package chessgameshared.messages;

import model.Event;

import java.util.List;

public class EventsMessage {
    private List<Event> events;

    public EventsMessage(List<Event> events) {
        this.events = events;
    }

    public List<Event> getEvents() {
        return events;
    }
}
