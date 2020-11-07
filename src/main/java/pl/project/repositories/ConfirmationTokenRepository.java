package pl.project.repositories;

import org.springframework.data.repository.CrudRepository;
import pl.project.entities.ConfirmationToken;

public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, Integer> {
}
