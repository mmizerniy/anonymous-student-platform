package mmdev.controller;


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
    public Subject createSubject(@RequestBody Subject subject){
        return subjectService.createSubject(subject);
    }

    @GetMapping
    public List<Subject> getAllSubjects(){
        return subjectService.getAllSubjects();
    }

    @GetMapping("/{id}")
    public Subject getSubjectById(@PathVariable Long id){
        return subjectService.getSubjectById(id);
    }

    @PutMapping("/{id}")
    public Subject updateSubject(
            @RequestBody Subject subject,
            @PathVariable Long id){
        return subjectService.updateSubject(id,subject);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSubject(@PathVariable Long id){
       subjectService.deleteSubject(id);
    }
}
