package pl.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.project.entities.Group;
import pl.project.entities.Subject;
import pl.project.entities.User;
import pl.project.entities.UserGroupSubject;
import pl.project.repositories.UserGroupSubjectRepository;
import pl.project.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserGroupSubjectService {
    @Autowired
    private UserGroupSubjectRepository userGroupSubjectRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Group> getAllGroupsByUserId(int id){
        List<Group> groups = new ArrayList<>();
        Optional<User> user = userRepository.findById(id);
        List<UserGroupSubject> userGroupSubjects = userGroupSubjectRepository.getAllByUserByUserId(user.get());
        if(userGroupSubjects.size() > 0)
            for (UserGroupSubject ugs:userGroupSubjects
                 ) {
                groups.add(ugs.getGroupByGroupId());
            }
        return groups;
    }

    public List<Subject> getAllSubjectsByUserId(int id){
        List<Subject> subjects = new ArrayList<>();
        Optional<User> user = userRepository.findById(id);
        List<UserGroupSubject> userGroupSubjects = userGroupSubjectRepository.getAllByUserByUserId(user.get());
        if(userGroupSubjects.size() > 0)
            for (UserGroupSubject ugs:userGroupSubjects
            ) {
                subjects.add(ugs.getSubjectBySubjectId());
            }
        return subjects;
    }

    public List<Group> getAllGroupsByTeacherId(int id){
        List<Group> groups = new ArrayList<>();
        Optional<User> user = userRepository.findById(id);
        List<UserGroupSubject> userGroupSubjects = userGroupSubjectRepository.getAllByUserByTeacherId(user.get());
        if(userGroupSubjects.size() > 0)
            for (UserGroupSubject ugs:userGroupSubjects
            ) {
                groups.add(ugs.getGroupByGroupId());
            }
        return groups;
    }

    public List<Subject> getAllSubjectsByTeacherId(int id){
        List<Subject> subjects = new ArrayList<>();
        Optional<User> user = userRepository.findById(id);
        List<UserGroupSubject> userGroupSubjects = userGroupSubjectRepository.getAllByUserByTeacherId(user.get());
        if(userGroupSubjects.size() > 0)
            for (UserGroupSubject ugs:userGroupSubjects
            ) {
                subjects.add(ugs.getSubjectBySubjectId());
            }
        return subjects;
    }

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
