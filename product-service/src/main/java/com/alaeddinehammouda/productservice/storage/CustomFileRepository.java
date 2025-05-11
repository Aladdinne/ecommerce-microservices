package com.alaeddinehammouda.productservice.storage;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomFileRepository extends MongoRepository<CustomFile, String> {
}
