package pl.project.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.project.entities.Subject;

@Repository
public interface SubjectRepository extends CrudRepository<Subject, Integer> {
}
