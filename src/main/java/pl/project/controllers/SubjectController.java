package pl.project.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.project.entities.Subject;
import pl.project.services.SubjectService;

import java.util.List;

@RestController
@RequestMapping(value = "/subject")
public class SubjectController {
    Logger log = LogManager.getLogger(this.getClass());
    @Autowired
    private SubjectService subjectService;

    @GetMapping()
    @CrossOrigin(origins = "*")
    public List<Subject> getSubject() {
        return subjectService.getAllSubject();
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "*")
    public Subject getSubject(@PathVariable Integer id) {
        return subjectService.getSubject(id);
    }

    @PostMapping()
    @CrossOrigin(origins = "*")
    public void addSubject(@RequestBody Subject subject) {
        subjectService.addSubject(subject);
    }

    @PutMapping(value = "/{id}")
    @CrossOrigin(origins = "*")
    public void updateSubject(@RequestBody Subject subject, @PathVariable Integer id) {
        subjectService.updateSubject(id, subject);
    }

    @DeleteMapping(value = "/{id}")
    @CrossOrigin(origins = "*")
    public void deleteSubject(@PathVariable Integer id) {
        subjectService.deleteSubject(id);
    }
}
