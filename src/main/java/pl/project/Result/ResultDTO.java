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
}
