package pl.project.Subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.project.User.User;
import pl.project.User.UserRepository;
import pl.project.UserSubject.UserSubject;
import pl.project.UserSubject.UserSubjectRepository;
import pl.project.security.MyUserDetails;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserSubjectRepository userSubjectRepository;

    public List<SubjectDTO> getAllSubject() {
        List<SubjectDTO> subjectList = new ArrayList<>();
        for (Subject subject: subjectRepository.findAll()
             ) {
            subjectList.add(new SubjectDTO(subject));
        }
        return subjectList;
    }

    public SubjectDTO getSubject(Integer id) {
        Subject subject = subjectRepository.findById(id).get();
        return new SubjectDTO(subject);
    }

    public void addSubject(SubjectDTO subjectDTO) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int userId = ((MyUserDetails) principal).getId();
        Subject subject = new Subject(0, subjectDTO.getName());
        subjectRepository.save(subject);
        User user = userRepository.findById(userId).get();
        UserSubject userSubject = new UserSubject(0, subject, user);
        userSubjectRepository.save(userSubject);
    }


    public void updateSubject(Integer id, SubjectDTO subjectDTO) {
        Subject subject = new Subject(subjectDTO.getId(), subjectDTO.getName());
        subjectRepository.save(subject);
    }


    public void deleteSubject(Integer id) {
        subjectRepository.deleteById(id);
    }
}
