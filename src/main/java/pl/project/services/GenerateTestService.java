package pl.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.project.entities.GenerateTest;
import pl.project.repositories.GenerateTestRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenerateTestService {
    @Autowired
    private GenerateTestRepository generateTestRepository;

    public List<GenerateTest> getAllGenerateTest() {
        List<GenerateTest> generateTestList = new ArrayList<>();
        generateTestRepository.findAll().forEach(generateTestList::add);
        return generateTestList;
    }

    public GenerateTest getGenerateTest(Integer id) {
        GenerateTest generateTest = generateTestRepository.findById(id).get();
        return generateTest;
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
