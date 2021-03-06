package pl.project.UserGroupSubject;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.project.User.User;
import java.util.List;

@Repository
public interface UserGroupSubjectRepository extends CrudRepository<UserGroupSubject, Integer> {
    List<UserGroupSubject> getAllByUser(User user);
    List<UserGroupSubject> getAllByUserTeacher(User user);
}
