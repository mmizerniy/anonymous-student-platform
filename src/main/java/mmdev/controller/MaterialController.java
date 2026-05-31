package mmdev.controller;


import mmdev.entity.Material;
import mmdev.service.MaterialService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api.materials")
public class MaterialController {

    private final MaterialService materialService;


    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Material createMaterial(@RequestBody Material material){
        return materialService.createMaterial(material);
    }

    @GetMapping
    public List<Material> getAllMaterials(){
        return materialService.getAllMaterials();
    }

    @GetMapping("/{id}")
    public Material getMaterialById(@PathVariable Long id){
        return materialService.getMaterialById(id);
    }

    @PutMapping("/{id}")
    public Material updateMaterial(
            @PathVariable Long id,
            @RequestBody Material material
    ){
        return materialService.updateMaterial(id,material);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMaterial(@PathVariable Long id){
        materialService.deleteMaterial(id);
    }
    @GetMapping("/subject/{subjectId}")
    public List<Material> getMaterialsBySubject(@PathVariable Long subjectId){
        return materialService.getMaterialsBySubject(subjectId);
    }

}
