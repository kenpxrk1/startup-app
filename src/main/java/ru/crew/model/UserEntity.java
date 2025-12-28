package ru.crew.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private Long telegramUserId;

    private String username;

    private String firstName;

    private String lastName;

    private String language;

    @Column(nullable = true)
    private Instant lastActiveAt;
}
