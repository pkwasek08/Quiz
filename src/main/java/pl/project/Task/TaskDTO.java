package pl.project.Task;

import pl.project.Test.Test;

public class TaskDTO {
    private int id;
    private String question;
    private String type;
    private String image;
    private Integer points;
    private int testId;

    public TaskDTO() {
    }

    public TaskDTO(int id, String question, String type, String image, Integer points, int testId) {
        this.id = id;
        this.question = question;
        this.type = type;
        this.image = image;
        this.points = points;
        this.testId = testId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }
}
