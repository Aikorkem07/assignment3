package repositories;

import entities.Event;

public interface EventRepository {
    void create(Event event);
    boolean isCancelled(int eventId);
}
