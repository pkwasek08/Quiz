package pl.project.GenerateTest;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenerateTestRepository extends CrudRepository<GenerateTest, Integer> {
}
