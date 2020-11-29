package pl.project.GenerateTask;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenerateTaskRepository extends CrudRepository<GenerateTask, Integer> {
    List<GenerateTask> findAllByGenerateTestsByGenerateTest(Integer id);
}
