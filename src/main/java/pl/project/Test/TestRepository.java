package pl.project.Test;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TestRepository extends CrudRepository<Test, Integer> {
    List<Test> findAllBySubject_Id(Integer subjectId);
}
