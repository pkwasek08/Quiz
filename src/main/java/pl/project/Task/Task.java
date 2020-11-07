package pl.project.Task;

import pl.project.Test.Test;

import javax.persistence.*;

@Entity
@Table(name = "tasks", schema = "public", catalog = "d9h3r67ca39jah")
public class Task {
    private int id;
    private String question;
    private String type;
    private String image;
    private Integer points;
    private Test testByTestId;

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
    @Column(name = "question", nullable = true, length = -1)
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Basic
    @Column(name = "type", nullable = true, length = -1)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "image", nullable = true, length = -1)
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

        Task that = (Task) o;

        if (id != that.id) return false;
        if (question != null ? !question.equals(that.question) : that.question != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (image != null ? !image.equals(that.image) : that.image != null) return false;
        if (points != null ? !points.equals(that.points) : that.points != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (question != null ? question.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (points != null ? points.hashCode() : 0);
        return result;
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
