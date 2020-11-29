package pl.project.GenerateTask;

import pl.project.ChosenAnswer.ChosenAnswer;
import pl.project.Task.Task;
import pl.project.Task.TaskDTO;

import java.util.List;

public class GenerateTaskAndAnswerDTO {
    List<ChosenAnswer> chosenAnswers;
    TaskDTO taskDTO;

    public List<ChosenAnswer> getChosenAnswers() {
        return chosenAnswers;
    }

    public void setChosenAnswers(List<ChosenAnswer> chosenAnswers) {
        this.chosenAnswers = chosenAnswers;
    }

    public TaskDTO getTaskDTO() {
        return taskDTO;
    }

    public void setTaskDTO(TaskDTO taskDTO) {
        this.taskDTO = taskDTO;
    }

    public GenerateTaskAndAnswerDTO(List<ChosenAnswer> chosenAnswers, TaskDTO taskDTO) {
        this.chosenAnswers = chosenAnswers;
        this.taskDTO = taskDTO;
    }

    public GenerateTaskAndAnswerDTO() {
    }
}
