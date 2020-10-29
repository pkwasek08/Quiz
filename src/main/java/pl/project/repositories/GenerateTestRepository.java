package pl.project.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.project.entities.GenerateTest;

@Repository
public interface GenerateTestRepository extends CrudRepository<GenerateTest, Integer> {
}
