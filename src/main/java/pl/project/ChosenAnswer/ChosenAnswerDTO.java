package pl.project.ChosenAnswer;

public class ChosenAnswerDTO {
    private int id;
    private String descriptedAnswer;
    private int generateTaskId;
    private int answerId;

    public ChosenAnswerDTO() {
    }

    public ChosenAnswerDTO(int id, String descriptedAnswer, int generateTaskId, int answerId) {
        this.id = id;
        this.descriptedAnswer = descriptedAnswer;
        this.generateTaskId = generateTaskId;
        this.answerId = answerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescriptedAnswer() {
        return descriptedAnswer;
    }

    public void setDescriptedAnswer(String descriptedAnswer) {
        this.descriptedAnswer = descriptedAnswer;
    }

    public int getGenerateTaskId() {
        return generateTaskId;
    }

    public void setGenerateTaskId(int generateTaskId) {
        this.generateTaskId = generateTaskId;
    }

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }
}
