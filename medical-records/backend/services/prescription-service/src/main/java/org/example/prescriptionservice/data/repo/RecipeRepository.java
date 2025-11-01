package org.example.prescriptionservice.data.repo;

import org.example.prescriptionservice.data.document.Recipe;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface RecipeRepository extends ReactiveMongoRepository<Recipe, String> { }
