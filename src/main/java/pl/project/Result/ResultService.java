package pl.project.Result;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import pl.project.Answer.AnswerDTO;
import pl.project.Answer.AnswerService;
import pl.project.ChosenAnswer.ChosenAnswerService;
import pl.project.GenerateTask.GenerateTaskService;
import pl.project.GenerateTest.GenerateTest;
import pl.project.GenerateTest.GenerateTestService;
import pl.project.Task.Task;
import pl.project.Task.TaskService;
import pl.project.User.UserService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.Objects.nonNull;

@Service
public class ResultService {
    Logger log = LogManager.getLogger(this.getClass());
    @Autowired
    private ResultDao resultDao;
    @Autowired
    private ResultRepository resultRepository;
    @Autowired
    private AnswerService answerService;
    @Autowired
    private UserService userService;
    @Autowired
    private GenerateTestService generateTestService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private ChosenAnswerService chosenAnswerService;
    @Autowired
    private GenerateTaskService generateTaskService;

    public List<Result> getAllResult() {
        List<Result> resultList = new ArrayList<>();
        resultRepository.findAll().forEach(resultList::add);
        return resultList;
    }

    public Result getResult(Integer id) {
        if (nonNull(id)) {
            return resultRepository.findById(id).get();
        }
        return null;
    }

    public Result getResultByUserIdAndGenerateTestIdAndAnswerList(Integer generateTestId, List<AnswerDTO> answerList, Integer userId) {
        chosenAnswerService.addChooseAnswerList(answerList);
        int points = getPointsAndAddChosenAnswer(answerList);
        List<Result> result = getResultByUserIdAndGenerateTestId(userId, generateTestId);
        if(!result.isEmpty()){
            return addResult(new ResultDTO(0, null, points, userId, generateTestId, null));
        } else {
            return updateResult(result.get(0).getId(), getResult(0));
        }
    }

    private HashMap<Integer, List<AnswerDTO>> createHashMapTaskWithoutTextQuestion(List<AnswerDTO> answerList) {
        HashMap<Integer, List<AnswerDTO>> hashMap = new HashMap<>();
        answerList.stream().filter(answerDTO -> !generateTaskService.getGenerateTask(answerDTO.getTaskId()).getTasksByTaskId().getType().equals("TextQuestion")).forEach(answerDTO -> {
            if (!hashMap.containsKey(answerDTO.getTaskId())) {
                List<AnswerDTO> list = new ArrayList<>();
                list.add(answerDTO);
                hashMap.put(answerDTO.getTaskId(), list);
            } else {
                hashMap.get(answerDTO.getTaskId()).add(answerDTO);
            }
        });
        return hashMap;
    }

    private int getPointsAndAddChosenAnswer(List<AnswerDTO> answerList) {
        AtomicInteger finalPoints = new AtomicInteger();
        finalPoints.set(0);
        HashMap<Integer, List<AnswerDTO>> hashMap = createHashMapTaskWithoutTextQuestion(answerList);
        hashMap.forEach((taskId, answerDTOList) -> {
            int taskPoints = 0;
            for (AnswerDTO answer : answerDTOList) {
                if (!answer.getCorrect()) {
                    break;
                } else {
                    taskPoints++;
                }
            }
            if (answerService.getNumberCorrectAnswerByGenerateTaskId(taskId).equals(taskPoints)) {
                finalPoints.addAndGet(generateTaskService.getPointsByGenerateTaskId(taskId));
            }
        });
        return finalPoints.get();
    }

    public Result getNextTermResultByResultIdAndAnswerList(Integer resultId, List<AnswerDTO> answerList) {
        Result previousResult = getResult(resultId);
        int points = getPointsAndAddChosenAnswer(answerList);
        Result result = addResultNextTerm(new ResultDTO(0, null,
                points, previousResult.getUser().getId(), previousResult.getGenerateTest().getId(), previousResult.getId()));
        return result;
    }

    private double getMark(int points, int fullPoints) {
        int percentResult = (points * 100) / fullPoints;

        if (percentResult >= 90) {
            return 5;
        }
        if (percentResult >= 82 && percentResult < 90) {
            return 4.5;
        }
        if (percentResult >= 75 && percentResult < 82) {
            return 4.0;
        }
        if (percentResult >= 65 && percentResult < 75) {
            return 3.5;
        }
        if (percentResult >= 50 && percentResult < 65) {
            return 3;
        }
        if (percentResult < 50) {
            return 2;
        }
        return 0;
    }

    public Result addResult(ResultDTO result) {
        Logger log = LogManager.getLogger(this.getClass());
        return resultRepository.save(new Result(0, result.getMark(), result.getPoints(), userService.getUser(result.getUserId()),
                generateTestService.getGenerateTest(result.getGenerateTestId()), null));
    }

    public Result addResultNextTerm(ResultDTO result) {
        return resultRepository.save(new Result(0, result.getMark(), result.getPoints(), userService.getUser(result.getUserId()),
                generateTestService.getGenerateTest(result.getGenerateTestId()), getResult(result.getResultId())));
    }

    public double addPointsToResult(@RequestParam Integer resultId, @RequestParam Integer points) {
        Result result = resultRepository.findById(resultId).get();
        GenerateTest generateTest = generateTestService.getGenerateTest(result.getGenerateTest().getId());
        result.setPoints(result.getPoints() + points);
        int fullPoints = taskService.getAllTaskByTestId(generateTest.getTest().getId()).stream().mapToInt(Task::getPoints).sum();
        result.setMark(getMark(result.getPoints(), fullPoints));
        resultRepository.save(result);
        return result.getMark();
    }


    public Result updateResult(Integer id, Result result) {
        return resultRepository.save(result);
    }


    public void deleteResult(Integer id) {
        resultRepository.deleteById(id);
    }

    public List<Result> getAllResultBySubjectId(Integer subjectId) {
        return resultRepository.findAllByGenerateTest_Test_Subject_Id(subjectId);
    }

    public List<Result> getResultByUserIdAndTestId(Integer userId, Integer testId) {
        return resultRepository.findAllByUser_IdAndGenerateTest_Test_Id(userId, testId);
    }

    public List<Result> getResultByUserIdAndGenerateTestId(Integer userId, Integer generateTestId) {
        return resultRepository.findAllByUser_IdAndGenerateTest_Id(userId, generateTestId);
    }


    public List<Result> getResultWithMarkListByUserIdAndSubjectId(Integer userId, Integer subjectId) {
        return resultRepository.findAllByUser_IdAndPointsIsNotNullAndGenerateTest_Test_Subject_Id(userId, subjectId);
    }

    public List<Result> getResultWithoutMarkListByUserIdAndSubjectId(Integer userId, Integer subjectId) {
        return resultRepository.findAllByUser_IdAndPointsIsNullAndGenerateTest_Test_Subject_Id(userId, subjectId);
    }

    public List<Result> getResultWithMarkListByTeacherIdAndSubjectId(Integer teacherId, Integer groupId, Integer subjectId) {
        return resultDao.getAllResultByTeacherIdAndGroupIdAndSubjectIdAndMarkNotNull(teacherId, groupId, subjectId);
    }

    public List<Result> getResultWithoutMarkListByTeacherIdAndSubjectId(Integer teacherId, Integer groupId, Integer subjectId) {
        return resultDao.getAllResultByTeacherIdAndGroupIdAndSubjectIdAndMarkIsNull(teacherId, groupId, subjectId);
    }
}
