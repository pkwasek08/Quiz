package pl.project.Test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.project.GenerateTest.GenerateTest;
import pl.project.GenerateTest.GenerateTestService;
import pl.project.Subject.SubjectRepository;
import pl.project.Test.TestDao;
import pl.project.Test.Test;
import pl.project.Test.TestRepository;
import pl.project.User.User;
import pl.project.User.UserDao;
import pl.project.User.UserRepository;
import pl.project.payload.response.MessageResponse;


import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
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
    private UserDao userDao;
    @Autowired
    private TestDao testDao;


    @Autowired
    private Scheduler scheduler;

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

    public ResponseEntity addTest(TestDTO testDTO) throws SchedulerException {
        Test test = new Test(0, testDTO.getName(), testDTO.getFullPoints(), testDTO.getDate(), testDTO.getTime(), subjectRepository.findById(testDTO.getSubjectId()).get());
        if(sendEmail(test)) {
            testRepository.save(test);
            return ResponseEntity.ok(new MessageResponse("Test added correctly!"));
        } else {
            return ResponseEntity.badRequest().body(new MessageResponse("Test should take place at least 24 hours from now!"));
        }
    }

    private boolean sendEmail(Test test) throws SchedulerException {
        List<User> users = userDao.findAllUsersBySubjectId(test.getSubject().getId());
        ZoneId poland = ZoneId.of("Poland");
        int hours = 24;
        Date emailDate = new Date(test.getDate().getTime() - hours * 60 * 60 * 1000);
        ZonedDateTime dateTime = ZonedDateTime.of(emailDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(), poland);
        if(dateTime.isBefore(ZonedDateTime.now())){
            return false;
        }
        if(users.isEmpty()) return true;
        System.out.println("Job added on time " + dateTime.toString());
        JobDetail jobDetail = buildJobDetail(test, users);
        Trigger trigger = buildJobTrigger(jobDetail, dateTime);
        scheduler.scheduleJob(jobDetail, trigger);
        return true;
    }

    private JobDetail buildJobDetail(Test test, List<User> users) {
        JobDataMap jobDataMap = new JobDataMap();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(users.get(0).getEmail());
        for(int i = 1; i < users.size(); i++){
            stringBuilder.append(" ").append(users.get(i).getEmail());
        }
        String emails = stringBuilder.toString();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateString = format.format(new Date(test.getDate().getTime()));

        jobDataMap.put("email", emails);
        jobDataMap.put("subject", test.getSubject().getName());
        jobDataMap.put("body", "Exam at " + dateString + "\nTime: " + test.getTime());

        return JobBuilder.newJob(EmailJob.class)
                .withIdentity(UUID.randomUUID().toString(), "email-jobs")
                .withDescription("Send Email Job")
                .usingJobData(jobDataMap)
                .storeDurably()
                .build();
    }

    private Trigger buildJobTrigger(JobDetail jobDetail, ZonedDateTime startAt) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(jobDetail.getKey().getName(), "email-triggers")
                .withDescription("Send Email Trigger")
                .startAt(Date.from(startAt.toInstant()))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionFireNow())
                .build();
    }

    public void updateTest(Integer id, TestDTO testDTO) {
        Test test = new Test(testDTO.getId(), testDTO.getName(), testDTO.getFullPoints(), testDTO.getDate(), testDTO.getTime(), subjectRepository.findById(testDTO.getSubjectId()).get());
        testRepository.save(test);
    }

    public List<TestDTO> getTestsByUserIdAndSubjectId(Integer userId, Integer subjectId){
        List<GenerateTest> generateTests = generateTestService.getGenerateTestsByUserId(userId);
        List<TestDTO> tests = new ArrayList<>();
        for (GenerateTest generateTest: generateTests) {
            Test test = generateTest.getTest();
            if(test.getSubject().getId() == subjectId){
                tests.add(new TestDTO(test));
            }
        }
        return tests;
    }


    public void deleteTest(Integer id) {
        testRepository.deleteById(id);
    }
}
