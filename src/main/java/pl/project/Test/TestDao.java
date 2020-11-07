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
        String sql = "select t from Test t inner join Subject s on t.subjectBySubjectId.id = s.id inner join UserGroupSubject ugs on s.id = ugs.subjectBySubjectId.id where " +
                "s.id = :subjectId and ugs.groupByGroupId.id = :groupId";
        return entityManager.createQuery(sql, Test.class).setParameter("subjectId", subjectId).setParameter("groupId", groupId).getResultList();
    }
}
