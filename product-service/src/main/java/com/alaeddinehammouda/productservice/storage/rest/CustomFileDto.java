package com.alaeddinehammouda.productservice.storage.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.yellowit.jobgate.core.storage.enums.FileUrlType;


import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomFileDto implements Serializable {
    private Long id;
    private String fileUrl;
    private FileUrlType fileUrlType;
}
