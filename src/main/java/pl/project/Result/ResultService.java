package pl.project.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.project.Answer.AnswerDTO;
import pl.project.ChosenAnswer.ChosenAnswerDTO;
import pl.project.ChosenAnswer.ChosenAnswerService;
import pl.project.GenerateTest.GenerateTestService;
import pl.project.User.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Service
public class ResultService {
    @Autowired
    private ResultRepository resultRepository;
    @Autowired
    private ChosenAnswerService chosenAnswerService;
    @Autowired
    private UserService userService;
    @Autowired
    private GenerateTestService generateTestService;

    public List<Result> getAllResult() {
        List<Result> resultList = new ArrayList<>();
        resultRepository.findAll().forEach(resultList::add);
        return resultList;
    }

    public Result getResult(Integer id) {
        if(nonNull(id)) {
            return resultRepository.findById(id).get();
        }
        return null;
    }

    public Result getResultByUserIdAndGenerateTestIdAndAnswerList(Integer generateTestId, List<AnswerDTO> answerList, Integer userId) {
        int points = getPointsAndAddChosenAnswer(answerList);
        Result result = addResult(new ResultDTO(0, getMark(points, generateTestService.getFullPoints(generateTestId)), points, userId, generateTestId, null));
        return result;
    }

    private int getPointsAndAddChosenAnswer(List<AnswerDTO> answerList) {
        int points = 0;
        for (AnswerDTO answer : answerList) {
            if (answer.getCorrect()) {
                points++;
            }
            chosenAnswerService.addChosenAnswer(new ChosenAnswerDTO(0, answer.getAnswer(), answer.getTaskId(), answer.getId()));
        }
        return points;
    }

    public Result getNextTermResultByResultIdAndAnswerList(Integer resultId, List<AnswerDTO> answerList) {
        Result previousResult = getResult(resultId);
        int points = getPointsAndAddChosenAnswer(answerList);
        Result result = addResult(new ResultDTO(0, getMark(points, generateTestService.getFullPoints(previousResult.getGenerateTestsByGenerateTestId().getId())),
                points, previousResult.getUserByUserId().getId(), previousResult.getGenerateTestsByGenerateTestId().getId(), previousResult.getId()));
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
        return resultRepository.save(new Result(0, result.getMark(), result.getPoints(), userService.getUser(result.getUserId()),
                generateTestService.getGenerateTest(result.getGenerateTestId()), getResult(result.getResultId())));
    }


    public void updateResult(Integer id, Result result) {
        resultRepository.save(result);
    }


    public void deleteResult(Integer id) {
        resultRepository.deleteById(id);
    }
}
