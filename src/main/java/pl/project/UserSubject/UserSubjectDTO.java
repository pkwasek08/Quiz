package pl.project.UserSubject;

import lombok.Getter;
import lombok.Setter;

public class UserSubjectDTO {
    private Integer userId;
    private Integer subjectId;

    public UserSubjectDTO() {
    }

    public UserSubjectDTO(Integer userId, Integer subjectId) {
        this.userId = userId;
        this.subjectId = subjectId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }
}
