package com.alaeddinehammouda.productservice.storage.enums;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
public enum FileType {
    ANY(AnyExtension.class), RECRUITER_PHOTO(ImageExtension.class), CANDIDATE_PHOTO(ImageExtension.class), RESUME(PdfExtension.class), COVER_LETTRE(PdfExtension.class), COMPANY_LOGO(ImageExtension.class), COMPANY_GALLERY(ImageExtension.class);

    public static final String DEFAULT_VALUE = "ANY";
    private final Set<String> extensionSet;
    private final Class<? extends Enum<?>> extensionClass;

    FileType(Class<? extends Enum<?>> extensionClass) {
        this.extensionClass = extensionClass;
        this.extensionSet = new HashSet<>();
        Enum<?>[] enumExtensions = extensionClass.getEnumConstants();
        for (Enum<?> ext : enumExtensions) {
            extensionSet.add(ext.name().toLowerCase());
        }
    }
}
