package me.timur.travelcompanyapp.repository;

import me.timur.travelcompanyapp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by Temurbek Ismoilov on 05/02/22.
 */

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
