package pl.project.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.project.entities.GenerateTest;
import pl.project.services.GenerateTestService;

import java.util.List;

@RestController
@RequestMapping(value = "/generateTest")
public class GenerateTestController {
    Logger log = LogManager.getLogger(this.getClass());
    @Autowired
    private GenerateTestService generateTestService;

    @GetMapping()
    @CrossOrigin(origins = "*")
    public List<GenerateTest> getGenerateTest() {
        return generateTestService.getAllGenerateTest();
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "*")
    public GenerateTest getGenerateTest(@PathVariable Integer id) {
        return generateTestService.getGenerateTest(id);
    }

    @PostMapping()
    @CrossOrigin(origins = "*")
    public void addGenerateTest(@RequestBody GenerateTest generateTest) {
        generateTestService.addGenerateTest(generateTest);
    }

    @PutMapping(value = "/{id}")
    @CrossOrigin(origins = "*")
    public void updateGenerateTest(@RequestBody GenerateTest generateTest, @PathVariable Integer id) {
        generateTestService.updateGenerateTest(id, generateTest);
    }

    @DeleteMapping(value = "/{id}")
    @CrossOrigin(origins = "*")
    public void deleteGenerateTest(@PathVariable Integer id) {
        generateTestService.deleteGenerateTest(id);
    }
}
