package ru.crew.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
        name = "likes",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"from_user_id", "to_user_id", "intent_id"}
        )
)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LikeEntity extends BaseEntity {

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "from_user_id")
    private UserEntity fromUser;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "to_user_id")
    private UserEntity toUser;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private IntentEntity intent;
}
