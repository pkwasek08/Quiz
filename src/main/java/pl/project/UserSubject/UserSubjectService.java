package pl.project.UserSubject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.project.Subject.Subject;
import pl.project.Subject.SubjectRepository;
import pl.project.User.User;
import pl.project.User.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserSubjectService {
    @Autowired
    private UserSubjectRepository userSubjectRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SubjectRepository subjectRepository;

    public List<UserSubject> getAllUserSubject() {
        List<UserSubject> userSubjectList = new ArrayList<>();
        userSubjectRepository.findAll().forEach(userSubjectList::add);
        return userSubjectList;
    }

    public UserSubject getUserSubject(Integer id) {
        UserSubject userSubject = userSubjectRepository.findById(id).get();
        return userSubject;
    }

    public void addUserSubject(UserSubjectDTO userSubjectDTO) {
        User user = userRepository.findById(userSubjectDTO.getUserId()).get();
        Subject subject = subjectRepository.findById(userSubjectDTO.getSubjectId()).get();
        UserSubject userSubject = new UserSubject(0, subject, user);
        userSubjectRepository.save(userSubject);
    }


    public void updateUserSubject(Integer id, UserSubject userSubject) {
        userSubjectRepository.save(userSubject);
    }


    public void deleteUserSubject(Integer id) {
        userSubjectRepository.deleteById(id);
    }
}
