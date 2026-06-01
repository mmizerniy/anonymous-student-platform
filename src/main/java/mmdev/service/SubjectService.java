package mmdev.service;


import mmdev.dto.request.CreateSubjectRequest;
import mmdev.dto.request.UpdateSubjectRequest;
import mmdev.dto.response.SubjectResponse;
import mmdev.entity.Subject;
import mmdev.exception.ResourceNotFoundException;
import mmdev.mapper.SubjectMapper;
import mmdev.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;


    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }


    public SubjectResponse createSubject(CreateSubjectRequest request) {
        Subject subject = SubjectMapper.toEntity(request);

        Subject savedSubject = subjectRepository.save(subject);

        return SubjectMapper.toResponse(savedSubject);
    }

    public SubjectResponse getSubjectById(Long id) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return SubjectMapper.toResponse(subject);
    }

    public List<SubjectResponse> getAllSubjects(){
        return subjectRepository.findAll()
                .stream()
                .map(SubjectMapper::toResponse)
                .toList();
    }

    public SubjectResponse updateSubject(Long id, UpdateSubjectRequest request){
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Subject not found: " + id));
        subject.setSemester(request.getSemester());
        subject.setTeacherName(request.getTeacherName());
        subject.setName(request.getName());
        subject.setFaculty(request.getFaculty());

        Subject savedSubject = subjectRepository.save(subject);

        return SubjectMapper.toResponse(savedSubject);
    }

    public void deleteSubject(Long id){
        if (!subjectRepository.existsById(id)){
            throw new ResourceNotFoundException("Subject not found with id: " + id);
        }
        subjectRepository.deleteById(id);
    }

}
