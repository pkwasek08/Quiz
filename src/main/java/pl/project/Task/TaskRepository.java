package pl.project.Task;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task, Integer> {
    List<Task> findAllByTestByTestId_Id(Integer testId);
    List<Task> findAllByTestByTestId_IdAndTypeNotLike(Integer testId, String type);
    List<Task> findAllByTestByTestId_IdAndType(Integer testId, String type);

}
