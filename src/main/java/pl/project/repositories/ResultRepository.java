package pl.project.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.project.entities.Result;

@Repository
public interface ResultRepository extends CrudRepository<Result, Integer> {
}
