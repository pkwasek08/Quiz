package pl.project.GenerateTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.project.Result.Result;
import pl.project.Test.TestRepository;
import pl.project.User.User;
import pl.project.Result.ResultRepository;
import pl.project.User.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GenerateTestService {
    @Autowired
    private GenerateTestRepository generateTestRepository;

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestRepository testRepository;

    public List<GenerateTest> getAllGenerateTest() {
        List<GenerateTest> generateTestList = new ArrayList<>();
        generateTestRepository.findAll().forEach(generateTestList::add);
        return generateTestList;
    }

    public GenerateTest getGenerateTest(Integer id) {
        GenerateTest generateTest = generateTestRepository.findById(id).get();
        return generateTest;
    }

    public List<GenerateTest> getGenerateTestsByUserId(Integer id) {
        List<Result> results = resultRepository.findAllByUser_Id(id);
        List<GenerateTest> generateTests = new ArrayList<>();
        for (Result result: results
             ) {
            generateTests.add(result.getGenerateTest());
        }
        return generateTests;
    }

    public GenerateTest addGenerateTest(GenerateTestDTO generateTestDTO) {
        GenerateTest generateTest = new GenerateTest(0, testRepository.findById(generateTestDTO.getTestId()).get());
        return generateTestRepository.save(generateTest);
    }

    public int getFullPoints(Integer generateTestId) {
        return generateTestRepository.getFullPoints(generateTestId);
    }

    public void updateGenerateTest(Integer id, GenerateTestDTO generateTestDTO) {
        GenerateTest generateTest = new GenerateTest(generateTestDTO.getId(), testRepository.findById(generateTestDTO.getTestId()).get());
        generateTestRepository.save(generateTest);
    }


    public void deleteGenerateTest(Integer id) {
        generateTestRepository.deleteById(id);
    }
}
