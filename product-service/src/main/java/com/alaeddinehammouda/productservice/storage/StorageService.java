package com.alaeddinehammouda.productservice.storage;

import com.alaeddinehammouda.productservice.storage.exceptions.InvalidFileTypeException;
import io.jsonwebtoken.lang.Strings;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    /**
     * @param file file to upload
     * @param path base upload directory path, typically fetched from properties
     * @return an already saved CustomFile entity
     */
    CustomFile uploadFile(MultipartFile file, String path);

    /**
     * @param file               file to upload
     * @param path               base upload directory path, typically fetched from properties
     * @param enumFileExtensions Enum class with file extensions that should be accepted.
     * @return path to file
     */
    CustomFile uploadFile(MultipartFile file, String path, @NotNull Class<? extends Enum<?>>... enumFileExtensions);


    CustomFile uploadFile(MultipartFile file, String path, @NotNull String... fileExtensions);

    /**
     * Uploads file to default upload directory
     *
     * @param file file to upload
     * @return path to file
     */
    CustomFile uploadFile(MultipartFile file);


    default void checkIfFileCompliantToFileType(String filename, @NotNull String... fileExtensions) throws InvalidFileTypeException {
        for (String extension : fileExtensions) {
            if ("ANY".equalsIgnoreCase(extension)) return;
            if (Strings.endsWithIgnoreCase(filename, "." + extension)) return;
        }
        throw new InvalidFileTypeException();
    }
}
