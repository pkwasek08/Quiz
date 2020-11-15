package pl.project.GenerateTask;

public class GenerateTaskDTO {
    private int id;
    private int taskId;
    private int generateTestId;

    public GenerateTaskDTO() {
    }

    public GenerateTaskDTO(int id, int taskId, int generateTestId) {
        this.id = id;
        this.taskId = taskId;
        this.generateTestId = generateTestId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public int getGenerateTestId() {
        return generateTestId;
    }

    public void setGenerateTestId(int generateTestId) {
        this.generateTestId = generateTestId;
    }
}
