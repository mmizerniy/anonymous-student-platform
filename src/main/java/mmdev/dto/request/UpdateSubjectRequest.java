package mmdev.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateSubjectRequest {
    @NotBlank
    @Size(max = 99,min = 1)
    private String name;
    @NotBlank
    @Size(max = 99,min = 1)
    private String teacherName;
    @NotBlank
    private String faculty;
    @NotNull
    private int semester;
}
