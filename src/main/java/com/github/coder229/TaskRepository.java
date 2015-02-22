package com.github.coder229;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;

@Document(collection="tasks")
public interface TaskRepository extends MongoRepository<Task, String> {

}
