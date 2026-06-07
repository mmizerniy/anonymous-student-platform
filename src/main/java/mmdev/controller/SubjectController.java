package mmdev.controller;


import jakarta.validation.Valid;
import mmdev.dto.request.CreateSubjectRequest;
import mmdev.dto.request.UpdateSubjectRequest;
import mmdev.dto.response.SubjectResponse;
import mmdev.service.SubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
    public SubjectResponse createSubject(@Valid  @RequestBody CreateSubjectRequest request){
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
            @Valid @RequestBody UpdateSubjectRequest request,
            @PathVariable Long id){
        return subjectService.updateSubject(id,request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteSubject(@PathVariable Long id){
       subjectService.deleteSubject(id);
    }
}
