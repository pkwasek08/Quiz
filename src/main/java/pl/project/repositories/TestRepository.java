package pl.project.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.project.entities.Test;

import java.util.List;

@Repository
public interface TestRepository extends CrudRepository<Test, Integer> {
    List<Test> findAllBySubjectBySubjectId_Id(Integer subjectId);
}
