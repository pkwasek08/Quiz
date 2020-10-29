package pl.project.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.project.entities.UserGroupSubject;

@Repository
public interface UserGroupSubjectRepository extends CrudRepository<UserGroupSubject, Integer> {
}
