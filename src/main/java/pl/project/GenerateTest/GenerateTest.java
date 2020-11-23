package pl.project.GenerateTest;

import pl.project.Test.Test;

import javax.persistence.*;

@Entity
@Table(name = "generate_tests", schema = "public", catalog = "d9h3r67ca39jah")
public class GenerateTest {
    private int id;
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
