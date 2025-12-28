package ru.crew.model;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "profiles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProfileEntity extends BaseEntity {

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private UserEntity user;

    @Column(nullable = false)
    private String name;

    private String bio;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private String city;

    @ElementCollection
    @CollectionTable(name = "profile_interests", joinColumns = @JoinColumn(name = "profile_id"))
    private List<String> interests;

    @ElementCollection
    @CollectionTable(name = "profile_photos", joinColumns = @JoinColumn(name = "profile_id"))
    private List<String> photos;

    @Column(nullable = false)
    private Boolean showIntents;

    // getters/setters
}

