package ru.crew.mapper;

import org.springframework.stereotype.Component;
import ru.crew.dto.profile.ProfileCreateRequest;
import ru.crew.dto.profile.ProfileResponse;
import ru.crew.dto.profile.ProfileUpdateRequest;
import ru.crew.model.ProfileEntity;
import ru.crew.model.UserEntity;

@Component
public class ProfileMapper {

    public ProfileEntity toEntity(ProfileCreateRequest r, UserEntity user) {
        ProfileEntity p = new ProfileEntity();
        p.setUser(user);
        p.setName(r.name());
        p.setBio(r.bio());
        p.setAge(r.age());
        p.setCity(r.city());
        p.setInterests(r.interests());
        p.setPhotos(r.photos());
        p.setShowIntents(r.showIntents());
        return p;
    }

    public void updateEntity(ProfileUpdateRequest r, ProfileEntity p) {
        if (r.name() != null) p.setName(r.name());
        if (r.bio() != null) p.setBio(r.bio());
        if (r.age() != null) p.setAge(r.age());
        if (r.city() != null) p.setCity(r.city());
        if (r.interests() != null) p.setInterests(r.interests());
        if (r.photos() != null) p.setPhotos(r.photos());
        if (r.showIntents() != null) p.setShowIntents(r.showIntents());
    }

    public ProfileResponse toResponse(ProfileEntity p) {
        return new ProfileResponse(
                p.getId(),
                p.getUser().getId(),
                p.getName(),
                p.getBio(),
                p.getAge(),
                p.getCity(),
                p.getInterests(),
                p.getPhotos(),
                p.getShowIntents(),
                p.getCreatedAt(),
                p.getUpdatedAt()
        );
    }
}


