package pl.project.GenerateTask;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import pl.project.ChosenAnswer.ChosenAnswer;
import pl.project.GenerateTest.GenerateTest;
import pl.project.Task.Task;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "generate_tasks", schema = "public", catalog = "d9h3r67ca39jah")
public class GenerateTask {
    private int id;
    private Task tasksByTaskId;
    private GenerateTest generateTestsByGenerateTest;
    private List<ChosenAnswer> chosenAnswers;

    @JsonManagedReference(value = "generateTask-chosenAnswer")
    @JsonIgnore
    @OneToMany(mappedBy = "generateTasksByGenerateTaskId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<ChosenAnswer> getChosenAnswers() {
        return chosenAnswers;
    }

    public void setChosenAnswers(List<ChosenAnswer> chosenAnswers) {
        this.chosenAnswers = chosenAnswers;
    }

    public GenerateTask() {
    }

    public GenerateTask(int id, Task tasksByTaskId, GenerateTest generateTestsByGenerateTest) {
        this.id = id;
        this.tasksByTaskId = tasksByTaskId;
        this.generateTestsByGenerateTest = generateTestsByGenerateTest;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GenerateTask that = (GenerateTask) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @ManyToOne
    @JoinColumn(name = "tasks_id", referencedColumnName = "id")
    public Task getTasksByTaskId() {
        return tasksByTaskId;
    }

    public void setTasksByTaskId(Task tasksByTaskId) {
        this.tasksByTaskId = tasksByTaskId;
    }

    @ManyToOne
    @JoinColumn(name = "generate_test", referencedColumnName = "id")
    public GenerateTest getGenerateTestsByGenerateTest() {
        return generateTestsByGenerateTest;
    }

    public void setGenerateTestsByGenerateTest(GenerateTest generateTestsByGenerateTest) {
        this.generateTestsByGenerateTest = generateTestsByGenerateTest;
    }
}
