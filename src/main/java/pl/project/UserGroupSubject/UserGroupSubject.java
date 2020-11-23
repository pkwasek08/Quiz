package pl.project.UserGroupSubject;

import pl.project.Group.Group;
import pl.project.Subject.Subject;
import pl.project.User.User;

import javax.persistence.*;

@Entity
@Table(name = "user_group_subject", schema = "public", catalog = "d9h3r67ca39jah")
public class UserGroupSubject {
    private int id;
    private User user;
    private Group group;
    private Subject subject;
    private User userTeacher;

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

    public UserGroupSubject() {
    }

    public UserGroupSubject(int id, User user, Group group, Subject subject, User userTeacher) {
        this.id = id;
        this.user = user;
        this.group = group;
        this.subject = subject;
        this.userTeacher = userTeacher;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    public User getUserTeacher() {
        return userTeacher;
    }

    public void setUserTeacher(User userTeacher) {
        this.userTeacher = userTeacher;
    }
}
