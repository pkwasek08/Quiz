package pl.project.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.project.dao.TestDao;
import pl.project.entities.Subject;
import pl.project.entities.Test;
import pl.project.repositories.SubjectRepository;
import pl.project.repositories.TestRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TestService {
    Logger log = LogManager.getLogger(this.getClass());

    @Autowired
    private TestRepository testRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private TestDao testDao;
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

    public List<Test> getAllTestBySubjectAndGroup(Integer subjectId, Integer groupId) {
        return testDao.findAllTestsBySubjectIdAndGroupId(subjectId,groupId);
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
