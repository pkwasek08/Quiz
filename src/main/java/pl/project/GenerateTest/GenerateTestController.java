package pl.project.GenerateTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/user/{userId}")
    @CrossOrigin(origins = "*")
    public List<GenerateTest> getGenerateTestByUserId(@PathVariable Integer userId) {
        return generateTestService.getGenerateTestsByUserId(userId);
    }

    @GetMapping("/unFilled/user/{userId}")
    @CrossOrigin(origins = "*")
    public List<GenerateTest> getUnFilledGenerateTestByUserId(@PathVariable Integer userId) {
        return generateTestService.getUnFieldGenerateTestsByUserId(userId);
    }

    @PostMapping()
    @CrossOrigin(origins = "*")
    public void addGenerateTest(@RequestBody GenerateTestDTO generateTestDTO) {
        generateTestService.addGenerateTest(generateTestDTO);
    }

    @PutMapping(value = "/{id}")
    @CrossOrigin(origins = "*")
    public void updateGenerateTest(@RequestBody GenerateTestDTO generateTestDTO, @PathVariable Integer id) {
        generateTestService.updateGenerateTest(id, generateTestDTO);
    }

    @DeleteMapping(value = "/{id}")
    @CrossOrigin(origins = "*")
    public void deleteGenerateTest(@PathVariable Integer id) {
        generateTestService.deleteGenerateTest(id);
    }
}
