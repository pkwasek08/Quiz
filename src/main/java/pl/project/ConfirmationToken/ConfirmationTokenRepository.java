package pl.project.ConfirmationToken;

import org.springframework.data.repository.CrudRepository;
import pl.project.User.User;

public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, Integer> {
    ConfirmationToken findByConfirmationToken(String confirmationToken);
    ConfirmationToken getByUsersByUserId(User user);
}
