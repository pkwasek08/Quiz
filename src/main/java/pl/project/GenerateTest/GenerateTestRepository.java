package pl.project.GenerateTest;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenerateTestRepository extends CrudRepository<GenerateTest, Integer> {
    @Query(value = "SELECT testByTestId.fullPoints from GenerateTest where id=:generateTestId")
    int getFullPoints(Integer generateTestId);
}
