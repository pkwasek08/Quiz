package pl.project.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.project.entities.User;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    Boolean existsByLogin(String login);
    Boolean existsByEmail(String email);
    Optional<User> findByLogin(String login);
}
