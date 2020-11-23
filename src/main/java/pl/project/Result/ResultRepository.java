package pl.project.Result;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.project.User.User;

import java.util.List;

@Repository
public interface ResultRepository extends CrudRepository<Result, Integer> {
    List<Result> findAllByUser_Id(Integer userId);
    List<Result> findAllByUser_IdAndMarkIsNullAndGenerateTest_Test_Subject_Id(Integer userId, Integer subjectId);
    List<Result> findAllByUser_IdAndMarkIsNotNullAndGenerateTest_Test_Subject_Id(Integer userId, Integer subjectId);
}
