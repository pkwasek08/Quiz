package pl.project.Result;

import lombok.Data;

@Data
public class ResultDTO {
    private int id;
    private Double mark;
    private Integer points;
    private Integer userId;
    private Integer generateTestId;
    private Integer resultId;

    public ResultDTO(int id, Double mark, Integer points, Integer userId, Integer generateTestId, Integer resultId) {
        this.id = id;
        this.mark = mark;
        this.points = points;
        this.userId = userId;
        this.generateTestId = generateTestId;
        this.resultId = resultId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getMark() {
        return mark;
    }

    public void setMark(Double mark) {
        this.mark = mark;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGenerateTestId() {
        return generateTestId;
    }

    public void setGenerateTestId(Integer generateTestId) {
        this.generateTestId = generateTestId;
    }

    public Integer getResultId() {
        return resultId;
    }

    public void setResultId(Integer resultId) {
        this.resultId = resultId;
    }
}
