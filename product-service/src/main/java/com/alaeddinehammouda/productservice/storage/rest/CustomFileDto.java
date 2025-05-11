package com.alaeddinehammouda.productservice.storage.rest;

import com.alaeddinehammouda.productservice.storage.enums.FileUrlType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomFileDto implements Serializable {
    private Long id;
    private String fileUrl;
    private FileUrlType fileUrlType;
}
