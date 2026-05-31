package mmdev.service;


import mmdev.entity.Subject;
import mmdev.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;


    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }


    public Subject createSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    public Subject getSubjectById(Long id) {
        return subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public List<Subject> getAllSubjects(){
        return subjectRepository.findAll();
    }

    public Subject updateSubject(Long id,Subject subject){
        Subject oldSubject = subjectRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Subject not found: " + id));
        oldSubject.setName(subject.getName());
        oldSubject.setTeacherName(subject.getTeacherName());
        oldSubject.setFaculty(subject.getFaculty());
        oldSubject.setSemester(subject.getSemester());

        return subjectRepository.save(oldSubject);
    }

    public void updateSubject(Long id){
        if (!subjectRepository.existsById(id)){
            throw new RuntimeException("Subject not found with id: " + id);
        }
        subjectRepository.deleteById(id);
    }

}
