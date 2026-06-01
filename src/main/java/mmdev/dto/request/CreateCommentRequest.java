package mmdev.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateCommentRequest {
    @NotBlank
    @Size(min = 3)
    private String text;
    @NotBlank
    private Long materialId;
    @NotBlank
    private Long authorId;
}
