package mmdev.mapper;

import mmdev.dto.request.CreateMaterialRequest;
import mmdev.dto.response.MaterialResponse;
import mmdev.entity.Material;

public class MaterialMapper {

    public static MaterialResponse toResponse(Material material){
        return MaterialResponse.builder()
                .id(material.getId())
                .title(material.getTitle())
                .description(material.getDescription())
                .fileUrl(material.getFileUrl())
                .subjectName(material.getSubject().getName())
                .authorUsername(material.getAuthor().getUsername())
                .build();
    }
    public static Material toEntity(CreateMaterialRequest request){
        return Material.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .build();
    }

}
