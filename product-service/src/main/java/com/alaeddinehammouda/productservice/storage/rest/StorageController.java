package com.alaeddinehammouda.productservice.storage.rest;


import com.alaeddinehammouda.productservice.repository.ProductRepository;
import com.alaeddinehammouda.productservice.storage.CustomFileRepository;
import com.alaeddinehammouda.productservice.storage.StorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/upload-files")
@RequiredArgsConstructor
public class StorageController {

    private final StorageService storageService;
    private final CustomFileMapper customFileMapper;
    private final ProductsProperties ProductsProperties;
    private final CustomFileRepository customFileRepository;
    private final ProductRepository productRepository;


}