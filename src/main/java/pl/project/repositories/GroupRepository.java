package pl.project.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.project.entities.Group;

@Repository
public interface GroupRepository extends CrudRepository<Group, Integer> {
}
