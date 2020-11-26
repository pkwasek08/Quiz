package pl.project.Test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.project.GenerateTest.GenerateTest;
import pl.project.GenerateTest.GenerateTestService;
import pl.project.Result.Result;
import pl.project.Result.ResultRepository;
import pl.project.Subject.SubjectRepository;
import pl.project.Test.TestDao;
import pl.project.Test.Test;
import pl.project.Test.TestRepository;

import java.util.*;

@Service
public class TestService {
    Logger log = LogManager.getLogger(this.getClass());

    @Autowired
    private TestRepository testRepository;
    @Autowired
    private GenerateTestService generateTestService;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private TestDao testDao;

    public List<Test> getAllTest() {
        List<Test> testList = new ArrayList<>();
        testRepository.findAll().forEach(testList::add);
        return testList;
    }

    public List<Test> getAllTestBySubject(Integer subject_id) {
        return testRepository.findAllBySubject_Id(subject_id);
    }

    public List<Test> getAllTestBySubjectAndGroup(Integer subjectId, Integer groupId) {
        return testDao.findAllTestsBySubjectIdAndGroupId(subjectId,groupId);
    }

    public Test getTest(Integer id) {
        return testRepository.findById(id).get();
    }

    public void addTest(TestDTO testDTO) {
        Test test = new Test(0, testDTO.getName(), testDTO.getFullPoints(), testDTO.getDate(), testDTO.getTime(), subjectRepository.findById(testDTO.getSubjectId()).get());
        testRepository.save(test);
    }


    public void updateTest(Integer id, TestDTO testDTO) {
        Test test = new Test(testDTO.getId(), testDTO.getName(), testDTO.getFullPoints(), testDTO.getDate(), testDTO.getTime(), subjectRepository.findById(testDTO.getSubjectId()).get());
        testRepository.save(test);
    }

    public List<Test> getTestsByUserIdAndSubjectId(Integer userId, Integer subjectId){
        List<GenerateTest> generateTests = generateTestService.getGenerateTestsByUserId(userId);
        List<Test> tests = new ArrayList<>();
        for (GenerateTest generateTest: generateTests) {
            Test test = generateTest.getTest();
            if(test.getSubject().getId() == subjectId){
                tests.add(test);
            }
        }
        return tests;
    }


    public void deleteTest(Integer id) {
        testRepository.deleteById(id);
    }
}
