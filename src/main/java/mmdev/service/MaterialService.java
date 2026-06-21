package mmdev.service;

import mmdev.dto.request.CreateMaterialRequest;
import mmdev.dto.request.UpdateMaterialRequest;
import mmdev.dto.response.MaterialResponse;
import mmdev.entity.Material;
import mmdev.entity.Subject;
import mmdev.entity.User;
import mmdev.event.MaterialUploadedEvent;
import mmdev.exception.ResourceNotFoundException;
import mmdev.mapper.MaterialMapper;
import mmdev.repository.MaterialRepository;
import mmdev.repository.SubjectRepository;
import mmdev.repository.UserRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class MaterialService {

    private final MaterialRepository materialRepository;
    private final SubjectRepository subjectRepository;
    private final UserRepository userRepository;
    private final FileStorageService fileStorageService;
    private final KafkaTemplate<String, Object> kafkaTemplate;


    public MaterialService(MaterialRepository materialRepository,
                           SubjectRepository subjectRepository,
                           UserRepository userRepository,
                           FileStorageService fileStorageService,
                           KafkaTemplate<String, Object> kafkaTemplate) {
        this.materialRepository = materialRepository;
        this.subjectRepository = subjectRepository;
        this.userRepository = userRepository;
        this.fileStorageService = fileStorageService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @CacheEvict(value = "materialsBySubject", key = "#request.getSubjectId()")
    public MaterialResponse createMaterial(CreateMaterialRequest request,
                                           MultipartFile file,
                                           String authorUsername) {

        Subject subject = subjectRepository.findById(request.getSubjectId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Subject not found"));
        User author = userRepository.findByUsername(authorUsername)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        if (author.getUniversity() == null) {
            throw new IllegalArgumentException("In your profile not chosen university");
        }
        if (subject.getUniversity() == null) {
            throw new IllegalArgumentException("In this subject not chosen university");
        }
        if (!author.getUniversity().getId().equals(subject.getUniversity().getId())) {
            throw new AccessDeniedException("You can add materials only for your university!");
        }

        String fileName = fileStorageService.saveFile(file);

        Material material = MaterialMapper.toEntity(request);

        material.setSubject(subject);
        material.setAuthor(author);
        material.setFileUrl(fileName);

        Material savedMaterial = materialRepository.save(material);

        MaterialUploadedEvent event = new MaterialUploadedEvent(
                savedMaterial.getId(),
                savedMaterial.getTitle(),
                authorUsername
        );

        kafkaTemplate.send("material-events",event);

        return MaterialMapper.toResponse(savedMaterial);
    }

    public List<MaterialResponse> getAllMaterials() {
        return materialRepository.findAll()
                .stream()
                .map(MaterialMapper::toResponse)
                .toList();
    }

    public MaterialResponse getMaterialById(Long id) {
        Material material = materialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found " + id));
        return MaterialMapper.toResponse(material);
    }

    public void deleteMaterial(Long id) {
        if (!materialRepository.existsById(id)) {
            throw new ResourceNotFoundException("Material not found with id: " + id);
        }
        materialRepository.deleteById(id);
        System.out.println("Delete material with id: " + id);
    }

    public MaterialResponse updateMaterial(Long id, UpdateMaterialRequest request) {
        Material material = materialRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found with id " + id));
        material.setTitle(request.getTitle());
        material.setDescription(request.getDescription());
        material.setFileUrl(request.getFileUrl());

        if (request.getSubjectId() != null) {
            Subject subject = subjectRepository.findById(request.getSubjectId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Subject not found"));
            material.setSubject(subject);
        }
        if (request.getAuthorId() != null) {
            User user = userRepository.findById(request.getAuthorId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException("User not found"));
            material.setAuthor(user);
        }
        Material saved = materialRepository.save(material);

        return MaterialMapper.toResponse(saved);
    }

    @Cacheable(value = "materialsBySubject", key = "#subjectId")
    public List<MaterialResponse> getMaterialsBySubject(Long subjectId) {
        return materialRepository.findBySubjectId(subjectId)
                .stream()
                .map(MaterialMapper::toResponse)
                .toList();
    }

    public List<MaterialResponse> searchMaterials(String keyword) {
        return materialRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
                        keyword, keyword
                ).stream()
                .map(MaterialMapper::toResponse)
                .toList();
    }
}
