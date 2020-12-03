package pl.project;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuizServerApplication {
    public static void main(String[] args) throws SchedulerException {
        SpringApplication.run(QuizServerApplication.class, args);
    }

}
