package com.alaeddinehammouda.productservice.storage.properties;

import lombok.Data;
import tn.yellowit.jobgate.core.storage.enums.FileType;

@Data
public class FileProperties {
    private String baseDir;
    private String defaultPath;
    private String recruiter;
    private String candidate;
    private String resume;
    private String companyLogo;
    private String companyGallery;
    private String coverLettre;

    public String getPathByFileType(FileType type) {
        return switch (type) {
            case RECRUITER_PHOTO -> recruiter;
            case CANDIDATE_PHOTO -> candidate;
            case RESUME -> resume;
            case COMPANY_LOGO -> companyLogo;
            case COMPANY_GALLERY -> companyGallery;
            case COVER_LETTRE -> coverLettre;
            default -> defaultPath;
        };
    }
}
