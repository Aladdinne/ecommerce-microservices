package com.alaeddinehammouda.productservice.storage;

import com.alaeddinehammouda.productservice.model.BaseEntity;
import com.alaeddinehammouda.productservice.storage.enums.FileUrlType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.io.File;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "custom_files")
@Slf4j
public class CustomFile extends BaseEntity {

    private String fileUrl;

    private String completePath;

    @Field("file_url_type")
    private FileUrlType fileUrlType;
    public void preRemove() {
        if (completePath != null) {
            File file = new File(completePath);
            if (file.exists()) {
                boolean deleted = file.delete();
                if (deleted) {
                    log.info("File deleted successfully: {}", completePath);
                } else {
                    log.warn("Failed to delete file: {}", completePath);
                }
            }
        }
    }
}