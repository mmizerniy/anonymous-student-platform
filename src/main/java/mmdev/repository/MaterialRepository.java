package mmdev.repository;

import mmdev.entity.Comment;
import mmdev.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaterialRepository extends JpaRepository<Material,Long> {

    List<Comment> findBySubjectId(Long materialId);
}
