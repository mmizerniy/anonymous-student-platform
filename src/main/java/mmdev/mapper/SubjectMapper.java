package mmdev.mapper;

import mmdev.dto.request.CreateSubjectRequest;
import mmdev.dto.response.SubjectResponse;
import mmdev.entity.Subject;

public class SubjectMapper {

    public static SubjectResponse toResponse(Subject subject){
        return SubjectResponse.builder()
                .id(subject.getId())
                .name(subject.getName())
                .teacherName(subject.getTeacherName())
                .faculty(subject.getFaculty())
                .semester(subject.getSemester())
                .build();
    }
    public static Subject toEntity(CreateSubjectRequest request){
        return Subject.builder()
                .name(request.getName())
                .teacherName(request.getTeacherName())
                .faculty(request.getFaculty())
                .semester(request.getSemester())
                .build();
    }

}
