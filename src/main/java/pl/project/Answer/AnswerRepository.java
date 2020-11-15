package pl.project.Answer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.project.Task.Task;

import java.util.List;

@Repository
public interface AnswerRepository extends CrudRepository<Answer, Integer> {
    List<Answer> findAllByTasksByTaskId(Task task);
}
