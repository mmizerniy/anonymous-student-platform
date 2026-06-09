package mmdev.dto.response;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;


@Data
@Builder
public class MaterialResponse implements Serializable {
    private Long id;
    private String title;
    private String description;
    private String fileUrl;
    private String subjectName;
    private String authorUsername;
}
