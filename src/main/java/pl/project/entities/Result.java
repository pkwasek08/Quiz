package pl.project.entities;

import javax.persistence.*;

@Entity
@Table(name = "results", schema = "public", catalog = "d9h3r67ca39jah")
public class Result {
    private int id;
    private Integer mark;
    private Integer points;
    private User userByUserId;
    private GenerateTest generateTestsByGenerateTestId;
    private Result resultByPreviousVersion;

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
    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
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
        if (mark != null ? !mark.equals(that.mark) : that.mark != null) return false;
        if (points != null ? !points.equals(that.points) : that.points != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (mark != null ? mark.hashCode() : 0);
        result = 31 * result + (points != null ? points.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "generate_test_id", referencedColumnName = "id")
    public GenerateTest getGenerateTestsByGenerateTestId() {
        return generateTestsByGenerateTestId;
    }

    public void setGenerateTestsByGenerateTestId(GenerateTest generateTestsByGenerateTestId) {
        this.generateTestsByGenerateTestId = generateTestsByGenerateTestId;
    }

    @ManyToOne
    @JoinColumn(name = "previous_version", referencedColumnName = "id")
    public Result getResultByPreviousVersion() {
        return resultByPreviousVersion;
    }

    public void setResultByPreviousVersion(Result resultByPreviousVersion) {
        this.resultByPreviousVersion = resultByPreviousVersion;
    }
}
