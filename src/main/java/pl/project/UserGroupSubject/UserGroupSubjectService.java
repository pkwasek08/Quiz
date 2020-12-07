package pl.project.UserGroupSubject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.project.Group.Group;
import pl.project.Group.GroupRepository;
import pl.project.Subject.Subject;
import pl.project.Subject.SubjectDTO;
import pl.project.Subject.SubjectRepository;
import pl.project.User.User;
import pl.project.User.UserRepository;
import pl.project.UserSubject.UserSubject;
import pl.project.UserSubject.UserSubjectRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserGroupSubjectService {
    @Autowired
    private UserGroupSubjectRepository userGroupSubjectRepository;

    @Autowired
    private UserSubjectRepository userSubjectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    public List<Group> getAllGroupsByUserId(int id){
        List<Group> groups = new ArrayList<>();
        Optional<User> user = userRepository.findById(id);
        List<UserGroupSubject> userGroupSubjects = userGroupSubjectRepository.getAllByUser(user.get());
        if(userGroupSubjects.size() > 0)
            for (UserGroupSubject ugs:userGroupSubjects
                 ) {
                groups.add(ugs.getGroup());
            }
        return groups;
    }

    public List<SubjectDTO> getAllSubjectsByUserId(int id){
        List<SubjectDTO> subjects = new ArrayList<>();
        Optional<User> user = userRepository.findById(id);
        List<UserGroupSubject> userGroupSubjects = userGroupSubjectRepository.getAllByUser(user.get());
        if(userGroupSubjects.size() > 0)
            for (UserGroupSubject ugs:userGroupSubjects
            ) {
                subjects.add(new SubjectDTO(ugs.getSubject()));
            }
        return subjects;
    }

    public List<Group> getAllGroupsByTeacherId(int id){
        List<Group> groups = new ArrayList<>();
        Optional<User> user = userRepository.findById(id);
        List<UserGroupSubject> userGroupSubjects = userGroupSubjectRepository.getAllByUserTeacher(user.get());
        if(userGroupSubjects.size() > 0)
            for (UserGroupSubject ugs:userGroupSubjects
            ) {
                groups.add(ugs.getGroup());
            }
        return groups;
    }

    public List<SubjectDTO> getAllSubjectsByTeacherId(int id){
        List<SubjectDTO> subjects = new ArrayList<>();
        Optional<User> user = userRepository.findById(id);
        List<UserSubject> userSubjects = userSubjectRepository.findAllByUserByUserId(user.get());
        if(userSubjects.size() > 0)
            for (UserSubject us:userSubjects
            ) {
                subjects.add(new SubjectDTO(us.getSubjectBySubjectId()));
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

    public void addUserGroupSubject(UserGroupSubjectDTO userGroupSubjectDTO) {
        UserGroupSubject userGroupSubject = new UserGroupSubject(0, userRepository.findById(userGroupSubjectDTO.getUserId()).get(),
                groupRepository.findById(userGroupSubjectDTO.getGroupId()).get(), subjectRepository.findById(userGroupSubjectDTO.getSubjectId()).get(),
                userRepository.findById(userGroupSubjectDTO.getTeacherId()).get());
        userGroupSubjectRepository.save(userGroupSubject);
    }

    public void updateUserGroupSubject(Integer id, UserGroupSubjectDTO userGroupSubjectDTO) {
        UserGroupSubject userGroupSubject = new UserGroupSubject(userGroupSubjectDTO.getId(), userRepository.findById(userGroupSubjectDTO.getUserId()).get(),
                groupRepository.findById(userGroupSubjectDTO.getGroupId()).get(), subjectRepository.findById(userGroupSubjectDTO.getSubjectId()).get(),
                userRepository.findById(userGroupSubjectDTO.getTeacherId()).get());
        userGroupSubjectRepository.save(userGroupSubject);
    }


    public void deleteUserGroupSubject(Integer id) {
        userGroupSubjectRepository.deleteById(id);
    }
}
