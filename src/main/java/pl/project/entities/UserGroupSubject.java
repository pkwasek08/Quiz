package pl.project.entities;

import javax.persistence.*;

@Entity
@Table(name = "user_group_subject", schema = "public", catalog = "d9h3r67ca39jah")
public class UserGroupSubject {
    private int id;
    private User userByUserId;
    private Group groupByGroupId;
    private Subject subjectBySubjectId;
    private User userByTeacherId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserGroupSubject that = (UserGroupSubject) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    public Group getGroupByGroupId() {
        return groupByGroupId;
    }

    public void setGroupByGroupId(Group groupByGroupId) {
        this.groupByGroupId = groupByGroupId;
    }

    @ManyToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    public Subject getSubjectBySubjectId() {
        return subjectBySubjectId;
    }

    public void setSubjectBySubjectId(Subject subjectBySubjectId) {
        this.subjectBySubjectId = subjectBySubjectId;
    }

    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    public User getUserByTeacherId() {
        return userByTeacherId;
    }

    public void setUserByTeacherId(User userByTeacherId) {
        this.userByTeacherId = userByTeacherId;
    }
}
