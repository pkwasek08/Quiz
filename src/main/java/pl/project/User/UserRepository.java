package pl.project.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    Boolean existsByLogin(String login);
    Boolean existsByEmail(String email);
    Boolean existsByLoginAndEnabled(String login, Boolean enabled);
    Optional<User> findByLogin(String login);
    User findByEmailIgnoreCase(String email);
}
