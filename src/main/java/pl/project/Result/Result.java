package pl.project.Result;

import pl.project.GenerateTest.GenerateTest;
import pl.project.User.User;

import javax.persistence.*;

@Entity
@Table(name = "results", schema = "public", catalog = "d9h3r67ca39jah")
public class Result {
    private int id;
    private double mark;
    private Integer points;
    private User user;
    private GenerateTest generateTest;
    private Result result;

    public Result() {
    }

    public Result(int id, double mark, Integer points, User user, GenerateTest generateTest, Result result) {
        this.id = id;
        this.mark = mark;
        this.points = points;
        this.user = user;
        this.generateTest = generateTest;
        this.result = result;
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
    @Column(name = "mark", nullable = true)
    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    @Basic
    @Column(name = "points", nullable = true)
    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Result that = (Result) o;

        if (id != that.id) return false;
        if (points != null ? !points.equals(that.points) : that.points != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (points != null ? points.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "generate_test_id", referencedColumnName = "id")
    public GenerateTest getGenerateTest() {
        return generateTest;
    }

    public void setGenerateTest(GenerateTest generateTest) {
        this.generateTest = generateTest;
    }

    @ManyToOne
    @JoinColumn(name = "previous_version", referencedColumnName = "id")
    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
