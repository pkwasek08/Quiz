package pl.project.UserGroupSubject;

import pl.project.Group.Group;
import pl.project.Subject.Subject;
import pl.project.User.User;

public class UserGroupSubjectDTO {
    private int id;
    private int userId;
    private int groupId;
    private int subjectId;
    private int teacherId;

    public UserGroupSubjectDTO() {
    }


    public UserGroupSubjectDTO(int id, int userId, int groupId, int subjectId, int teacherId) {
        this.id = id;
        this.userId = userId;
        this.groupId = groupId;
        this.subjectId = subjectId;
        this.teacherId = teacherId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
