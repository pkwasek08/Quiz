package pl.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.project.entities.Test;
import pl.project.repositories.TestRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestService {
    @Autowired
    private TestRepository testRepository;

    public List<Test> getAllTest() {
        List<Test> testList = new ArrayList<>();
        testRepository.findAll().forEach(testList::add);
        return testList;
    }

    public Test getTest(Integer id) {
        Test test = testRepository.findById(id).get();
        return test;
    }

    public void addTest(Test test) {
        testRepository.save(test);
    }


    public void updateTest(Integer id, Test test) {
        testRepository.save(test);
    }


    public void deleteTest(Integer id) {
        testRepository.deleteById(id);
    }
}
