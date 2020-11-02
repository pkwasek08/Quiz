package pl.project.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.project.dao.TestDao;
import pl.project.entities.Test;
import pl.project.repositories.TestRepository;

import java.util.*;

@Service
public class TestService {
    Logger log = LogManager.getLogger(this.getClass());

    @Autowired
    private TestRepository testRepository;
    @Autowired
    private TestDao testDao;
    public List<Test> getAllTest() {
        List<Test> testList = new ArrayList<>();
        testRepository.findAll().forEach(testList::add);
        return testList;
    }

    public List<Test> getAllTestBySubject(Integer subject_id) {
        return testRepository.findAllBySubjectBySubjectId_Id(subject_id);
    }

    public List<Test> getAllTestBySubjectAndGroup(Integer subjectId, Integer groupId) {
        return testDao.findAllTestsBySubjectIdAndGroupId(subjectId,groupId);
    }

    public Test getTest(Integer id) {
        return testRepository.findById(id).get();
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
