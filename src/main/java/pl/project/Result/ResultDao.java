package pl.project.Result;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ResultDao {
    @PersistenceContext
    private EntityManager entityManager;

    List<Result> getAllResultByTeacherIdAndGroupIdAndSubjectIdAndMarkNotNull(Integer teacherId, Integer groupId, Integer subjectId) {
        String sql = "SELECT r FROM Result r INNER JOIN UserGroupSubject ugs ON r.user.id = ugs.id WHERE r.user.id = :teacherId AND ugs.subject.id = :subjectId " +
                " AND ugs.group.id = :groupId AND ugs.userTeacher.id = :teacherId AND r.mark != NULL";
        return entityManager.createQuery(sql, Result.class).setParameter("teacherId", teacherId).setParameter("subjectId", subjectId).
                setParameter("groupId", groupId).getResultList();
    }

    List<Result> getAllResultByTeacherIdAndGroupIdAndSubjectIdAndMarkIsNull(Integer teacherId, Integer groupId, Integer subjectId) {
        String sql = "SELECT r FROM Result r INNER JOIN UserGroupSubject ugs ON r.user.id = ugs.id WHERE r.user.id = :teacherId AND ugs.subject.id = :subjectId " +
                " AND ugs.group.id = :groupId AND ugs.userTeacher.id = :teacherId AND r.mark = NULL";
        return entityManager.createQuery(sql, Result.class).setParameter("teacherId", teacherId).setParameter("subjectId", subjectId).
                setParameter("groupId", groupId).getResultList();
    }
    /*List<Result> getAllResultByTeacherIdTestIdAndGroupId(Integer teacherId, Integer testId, Integer groupId) {
        String sql = "SELECT r FROM Result r INNER JOIN UserGroupSubject ugs ON ugs.user.id = r.user.id WHERE " +
                " ugs.group.id = :groupId AND ugs.group.founder.id = :teacherId AND r.generateTest.test.id = :testId";
        return entityManager.createQuery(sql, Result.class).setParameter("testId", testId).setParameter("teacherId", teacherId).
                setParameter("groupId", groupId).getResultList();
    }*/

}
