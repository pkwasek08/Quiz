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
    private Test test;

    public GenerateTest() {
    }

    public GenerateTest(int id, Test test) {
        this.id = id;
        this.test = test;
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
    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }
}
