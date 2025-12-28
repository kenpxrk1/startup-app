package ru.crew.event;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.crew.kafka.KafkaSender;
import ru.crew.model.MatchEntity;

@Component
@Slf4j
@RequiredArgsConstructor
public class MatchEventListener {
    private final KafkaSender kafkaSender;

    @EventListener
    public void handleMatchCreated(MatchCreatedEvent event) {
        MatchEntity match = event.getMatch();
        log.info("New match event handling: {}", match);
        kafkaSender.send(); // SENDER MOCKED NOW
    }
}
