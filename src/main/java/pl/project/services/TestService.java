package pl.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.project.entities.Subject;
import pl.project.entities.Test;
import pl.project.repositories.SubjectRepository;
import pl.project.repositories.TestRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TestService {
    @Autowired
    private TestRepository testRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    public List<Test> getAllTest() {
        List<Test> testList = new ArrayList<>();
        testRepository.findAll().forEach(testList::add);
        return testList;
    }

    public List<Test> getAllTestBySubject(int subject_id) {
        List<Test> testList;
        Optional<Subject> subject = subjectRepository.findById(subject_id);
        if(!subject.isPresent()) return null;
        testList = testRepository.findAllBySubjectBySubjectId(subject.get());
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
