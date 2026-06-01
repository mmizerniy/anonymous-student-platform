package mmdev.controller;


import mmdev.dto.request.CreateMaterialRequest;
import mmdev.dto.request.UpdateMaterialRequest;
import mmdev.dto.response.MaterialResponse;
import mmdev.service.MaterialService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materials")
public class MaterialController {

    private final MaterialService materialService;


    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MaterialResponse createMaterial(@RequestBody CreateMaterialRequest request){
        return materialService.createMaterial(request);
    }

    @GetMapping
    public List<MaterialResponse> getAllMaterials(){
        return materialService.getAllMaterials();
    }

    @GetMapping("/{id}")
    public MaterialResponse getMaterialById(@PathVariable Long id){
        return materialService.getMaterialById(id);
    }

    @PutMapping("/{id}")
    public MaterialResponse updateMaterial(
            @PathVariable Long id,
            @RequestBody UpdateMaterialRequest request
    ){
        return materialService.updateMaterial(id,request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMaterial(@PathVariable Long id){
        materialService.deleteMaterial(id);
    }
    @GetMapping("/subject/{subjectId}")
    public List<MaterialResponse> getMaterialsBySubject(@PathVariable Long subjectId){
        return materialService.getMaterialsBySubject(subjectId);
    }

}
