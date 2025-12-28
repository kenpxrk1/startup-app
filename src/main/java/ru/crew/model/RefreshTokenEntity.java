package ru.crew.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "refresh_tokens")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RefreshTokenEntity {

    @Id
    private String token;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity user;

    private Instant expiresAt;
}
