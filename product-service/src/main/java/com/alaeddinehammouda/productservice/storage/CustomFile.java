package com.alaeddinehammouda.productservice.storage;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.PreRemove;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import tn.yellowit.jobgate.core.storage.enums.FileUrlType;
import tn.yellowit.jobgate.domain.entities.BaseEntity;

import java.io.File;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Slf4j
public class CustomFile extends BaseEntity {

    private String fileUrl;
    private String completePath;
    @Enumerated(EnumType.STRING)
    private FileUrlType fileUrlType;


    @PreRemove
    private void preRemove() {
        // Delete the associated file
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
