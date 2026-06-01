package mmdev.dto.response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubjectResponse {
    private Long id;
    private String name;
    private String teacherName;
    private String faculty;
    private int semester;
}
