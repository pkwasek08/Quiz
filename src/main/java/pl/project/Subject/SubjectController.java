package pl.project.Subject;

import io.swagger.annotations.ApiParam;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.project.UserGroupSubject.UserGroupSubjectService;

import java.util.List;

@RestController
@RequestMapping(value = "/subject")
public class SubjectController {
    Logger log = LogManager.getLogger(this.getClass());
    @Autowired
    private SubjectService subjectService;

    @Autowired
    private UserGroupSubjectService userGroupSubjectService;

    @GetMapping()
    @ApiParam(value="tests", hidden=true, required=false)
    @CrossOrigin(origins = "*")
    public List<SubjectDTO> getSubject() {
        return subjectService.getAllSubject();
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "*")
    public SubjectDTO getSubject(@PathVariable Integer id) {
        return subjectService.getSubject(id);
    }

    @PostMapping()
    @CrossOrigin(origins = "*")
    public void addSubject(@RequestBody SubjectDTO subjectDTO) {
        subjectService.addSubject(subjectDTO);
    }

    @PutMapping(value = "/{id}")
    @CrossOrigin(origins = "*")
    public void updateSubject(@RequestBody SubjectDTO subjectDTO, @PathVariable Integer id) {
        subjectService.updateSubject(id, subjectDTO);
    }

    @DeleteMapping(value = "/{id}")
    @CrossOrigin(origins = "*")
    public void deleteSubject(@PathVariable Integer id) {
        subjectService.deleteSubject(id);
    }

    @GetMapping("/teacher/{id}")
    @CrossOrigin(origins = "*")
    public List<SubjectDTO> getTeacherSubject(@PathVariable Integer id) {
        return userGroupSubjectService.getAllSubjectsByTeacherId(id);
    }

    @GetMapping("/student/{id}")
    @CrossOrigin(origins = "*")
    public List<SubjectDTO> getUserSubject(@PathVariable Integer id) {
        return userGroupSubjectService.getAllSubjectsByUserId(id);
    }
}
