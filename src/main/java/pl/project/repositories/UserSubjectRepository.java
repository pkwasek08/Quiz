package pl.project.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.project.entities.UserSubject;

@Repository
public interface UserSubjectRepository extends CrudRepository<UserSubject, Integer> {
}
