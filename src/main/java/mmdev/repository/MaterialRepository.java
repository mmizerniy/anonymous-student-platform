package mmdev.repository;

import mmdev.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaterialRepository extends JpaRepository<Material,Long> {

    List<Material> findBySubjectId(Long subjectId);

    List<Material> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String titleKeyword,String descriptionKeyword);

}
