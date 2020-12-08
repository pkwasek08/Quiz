package pl.project.Result;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends CrudRepository<Result, Integer> {
    List<Result> findAllByUser_Id(Integer userId);
    List<Result> findAllByUser_IdAndPointsIsNotNull(Integer userId);
    List<Result> findAllByUser_IdAndPointsIsNull(Integer userId);
    List<Result> findAllByGenerateTest_Test_Subject_Id(Integer subjectId);
    List<Result> findAllByUser_IdAndGenerateTest_Test_Id(Integer userId, Integer testId);
    List<Result> findAllByUser_IdAndGenerateTest_Id(Integer userId, Integer generateTestId);
    List<Result> findAllByUser_IdAndGenerateTest_Test_IdAndPointsIsNotNull(Integer userId, Integer testId);
    List<Result> findAllByUser_IdAndPointsIsNullAndGenerateTest_Test_Subject_Id(Integer userId, Integer subjectId);
    List<Result> findAllByUser_IdAndPointsIsNotNullAndGenerateTest_Test_Subject_Id(Integer userId, Integer subjectId);
}
