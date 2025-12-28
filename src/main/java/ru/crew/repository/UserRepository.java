package ru.crew.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.crew.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
