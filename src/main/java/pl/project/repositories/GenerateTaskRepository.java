package pl.project.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.project.entities.GenerateTask;

@Repository
public interface GenerateTaskRepository extends CrudRepository<GenerateTask, Integer> {
}