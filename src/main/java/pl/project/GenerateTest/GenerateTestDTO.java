package pl.project.GenerateTest;

import pl.project.Test.Test;

public class GenerateTestDTO {
    private int id;
    private int testId;

    public GenerateTestDTO() {
    }

    public GenerateTestDTO(int id, int testId) {
        this.id = id;
        this.testId = testId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }
}
