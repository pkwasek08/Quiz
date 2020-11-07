package pl.project.UserSubject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserSubjectService {
    @Autowired
    private UserSubjectRepository userSubjectRepository;

    public List<UserSubject> getAllUserSubject() {
        List<UserSubject> userSubjectList = new ArrayList<>();
        userSubjectRepository.findAll().forEach(userSubjectList::add);
        return userSubjectList;
    }

    public UserSubject getUserSubject(Integer id) {
        UserSubject userSubject = userSubjectRepository.findById(id).get();
        return userSubject;
    }

    public void addUserSubject(UserSubject userSubject) {
        userSubjectRepository.save(userSubject);
    }


    public void updateUserSubject(Integer id, UserSubject userSubject) {
        userSubjectRepository.save(userSubject);
    }


    public void deleteUserSubject(Integer id) {
        userSubjectRepository.deleteById(id);
    }
}
