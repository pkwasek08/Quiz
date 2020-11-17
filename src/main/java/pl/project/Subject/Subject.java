package pl.project.Subject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import pl.project.Test.Test;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "subjects", schema = "public", catalog = "d9h3r67ca39jah")
public class Subject {
    private int id;
    private String name;
    private List<Test> tests;

    @JsonManagedReference(value="subject-test")
    @JsonIgnore
    @OneToMany(mappedBy = "subjectBySubjectId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<Test> getTests() {
        return tests;
    }

    public Subject(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Subject() {
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subject that = (Subject) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
