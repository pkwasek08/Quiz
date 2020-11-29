package pl.project.GenerateTask;

import pl.project.ChosenAnswer.ChosenAnswer;
import pl.project.Task.Task;

import java.util.List;

public class GenerateTaskAndAnswerDTO {
    List<ChosenAnswer> chosenAnswers;
    Task task;

    public List<ChosenAnswer> getChosenAnswers() {
        return chosenAnswers;
    }

    public void setChosenAnswers(List<ChosenAnswer> chosenAnswers) {
        this.chosenAnswers = chosenAnswers;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public GenerateTaskAndAnswerDTO(List<ChosenAnswer> chosenAnswers, Task task) {
        this.chosenAnswers = chosenAnswers;
        this.task = task;
    }

    public GenerateTaskAndAnswerDTO() {
    }
}
