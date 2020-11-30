package pl.project.Answer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.project.GenerateTask.GenerateTaskService;
import pl.project.Task.Task;
import pl.project.Task.TaskRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerService {
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private GenerateTaskService generateTaskService;

    public List<Answer> getAllAnswer() {
        List<Answer> answerList = new ArrayList<>();
        answerRepository.findAll().forEach(answerList::add);
        return answerList;
    }

    public List<Answer> getAllAnswerByTask(int taskId){
        Task task = taskRepository.findById(taskId).get();
        return answerRepository.findAllByTask(task);
    }

    public Answer getAnswer(Integer id) {
        Answer answer = answerRepository.findById(id).get();
        return answer;
    }

    public void addAnswer(AnswerDTO answerDTO) {
        Answer answer = new Answer(0, answerDTO.getAnswer(), answerDTO.getCorrect(), taskRepository.findById(answerDTO.getTaskId()).get());
        answerRepository.save(answer);
    }


    public void updateAnswer(Integer id, AnswerDTO answerDTO) {
        Answer answer = new Answer(answerDTO.getId(), answerDTO.getAnswer(), answerDTO.getCorrect(), taskRepository.findById(answerDTO.getTaskId()).get());
        answerRepository.save(answer);
    }

    public Integer getNumberCorrectAnswerByGenerateTaskId(Integer taskId){
        Task task = generateTaskService.getGenerateTask(taskId).getTasksByTaskId();
        return answerRepository.countAnswerByTask_IdAndCorrectIsTrue(task.getId());
    }

    public void deleteAnswer(Integer id) {
        answerRepository.deleteById(id);
    }
}
