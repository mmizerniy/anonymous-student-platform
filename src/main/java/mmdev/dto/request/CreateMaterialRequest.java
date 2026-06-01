package mmdev.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Data
public class CreateMaterialRequest {
    @NotBlank
    private String title;
    @NotBlank
    @Size(max = 2000)
    private String description;
    @NotBlank
    @URL
    private String fileUrl;
    @NotNull
    private Long subjectId;
    @NotNull
    private Long authorId;
}
