package pl.project.Task;

import pl.project.Answer.Answer;
import pl.project.Answer.AnswerDTO;

import java.util.ArrayList;
import java.util.List;

public class TaskDTO {
    private int id;
    private String question;
    private String type;
    private String image;
    private Integer points;
    private int testId;
    private List<AnswerDTO> answerList;

    public TaskDTO() {
    }

    public TaskDTO(Task task) {
        this.id = task.getId();
        this.question = task.getQuestion();
        this.type = task.getType();
        this.image = task.getImage();
        this.points = task.getPoints();
        this.testId = task.getTestByTestId().getId();
        this.answerList = new ArrayList<>();
        for(Answer answer: task.getAnswers()){
            this.answerList.add(new AnswerDTO(answer));
        }
    }

    public TaskDTO(int id, String question, String type, String image, Integer points, int testId, List<AnswerDTO> answerList) {
        this.id = id;
        this.question = question;
        this.type = type;
        this.image = image;
        this.points = points;
        this.testId = testId;
        this.answerList = answerList;
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

    public void setAnswerList(List<AnswerDTO> answerList){
        this.answerList = answerList;
    }
    public List<AnswerDTO> getAnswerList(){
        return this.answerList;
    }
}
