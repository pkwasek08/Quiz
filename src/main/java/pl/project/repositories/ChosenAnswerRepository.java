package pl.project.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.project.entities.ChosenAnswer;

@Repository
public interface ChosenAnswerRepository extends CrudRepository<ChosenAnswer, Integer> {
}
