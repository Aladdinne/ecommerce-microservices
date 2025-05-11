package com.alaeddinehammouda.productservice.storage.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Slf4j
@Getter
@Setter
@ConfigurationProperties(prefix = "products", ignoreUnknownFields = false)
public class ProductsProperties {
    private final FileProperties file = new FileProperties();
}
