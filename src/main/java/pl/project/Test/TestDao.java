package pl.project.Test;

import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TestDao {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Test> findAllTestsBySubjectIdAndGroupId(Integer subjectId, Integer groupId) {
        String sql = "select t from Test t inner join Subject s on t.subject.id = s.id inner join UserGroupSubject ugs on s.id = ugs.subject.id where " +
                "s.id = :subjectId and ugs.group.id = :groupId";
        return entityManager.createQuery(sql, Test.class).setParameter("subjectId", subjectId).setParameter("groupId", groupId).getResultList();
    }
}
