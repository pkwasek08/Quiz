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
        Result result = resultRepository.findById(id).get();
        return result;
    }

    public Result getResultByUserIdAndGenerateTestIdAndAnswerList(Integer generateTestId, List<AnswerDTO> answerList, Integer userId) {
        int points = getPointsAndAddChosenAnswer(answerList);
        Result result = addResult(new ResultDTO(0, 5, points, userId, generateTestId, null));
        return result;
    }

    public int getPointsAndAddChosenAnswer(List<AnswerDTO> answerList) {
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
        Result result = addResult(new ResultDTO(0, 5, points, previousResult.getUserByUserId().getId(),
                previousResult.getGenerateTestsByGenerateTestId().getId(), previousResult.getId()));
        return result;
    }

    public Result addResult(ResultDTO result) {
        return resultRepository.save(new Result(0, result.getMark(), result.getPoints(), userService.getUser(result.getUserId()),
                generateTestService.getGenerateTest(result.getGenerateTestId()), getResult(result.getGenerateTestId())));
    }


    public void updateResult(Integer id, Result result) {
        resultRepository.save(result);
    }


    public void deleteResult(Integer id) {
        resultRepository.deleteById(id);
    }
}
