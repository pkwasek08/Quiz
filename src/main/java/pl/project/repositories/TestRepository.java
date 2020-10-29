package pl.project.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.project.entities.Test;

@Repository
public interface TestRepository extends CrudRepository<Test, Integer> {
}
