package mmdev.controller;


import mmdev.dto.request.CreateSubjectRequest;
import mmdev.dto.request.UpdateSubjectRequest;
import mmdev.dto.response.SubjectResponse;
import mmdev.entity.Subject;
import mmdev.service.SubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SubjectResponse createSubject(@RequestBody CreateSubjectRequest request){
        return subjectService.createSubject(request);
    }

    @GetMapping
    public List<SubjectResponse> getAllSubjects(){
        return subjectService.getAllSubjects();
    }

    @GetMapping("/{id}")
    public SubjectResponse getSubjectById(@PathVariable Long id){
        return subjectService.getSubjectById(id);
    }

    @PutMapping("/{id}")
    public SubjectResponse updateSubject(
            @RequestBody UpdateSubjectRequest request,
            @PathVariable Long id){
        return subjectService.updateSubject(id,request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSubject(@PathVariable Long id){
       subjectService.deleteSubject(id);
    }
}
