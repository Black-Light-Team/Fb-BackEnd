package com.irisi.facebook.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Profile extends MongoRepository<Profile, String> {
}
