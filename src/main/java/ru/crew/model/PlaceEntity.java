package ru.crew.model;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.crew.model.constant.PlaceType;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "places")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PlaceEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PlaceType type;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String description;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "place_photos", joinColumns = @JoinColumn(name = "place_id"))
    private List<String> photos = new ArrayList<>();
}
