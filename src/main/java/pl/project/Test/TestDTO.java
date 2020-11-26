package pl.project.Test;


import java.util.Date;

public class TestDTO {
    private int id;
    private String name;
    private Integer fullPoints;
    private Date date;
    private Long time;
    private int subjectId;

    public TestDTO() {
    }

    public TestDTO(int id, String name, Integer fullPoints, Date date, Long time, int subjectId) {
        this.id = id;
        this.name = name;
        this.fullPoints = fullPoints;
        this.date = date;
        this.time = time;
        this.subjectId = subjectId;
    }

    public TestDTO(Test test) {
        this.id = test.getId();
        this.name = test.getName();
        this.fullPoints = test.getFullPoints();
        this.date = test.getDate();
        this.time = test.getTime();
        this.subjectId = test.getSubject().getId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFullPoints() {
        return fullPoints;
    }

    public void setFullPoints(Integer fullPoints) {
        this.fullPoints = fullPoints;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }
}
