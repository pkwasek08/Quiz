package pl.project.Subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

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
        Subject subject = new Subject(0, subjectDTO.getName());
        subjectRepository.save(subject);
    }


    public void updateSubject(Integer id, SubjectDTO subjectDTO) {
        Subject subject = new Subject(subjectDTO.getId(), subjectDTO.getName());
        subjectRepository.save(subject);
    }


    public void deleteSubject(Integer id) {
        subjectRepository.deleteById(id);
    }
}
