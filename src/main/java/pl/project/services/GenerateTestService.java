package pl.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.project.entities.GenerateTest;
import pl.project.entities.Result;
import pl.project.entities.User;
import pl.project.repositories.GenerateTestRepository;
import pl.project.repositories.ResultRepository;
import pl.project.repositories.UserRepository;

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
        Optional<User> user =  userRepository.findById(id);
        if(!user.isPresent()) return null;
        List<Result> results = resultRepository.findAllByUserByUserId(user.get());
        List<GenerateTest> generateTests = new ArrayList<>();
        for (Result result: results
             ) {
            generateTests.add(result.getGenerateTestsByGenerateTestId());
        }
        return generateTests;
    }

    public void addGenerateTest(GenerateTest generateTest) {
        generateTestRepository.save(generateTest);
    }


    public void updateGenerateTest(Integer id, GenerateTest generateTest) {
        generateTestRepository.save(generateTest);
    }


    public void deleteGenerateTest(Integer id) {
        generateTestRepository.deleteById(id);
    }
}
