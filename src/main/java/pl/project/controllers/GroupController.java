package pl.project.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.project.entities.Group;
import pl.project.services.GroupService;
import pl.project.services.UserGroupSubjectService;

import java.util.List;

@RestController
@RequestMapping(value = "/group")
public class GroupController {
    Logger log = LogManager.getLogger(this.getClass());
    @Autowired
    private GroupService groupService;

    @Autowired
    private UserGroupSubjectService userGroupSubjectService;

    @GetMapping()
    @CrossOrigin(origins = "*")
    public List<Group> getGroup() {
        return groupService.getAllGroup();
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "*")
    public Group getGroup(@PathVariable Integer id) {
        return groupService.getGroup(id);
    }

    @PostMapping()
    @CrossOrigin(origins = "*")
    public void addGroup(@RequestBody Group group) {
        groupService.addGroup(group);
    }

    @PutMapping(value = "/{id}")
    @CrossOrigin(origins = "*")
    public void updateGroup(@RequestBody Group group, @PathVariable Integer id) {
        groupService.updateGroup(id, group);
    }

    @DeleteMapping(value = "/{id}")
    @CrossOrigin(origins = "*")
    public void deleteGroup(@PathVariable Integer id) {
        groupService.deleteGroup(id);
    }

    @GetMapping("/teacher/{id}")
    @CrossOrigin(origins = "*")
    public List<Group> getTeacherGroup(@PathVariable Integer id) {
        return userGroupSubjectService.getAllGroupsByTeacherId(id);
    }

    @GetMapping("/student/{id}")
    @CrossOrigin(origins = "*")
    public List<Group> getUserGroup(@PathVariable Integer id) {
        return userGroupSubjectService.getAllGroupsByUserId(id);
    }
}
