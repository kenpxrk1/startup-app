package ru.crew.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.crew.model.constant.IntentContextType;
import ru.crew.model.constant.IntentStatus;
import ru.crew.model.constant.TimeSlot;

import java.time.LocalDate;

@Entity
@Table(name = "intents")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class IntentEntity extends BaseEntity {

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private UserEntity user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IntentContextType contextType;

    @ManyToOne(fetch = FetchType.EAGER)
    private EventEntity event;

    @ManyToOne(fetch = FetchType.EAGER)
    private PlaceEntity place;

    @Column(nullable = false)
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TimeSlot timeSlot;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private IntentStatus status;
}

