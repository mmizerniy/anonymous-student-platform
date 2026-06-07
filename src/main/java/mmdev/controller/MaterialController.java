package mmdev.controller;


import jakarta.validation.Valid;
import mmdev.dto.request.CreateMaterialRequest;
import mmdev.dto.request.UpdateMaterialRequest;
import mmdev.dto.response.MaterialResponse;
import mmdev.security.CustomUserDetails;
import mmdev.service.FileStorageService;
import mmdev.service.MaterialService;
import org.cef.callback.CefContextMenuParams;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/materials")
public class MaterialController {

    private final MaterialService materialService;
    private final FileStorageService fileStorageService;

    public MaterialController(MaterialService materialService,
                              FileStorageService fileStorageService) {
        this.materialService = materialService;
        this.fileStorageService = fileStorageService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('STUDENT')")
    public MaterialResponse createMaterial(
            @RequestPart("data") @Valid CreateMaterialRequest request,
            @RequestPart("file") MultipartFile file,
            @AuthenticationPrincipal CustomUserDetails currentUser) {
        return materialService.createMaterial(request, file, currentUser.getUsername());
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('STUDENT','MODERATOR','ADMIN')")
    public List<MaterialResponse> getAllMaterials() {
        return materialService.getAllMaterials();
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public MaterialResponse getMaterialById(@PathVariable Long id) {
        return materialService.getMaterialById(id);
    }

    @PutMapping("/{id}")
    public MaterialResponse updateMaterial(
            @PathVariable Long id,
            @Valid @RequestBody UpdateMaterialRequest request
    ) {
        return materialService.updateMaterial(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteMaterial(@PathVariable Long id) {
        materialService.deleteMaterial(id);
    }

    @GetMapping("/subject/{subjectId}")
    public List<MaterialResponse> getMaterialsBySubject(@PathVariable Long subjectId) {
        return materialService.getMaterialsBySubject(subjectId);
    }
    @GetMapping("/files/{fileName:.+}")
    @PreAuthorize("hasAnyRole('STUDENT','MODERATOR','ADMIN')")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName){
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('STUDENT','MODERATOR','ADMIN')")
    public List<MaterialResponse> searchMaterials(@RequestParam String keyword){
        return materialService.searchMaterials(keyword);
    }

}
