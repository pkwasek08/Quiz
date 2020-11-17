package pl.project.GenerateTest;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import pl.project.GenerateTask.GenerateTask;
import pl.project.Test.Test;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "generate_tests", schema = "public", catalog = "d9h3r67ca39jah")
public class GenerateTest {
    private int id;
    private Test testByTestId;
    private List<GenerateTask> generateTasks;

    @JsonManagedReference(value = "generateTest-generateTask")
    @JsonIgnore
    @OneToMany(mappedBy = "generateTestsByGenerateTest", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<GenerateTask> getGenerateTasks() {
        return generateTasks;
    }

    public void setGenerateTasks(List<GenerateTask> generateTasks) {
        this.generateTasks = generateTasks;
    }

    public GenerateTest() {
    }

    public GenerateTest(int id, Test testByTestId) {
        this.id = id;
        this.testByTestId = testByTestId;
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

        GenerateTest that = (GenerateTest) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @ManyToOne
    @JoinColumn(name = "test_id", referencedColumnName = "id")
    public Test getTestByTestId() {
        return testByTestId;
    }

    public void setTestByTestId(Test testByTestId) {
        this.testByTestId = testByTestId;
    }
}
