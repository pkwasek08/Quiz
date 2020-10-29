package pl.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.project.entities.Subject;
import pl.project.repositories.SubjectRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    public List<Subject> getAllSubject() {
        List<Subject> subjectList = new ArrayList<>();
        subjectRepository.findAll().forEach(subjectList::add);
        return subjectList;
    }

    public Subject getSubject(Integer id) {
        Subject subject = subjectRepository.findById(id).get();
        return subject;
    }

    public void addSubject(Subject subject) {
        subjectRepository.save(subject);
    }


    public void updateSubject(Integer id, Subject subject) {
        subjectRepository.save(subject);
    }


    public void deleteSubject(Integer id) {
        subjectRepository.deleteById(id);
    }
}
