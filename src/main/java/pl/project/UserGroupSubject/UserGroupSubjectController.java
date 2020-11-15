package pl.project.UserGroupSubject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/userGroupSubject")
public class UserGroupSubjectController {
    Logger log = LogManager.getLogger(this.getClass());
    @Autowired
    private UserGroupSubjectService userGroupSubjectService;

    @GetMapping()
    @CrossOrigin(origins = "*")
    public List<UserGroupSubject> getAllUser() {
        return userGroupSubjectService.getAllUserGroupSubject();
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "*")
    public UserGroupSubject getUserGroupSubject(@PathVariable Integer id) {
        return userGroupSubjectService.getUserGroupSubject(id);
    }

    @PostMapping()
    @CrossOrigin(origins = "*")
    public void addUserGroupSubject(@RequestBody UserGroupSubjectDTO userGroupSubjectDTO) {
        userGroupSubjectService.addUserGroupSubject(userGroupSubjectDTO);
    }

    @PutMapping(value = "/{id}")
    @CrossOrigin(origins = "*")
    public void updateUserGroupSubject(@RequestBody UserGroupSubjectDTO userGroupSubjectDTO, @PathVariable Integer id) {
        userGroupSubjectService.updateUserGroupSubject(id, userGroupSubjectDTO);
    }

    @DeleteMapping(value = "/{id}")
    @CrossOrigin(origins = "*")
    public void deleteUserGroupSubject(@PathVariable Integer id) {
        userGroupSubjectService.deleteUserGroupSubject(id);
    }


}













