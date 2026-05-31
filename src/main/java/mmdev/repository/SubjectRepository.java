package mmdev.repository;


import mmdev.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject,Long> {

    List<Subject> getSubjectsByFaculty(String faculty);

    List<Subject> getSubjectsByTeacherName(String teacherName);
}
