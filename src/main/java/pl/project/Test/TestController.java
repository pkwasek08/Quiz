package pl.project.Test;

import io.swagger.models.auth.In;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/test")
public class TestController {
    Logger log = LogManager.getLogger(this.getClass());
    @Autowired
    private TestService testService;

    @GetMapping()
    @CrossOrigin(origins = "*")
    public List<Test> getTest() {
        return testService.getAllTest();
    }

    @GetMapping("/subject/{id}")
    @CrossOrigin(origins = "*")
    public List<Test> getTestsBySubject(@PathVariable Integer id) {
        return testService.getAllTestBySubject(id);
    }

    @GetMapping("/subjectAndGroup")
    @CrossOrigin(origins = "*")
    public List<Test> getTestsBySubjectAndGroup(@RequestParam Integer subjectId,@RequestParam  Integer groupId) {
        return testService.getAllTestBySubjectAndGroup(subjectId,groupId);
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "*")
    public Test getTest(@PathVariable Integer id) {
        return testService.getTest(id);
    }


    @GetMapping("/userAndSubject")
    @CrossOrigin(origins = "*")
    public List<TestDTO> getTestsByUserIdAndSubjectId(@RequestParam Integer userId, @RequestParam Integer subjectId) {
        return testService.getTestsByUserIdAndSubjectId(userId, subjectId);
    }

    @PostMapping()
    @CrossOrigin(origins = "*")
    public ResponseEntity addTest(@RequestBody TestDTO testDTO) throws SchedulerException {
        return testService.addTest(testDTO);
    }

    @PutMapping(value = "/{id}")
    @CrossOrigin(origins = "*")
    public void updateTest(@RequestBody TestDTO testDTO, @PathVariable Integer id) {
        testService.updateTest(id, testDTO);
    }

    @DeleteMapping(value = "/{id}")
    @CrossOrigin(origins = "*")
    public void deleteTest(@PathVariable Integer id) {
        testService.deleteTest(id);
    }
}
