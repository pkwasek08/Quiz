package pl.project.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.project.entities.Answer;

@Repository
public interface AnswerRepository extends CrudRepository<Answer, Integer> {
}
