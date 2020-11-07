package pl.project.UserSubject;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSubjectRepository extends CrudRepository<UserSubject, Integer> {
}
