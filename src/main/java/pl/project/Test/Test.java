package pl.project.Test;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import pl.project.Answer.Answer;
import pl.project.GenerateTest.GenerateTest;
import pl.project.Subject.Subject;
import pl.project.Task.Task;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tests", schema = "public", catalog = "d9h3r67ca39jah")
public class Test {
    private int id;
    private String name;
    private Integer fullPoints;
    private Date date;
    private Long time;
    private List<Task> tasks;
    private List<GenerateTest> generateTests;

    @JsonManagedReference(value="test-generateTest")
    @JsonIgnore
    @OneToMany(mappedBy = "test", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<GenerateTest> getGenerateTests() {
        return generateTests;
    }

    public void setGenerateTests(List<GenerateTest> generateTests) {
        this.generateTests = generateTests;
    }

    @JsonManagedReference(value="test-task")
    @JsonIgnore
    @OneToMany(mappedBy = "testByTestId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
    private Subject subject;

    public Test() {
    }

    public Test(int id, String name, Integer fullPoints, Date date, Long time, Subject subject) {
        this.id = id;
        this.name = name;
        this.fullPoints = fullPoints;
        this.date = date;
        this.time = time;
        this.subject = subject;
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
    @Column(name = "name", nullable = true, length = -1)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "full_points", nullable = true)
    public Integer getFullPoints() {
        return fullPoints;
    }

    public void setFullPoints(Integer fullPoints) {
        this.fullPoints = fullPoints;
    }

    @Basic
    @Column(name = "date", nullable = true)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "time", nullable = true)
    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Test that = (Test) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (fullPoints != null ? !fullPoints.equals(that.fullPoints) : that.fullPoints != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (fullPoints != null ? fullPoints.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JsonBackReference(value="subject-test")
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
