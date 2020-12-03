package pl.project.User;

import org.springframework.stereotype.Repository;
import pl.project.Test.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    public List<User> findAllUsersBySubjectId(Integer subjectId) {
        String sql = "select u from User u inner join UserGroupSubject ugs on u.id = ugs.user.id where " +
                "ugs.subject.id = :subjectId";
        return entityManager.createQuery(sql, User.class).setParameter("subjectId", subjectId).getResultList();
    }
}
