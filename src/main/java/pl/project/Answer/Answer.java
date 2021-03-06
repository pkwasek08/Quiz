package pl.project.Answer;

import pl.project.Task.Task;

import javax.persistence.*;

@Entity
@Table(name = "answers", schema = "public", catalog = "d9h3r67ca39jah")
public class Answer {
    private int id;
    private String answer;
    private Boolean correct;
    private Task task;

    public Answer() {
    }

    public Answer(int id, String answer, Boolean correct, Task task) {
        this.id = id;
        this.answer = answer;
        this.correct = correct;
        this.task = task;
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
    @Column(name = "answer", nullable = true, length = -1)
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Basic
    @Column(name = "correct", nullable = true)
    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Answer that = (Answer) o;

        if (id != that.id) return false;
        if (answer != null ? !answer.equals(that.answer) : that.answer != null) return false;
        if (correct != null ? !correct.equals(that.correct) : that.correct != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (answer != null ? answer.hashCode() : 0);
        result = 31 * result + (correct != null ? correct.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "tasks_id", referencedColumnName = "id")
    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
