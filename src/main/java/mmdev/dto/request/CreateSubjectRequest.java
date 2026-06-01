package mmdev.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateSubjectRequest {

    @NotBlank
    @Size(min = 1,max = 99)
    private String name;
    @NotBlank
    @Size(min = 1,max = 99)
    private String teacherName;
    @NotBlank
    private String faculty;
    @NotNull
    private int semester;
}
