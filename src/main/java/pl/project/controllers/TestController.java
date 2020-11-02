package pl.project.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.project.entities.Test;
import pl.project.services.TestService;

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

    @PostMapping()
    @CrossOrigin(origins = "*")
    public void addTest(@RequestBody Test test) {
        testService.addTest(test);
    }

    @PutMapping(value = "/{id}")
    @CrossOrigin(origins = "*")
    public void updateTest(@RequestBody Test test, @PathVariable Integer id) {
        testService.updateTest(id, test);
    }

    @DeleteMapping(value = "/{id}")
    @CrossOrigin(origins = "*")
    public void deleteTest(@PathVariable Integer id) {
        testService.deleteTest(id);
    }
}
