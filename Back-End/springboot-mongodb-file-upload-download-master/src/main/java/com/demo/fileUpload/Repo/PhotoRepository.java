package com.demo.fileUpload.Repo;

import com.demo.fileUpload.model.Photo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PhotoRepository extends MongoRepository<Photo, String> { }
