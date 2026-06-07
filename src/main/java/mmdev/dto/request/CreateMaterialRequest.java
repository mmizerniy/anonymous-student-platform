package mmdev.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateMaterialRequest {
    @NotBlank
    private String title;
    @NotBlank
    @Size(max = 2000)
    private String description;
    @NotNull
    private Long subjectId;
}
