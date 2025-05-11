package com.alaeddinehammouda.productservice.storage.rest;


import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import tn.yellowit.jobgate.core.storage.enums.FileType;


@Component
public class FileTypeEnumConverter implements Converter<String, FileType> {

    @Override
    public FileType convert(@NonNull String value) {
        try {
            return FileType.valueOf(value.toUpperCase());
        } catch (Exception e) {
            return FileType.ANY;
        }
    }
}
