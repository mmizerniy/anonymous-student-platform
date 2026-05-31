package mmdev.service;

import mmdev.entity.Material;
import mmdev.repository.MaterialRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialService {

    private final MaterialRepository materialRepository;


    public MaterialService(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }


    public Material createMaterial(Material material){
        return materialRepository.save(material);
    }

    public List<Material> getAllMaterials(){
        return materialRepository.findAll();
    }

    public Material getMaterialById(Long id){
        return materialRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Not found " + id));
    }

    public void deleteMaterial(Long id){
        if (!materialRepository.existsById(id)){
            throw new RuntimeException("Material not found with id: " + id);
        }
        materialRepository.deleteById(id);
        System.out.println("Delete material with id: " + id);
    }
    public Material updateMaterial(Long id,Material material){
        Material oldMaterial = materialRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Not found with id " + id));
        oldMaterial.setTitle(material.getTitle());
        oldMaterial.setDescription(oldMaterial.getDescription());
        oldMaterial.setFileUrl(oldMaterial.getFileUrl());

        return materialRepository.save(oldMaterial);
    }
    public List<Material> getMaterialsBySubject(Long subjectId){
        return materialRepository.findSubjectsById(subjectId);
    }

}
