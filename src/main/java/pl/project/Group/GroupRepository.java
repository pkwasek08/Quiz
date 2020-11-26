package pl.project.Group;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends CrudRepository<Group, Integer> {
    List<Group> findAllByFounderId(Integer id);
}
