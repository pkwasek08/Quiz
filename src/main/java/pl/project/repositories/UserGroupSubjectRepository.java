package pl.project.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.project.entities.User;
import pl.project.entities.UserGroupSubject;

import java.util.List;

@Repository
public interface UserGroupSubjectRepository extends CrudRepository<UserGroupSubject, Integer> {
    List<UserGroupSubject> getAllByUserByUserId(User user);
    List<UserGroupSubject> getAllByUserByTeacherId(User user);
}
