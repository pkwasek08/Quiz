package pl.project.Task;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.project.Answer.AnswerDTO;
import pl.project.Answer.AnswerService;
import pl.project.GenerateTask.GenerateTask;
import pl.project.GenerateTask.GenerateTaskDTO;
import pl.project.GenerateTask.GenerateTaskService;
import pl.project.GenerateTest.GenerateTest;
import pl.project.GenerateTest.GenerateTestDTO;
import pl.project.GenerateTest.GenerateTestService;
import pl.project.Test.TestRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Objects.nonNull;

@Service
public class TaskService {
    Logger log = LogManager.getLogger(this.getClass());

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private AnswerService answerService;
    @Autowired
    private GenerateTestService generateTestService;
    @Autowired
    private GenerateTaskService generateTaskService;

    public List<Task> getAllTask() {
        List<Task> taskList = new ArrayList<>();
        taskRepository.findAll().forEach(taskList::add);
        return taskList;
    }

    public List<Task> getAllTaskByTestId(Integer testId) {
        return taskRepository.findAllByTestByTestId_Id(testId);
    }

    public List<Task> getAllTaskByTestIdNotTextType(Integer testId, String type) {
        return taskRepository.findAllByTestByTestId_IdAndTypeNotLike(testId, type);
    }

    public List<Task> getAllTaskByTestIdAndType(Integer testId, String type) {
        return taskRepository.findAllByTestByTestId_IdAndType(testId, type);
    }

    public Integer getPointsByTaskId(Integer taskId) {
        return taskRepository.getPointsByTaskId(taskId);
    }

    public Task getTask(Integer id) {
        Task task = taskRepository.findById(id).get();
        return task;
    }

    public List<TaskDTO> getGenerateTaskAndAnswers(Integer testId, Integer amountTasks) {
        if (nonNull(testId) && amountTasks > 0) {
            return getGenerateTasksAndAnswers(testId, amountTasks);
        } else {
            return Collections.emptyList();
        }
    }

    public void addTask(TaskDTO taskDTO) {
        Task task = taskRepository.save(new Task(0, taskDTO.getQuestion(), taskDTO.getType(), taskDTO.getImage(), taskDTO.getPoints(), testRepository.findById(taskDTO.getTestId()).get()));
        taskDTO.getAnswerList().stream().forEach(answer -> {
            answer.setTaskId(task.getId());
            answerService.addAnswer(answer);
        });
    }


    public void updateTask(Integer id, TaskDTO taskDTO) {
        Task task = new Task(taskDTO.getId(), taskDTO.getQuestion(), taskDTO.getType(), taskDTO.getImage(), taskDTO.getPoints(), testRepository.findById(taskDTO.getTestId()).get());
        taskRepository.save(task);
        taskDTO.getAnswerList().stream().forEach(answer -> {
            answer.setTaskId(task.getId());
            answerService.updateAnswer(answer.getId(), answer);
        });
    }


    public void deleteTask(Integer id) {
        taskRepository.deleteById(id);
    }

    public List<TaskDTO> getGenerateTasksAndAnswers(Integer testId, Integer amountTasks) {
        GenerateTest generateTest = generateTestService.addGenerateTest(new GenerateTestDTO(0, testId));
        List<TaskDTO> taskList = new ArrayList<>();
        List<Task> taskDataList = getAllTaskByTestIdNotTextType(testId, "TextQuestion");
        taskList.add(addTextQuestionTask(testId, generateTest.getId()));
        if (--amountTasks > 0) {
            taskList.addAll(taskRand(taskDataList, amountTasks, generateTest.getId()));
        }
        return taskList;
    }

    private List<TaskDTO> taskRand(List<Task> taskDataList, Integer amountTasks, Integer generateTestId) {
        List<TaskDTO> result = new ArrayList<>();
        Collections.shuffle(taskDataList);
        for (Task task : taskDataList) {
            List<AnswerDTO> answerList = new ArrayList<>();
            GenerateTask generateTask = generateTaskService.addGenerateTask(new GenerateTaskDTO(0, task.getId(), generateTestId));
            answerService.getAllAnswerByTask(task.getId()).stream().forEach(answer ->
                    answerList.add(new AnswerDTO(answer.getId(), answer.getAnswer(), answer.getCorrect(), generateTask.getId()))
            );
            result.add(new TaskDTO(task.getId(), task.getQuestion(), task.getType(), task.getImage(), task.getPoints(), generateTestId, answerList));
            amountTasks--;
            if (amountTasks == 0) {
                break;
            }
        }
        return result;
    }

    private TaskDTO addTextQuestionTask(Integer testId, Integer generateTestId) {
        List<Task> taskTextQuestionList = getAllTaskByTestIdAndType(testId, "TextQuestion");
        if (taskTextQuestionList.isEmpty()) {
            return new TaskDTO();
        }
        Collections.shuffle(taskTextQuestionList);
        Task textTask = taskTextQuestionList.get(0);
        return new TaskDTO(textTask.getId(), textTask.getQuestion(), textTask.getType(), textTask.getImage(), textTask.getPoints(), generateTestId, Collections.emptyList());
    }
}
