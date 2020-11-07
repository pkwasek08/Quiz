package pl.project.UserSubject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/userSubject")
public class UserSubjectController {
    Logger log = LogManager.getLogger(this.getClass());
    @Autowired
    private UserSubjectService userSubjectService;

    @GetMapping()
    @CrossOrigin(origins = "*")
    public List<UserSubject> getUser() {
        return userSubjectService.getAllUserSubject();
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "*")
    public UserSubject getUser(@PathVariable Integer id) {
        return userSubjectService.getUserSubject(id);
    }

    @PostMapping()
    @CrossOrigin(origins = "*")
    public void addUser(@RequestBody UserSubject userSubject) {
        userSubjectService.addUserSubject(userSubject);
    }

    @PutMapping(value = "/{id}")
    @CrossOrigin(origins = "*")
    public void updateUser(@RequestBody UserSubject userSubject, @PathVariable Integer id) {
        userSubjectService.updateUserSubject(id, userSubject);
    }

    @DeleteMapping(value = "/{id}")
    @CrossOrigin(origins = "*")
    public void deleteUser(@PathVariable Integer id) {
        userSubjectService.deleteUserSubject(id);
    }
}
