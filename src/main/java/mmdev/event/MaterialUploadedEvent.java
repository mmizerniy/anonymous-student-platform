package mmdev.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaterialUploadedEvent {

    private Long materialId;

    private String title;

    private String authorUsername;

}
