package mmdev.service;


import mmdev.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageService {

    private final Path fileStorageLocation;

    public FileStorageService(@Value("${file.upload-dir}") String uploadDir){
        this.fileStorageLocation = Paths.get(uploadDir).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        }catch (Exception ex){
            throw new RuntimeException("Cannot create directory for files.",ex);
        }
    }
    public String saveFile(MultipartFile file){
        try {
            String originalFileName = file.getOriginalFilename();
            String uniqueFileName = UUID.randomUUID().toString() + "_" + originalFileName;

            Path targetLocation = this.fileStorageLocation.resolve(uniqueFileName);

            Files.copy(file.getInputStream(),targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return uniqueFileName;
        }catch (IOException ex){
            throw new RuntimeException("Cannot save file " + file.getOriginalFilename(),ex);
        }
    }
    public Resource loadFileAsResource(String fileName){
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() || resource.isReadable()){
                return resource;
            }else {
                throw new ResourceNotFoundException("File not found: " + fileName);
            }
        }catch (MalformedURLException ex){
            throw new ResourceNotFoundException("Error way to file: " + fileName);
        }
    }

}
