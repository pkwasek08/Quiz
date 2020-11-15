package pl.project.Answer;

public class AnswerDTO {
    private int id;
    private String answer;
    private Boolean correct;
    private int taskId;

    public AnswerDTO() {
    }

    public AnswerDTO(int id, String answer, Boolean correct, int taskId) {
        this.id = id;
        this.answer = answer;
        this.correct = correct;
        this.taskId = taskId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
}
