package com.rep.api.event;

public interface EventService {

    void save(Event event);

    void addUser(long eventId, Long userId);
}
