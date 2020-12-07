package pl.project.Result;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.project.Answer.AnswerDTO;
import pl.project.Helper.PolishStringHelper;
import pl.project.Result.Export.ResultExcelGenerator;
import pl.project.Result.Export.ResultPdfGenerator;
import pl.project.Test.TestService;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping(value = "/result")
public class ResultController {
    Logger log = LogManager.getLogger(this.getClass());
    @Autowired
    private ResultService resultService;
    @Autowired
    private TestService testService;

    @GetMapping()
    @CrossOrigin(origins = "*")
    public List<Result> getResult() {
        return resultService.getAllResult();
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "*")
    public Result getResult(@PathVariable Integer id) {
        return resultService.getResult(id);
    }

    @PostMapping("/user/answerList")
    @CrossOrigin(origins = "*")
    public Result getResultByUserIdAndGenerateTestIdAndAnswerList(@RequestParam Integer userId,
                                                                  @RequestParam Integer generateTestId, @RequestBody List<AnswerDTO> answerList) {
        return resultService.getResultByUserIdAndGenerateTestIdAndAnswerList(generateTestId, answerList, userId);
    }

    @PostMapping("/user/answerList/nextTerm")
    @CrossOrigin(origins = "*")
    public Result getNextTermResultByResultIdAndAnswerList(@RequestParam Integer resultId, @RequestBody List<AnswerDTO> answerList) {
        return resultService.getNextTermResultByResultIdAndAnswerList(resultId, answerList);
    }

    @GetMapping("/user/subject/isMark")
    @CrossOrigin(origins = "*")
    public List<Result> getResultWithMarkListByUserIdAndSubjectId(@RequestParam Integer userId, @RequestParam Integer subjectId, @RequestParam Boolean isMark) {
        if (isMark) {
            return resultService.getResultWithMarkListByUserIdAndSubjectId(userId, subjectId);
        } else {
            return resultService.getResultWithoutMarkListByUserIdAndSubjectId(userId, subjectId);
        }
    }

    @GetMapping("/teacher/subject/group/isMark")
    @CrossOrigin(origins = "*")
    public List<Result> getResultWithMarkListByUserIdAndSubjectId(@RequestParam Integer teacherId, @RequestParam Integer groupId, @RequestParam Integer subjectId, @PathVariable Boolean isMark) {
        if (isMark) {
            return resultService.getResultWithMarkListByTeacherIdAndSubjectId(teacherId, groupId, subjectId);
        } else {
            return resultService.getResultWithoutMarkListByTeacherIdAndSubjectId(teacherId, groupId, subjectId);
        }
    }

    @PostMapping()
    @CrossOrigin(origins = "*")
    public void addResult(@RequestBody ResultDTO result) {
        resultService.addResult(result);
    }

    @PostMapping("/points")
    @CrossOrigin(origins = "*")
    public double addPointsToResult(@RequestParam Integer resultId, @RequestParam Integer points) {
        return resultService.addPointsToResult(resultId, points);
    }

    @PutMapping(value = "/{id}")
    @CrossOrigin(origins = "*")
    public void updateResult(@RequestBody Result result, @PathVariable Integer id) {
        resultService.updateResult(id, result);
    }

    @DeleteMapping(value = "/{id}")
    @CrossOrigin(origins = "*")
    public void deleteResult(@PathVariable Integer id) {
        resultService.deleteResult(id);
    }

    @GetMapping("/export/excel")
    public void exportToExcelByTestIdAndGroupId(HttpServletResponse response, @RequestParam Integer teacherId, @RequestParam Integer testId, @RequestParam Integer groupId) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        String currentDateTime = dateFormatter.format(new Date());
        List<Result> resultList = resultService.getAllResultByTeacherIdTestIdAndGroupId(teacherId, testId, groupId);

        String headerKey = "Content-Disposition";
        if (!resultList.isEmpty()) {
            String testName = resultList.get(0).getGenerateTest().getTest().getName().replaceAll("\\s+", "_") + "_" + currentDateTime + ".xlsx";
            String headerValue = "attachment; filename=Wyniki_" + PolishStringHelper.replacePolishCharacters(testName);
            response.setHeader(headerKey, headerValue);

            ResultExcelGenerator excelExporter = new ResultExcelGenerator(resultList);

            excelExporter.export(response);
        }
    }

    @GetMapping(value = "/export/pdf")
    public ResponseEntity<Object> resultsReport(@RequestParam Integer teacherId, @RequestParam Integer testId, @RequestParam Integer groupId) {
        ArrayList<Result> resultList =  new ArrayList<>();
        resultList.addAll(resultService.getAllResultByTeacherIdTestIdAndGroupId(teacherId, testId, groupId));
        if (!resultList.isEmpty()) {
            DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
            String currentDateTime = dateFormatter.format(new Date());
            ByteArrayInputStream bis = ResultPdfGenerator.resultsReport(resultList);
            String testName = resultList.get(0).getGenerateTest().getTest().getName().replaceAll("\\s+", "_") + "_" + currentDateTime + ".pdf";
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline; filename=Wyniki_" + PolishStringHelper.replacePolishCharacters(testName));

            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(new InputStreamResource(bis));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED).body("No results");
        }
    }
}
