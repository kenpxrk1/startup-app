package ru.crew.event;

import org.springframework.context.ApplicationEvent;
import ru.crew.model.MatchEntity;

public class MatchCreatedEvent extends ApplicationEvent {

    private final MatchEntity match;

    public MatchCreatedEvent(Object source, MatchEntity match) {
        super(source);
        this.match = match;
    }

    public MatchEntity getMatch() {
        return match;
    }
}

