package com.alaeddinehammouda.productservice.storage;

import com.alaeddinehammouda.productservice.storage.enums.FileUrlType;
import com.alaeddinehammouda.productservice.storage.exceptions.FileUploadException;
import com.alaeddinehammouda.productservice.storage.properties.ProductsProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import jakarta.validation.constraints.NotNull;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@EnableConfigurationProperties(ProductsProperties.class)
public class LocalStorageServiceImpl implements StorageService {

    private final ProductsProperties productsProperties;
    private final CustomFileRepository customFileRepository;

    @Override
    public CustomFile uploadFile(MultipartFile file, String path  ) {
        String relativeFilePath = path + UUID.randomUUID() + file.getOriginalFilename();
        String completeFilePath = this.productsProperties.getFile().getBaseDir() + relativeFilePath;
        File myFile = new File(completeFilePath);
        saveToFileSystem(file, myFile);
        CustomFile customFile = new CustomFile( relativeFilePath, completeFilePath, FileUrlType.RELATIVE);
        return this.customFileRepository.save(customFile);
    }


    @Override
    @SafeVarargs
    public final CustomFile uploadFile(MultipartFile file, String path, @NotNull Class<? extends Enum<?>>... enumFileExtensions) {
        checkIfFileCompliantToFileType(file.getOriginalFilename(), this.enumExtensionToString(enumFileExtensions));
        return this.uploadFile(file, path);
    }

    @Override
    public CustomFile uploadFile(MultipartFile file, String path, @NotNull String... fileExtensions) {
        checkIfFileCompliantToFileType(file.getOriginalFilename(), fileExtensions);
        return this.uploadFile(file, path);
    }

    @Override
    public CustomFile uploadFile(MultipartFile file) {
        return this.uploadFile(file, this.productsProperties.getFile().getDefaultPath());
    }

    private void saveToFileSystem(MultipartFile file, File myFile) throws FileUploadException {
        try {
            //noinspection ResultOfMethodCallIgnored
            myFile.createNewFile(); //NOSONAR
            FileOutputStream fos = new FileOutputStream(myFile);
            fos.write(file.getBytes());
            fos.close();
        } catch (IOException exception) {
            log.warn(exception.getMessage());
            throw new FileUploadException();
        }
    }

    @SafeVarargs
    private String[] enumExtensionToString(@NotNull Class<? extends Enum<?>>... enumFileExtensions) {
        List<String> extensions = new ArrayList<>();
        Arrays.stream(enumFileExtensions).forEach(enumFileExtensionClass -> Arrays.stream(enumFileExtensionClass.getEnumConstants()).forEach(enumFileExtensionAttribute -> extensions.add(enumFileExtensionAttribute.name())));
        return extensions.toArray(String[]::new);
    }
}
