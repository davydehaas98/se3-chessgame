package chessgameshared.messages;

import model.Event;

public class NewEventMessage {
    private Event event;
    public NewEventMessage(Event event) {
        this.event = event;
    }

    public Event getEvent() {
        return event;
    }
}
