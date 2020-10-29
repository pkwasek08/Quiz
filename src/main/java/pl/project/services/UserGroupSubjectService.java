package pl.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.project.entities.UserGroupSubject;
import pl.project.repositories.UserGroupSubjectRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserGroupSubjectService {
    @Autowired
    private UserGroupSubjectRepository userGroupSubjectRepository;

    public List<UserGroupSubject> getAllUserGroupSubject() {
        List<UserGroupSubject> userGroupSubjectList = new ArrayList<>();
        userGroupSubjectRepository.findAll().forEach(userGroupSubjectList::add);
        return userGroupSubjectList;
    }

    public UserGroupSubject getUserGroupSubject(Integer id) {
        UserGroupSubject userGroupSubject = userGroupSubjectRepository.findById(id).get();
        return userGroupSubject;
    }

    public void addUserGroupSubject(UserGroupSubject userGroupSubject) {
        userGroupSubjectRepository.save(userGroupSubject);
    }


    public void updateUserGroupSubject(Integer id, UserGroupSubject userGroupSubject) {
        userGroupSubjectRepository.save(userGroupSubject);
    }


    public void deleteUserGroupSubject(Integer id) {
        userGroupSubjectRepository.deleteById(id);
    }
}
