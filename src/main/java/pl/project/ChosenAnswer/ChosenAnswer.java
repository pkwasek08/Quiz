package pl.project.ChosenAnswer;

import pl.project.Answer.Answer;
import pl.project.GenerateTask.GenerateTask;

import javax.persistence.*;

@Entity
@Table(name = "chosen_answers", schema = "public", catalog = "d9h3r67ca39jah")
public class ChosenAnswer {
    private int id;
    private String descriptedAnswer;
    private GenerateTask generateTasksByGenerateTaskId;
    private Answer answerByAnswerId;

    public ChosenAnswer() {
    }

    public ChosenAnswer(int id, String descriptedAnswer, GenerateTask generateTasksByGenerateTaskId, Answer answerByAnswerId) {
        this.id = id;
        this.descriptedAnswer = descriptedAnswer;
        this.generateTasksByGenerateTaskId = generateTasksByGenerateTaskId;
        this.answerByAnswerId = answerByAnswerId;
    }

    public ChosenAnswer(int id, String descriptedAnswer, GenerateTask generateTasksByGenerateTaskId) {
        this.id = id;
        this.descriptedAnswer = descriptedAnswer;
        this.generateTasksByGenerateTaskId = generateTasksByGenerateTaskId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "descripted_answer", nullable = true, length = -1)
    public String getDescriptedAnswer() {
        return descriptedAnswer;
    }

    public void setDescriptedAnswer(String descriptedAnswer) {
        this.descriptedAnswer = descriptedAnswer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChosenAnswer that = (ChosenAnswer) o;

        if (id != that.id) return false;
        if (descriptedAnswer != null ? !descriptedAnswer.equals(that.descriptedAnswer) : that.descriptedAnswer != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (descriptedAnswer != null ? descriptedAnswer.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "generate_tasks_id", referencedColumnName = "id")
    public GenerateTask getGenerateTasksByGenerateTaskId() {
        return generateTasksByGenerateTaskId;
    }

    public void setGenerateTasksByGenerateTaskId(GenerateTask generateTasksByGenerateTaskId) {
        this.generateTasksByGenerateTaskId = generateTasksByGenerateTaskId;
    }

    @ManyToOne
    @JoinColumn(name = "answer_id", referencedColumnName = "id")
    public Answer getAnswerByAnswerId() {
        return answerByAnswerId;
    }

    public void setAnswerByAnswerId(Answer answerByAnswerId) {
        this.answerByAnswerId = answerByAnswerId;
    }
}
