package pl.project.GenerateTask;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenerateTaskRepository extends CrudRepository<GenerateTask, Integer> {
}
