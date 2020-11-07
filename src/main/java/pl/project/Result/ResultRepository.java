package pl.project.Result;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.project.User.User;

import java.util.List;

@Repository
public interface ResultRepository extends CrudRepository<Result, Integer> {
    List<Result> findAllByUserByUserId(User user);
}
