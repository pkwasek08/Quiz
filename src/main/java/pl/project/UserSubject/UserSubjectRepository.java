package pl.project.UserSubject;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.project.User.User;

import java.util.List;

@Repository
public interface UserSubjectRepository extends CrudRepository<UserSubject, Integer> {
    List<UserSubject> findAllByUserByUserId(User user);
}
