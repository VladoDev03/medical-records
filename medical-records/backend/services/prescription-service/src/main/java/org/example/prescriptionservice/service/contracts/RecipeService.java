package org.example.prescriptionservice.service.contracts;

import org.example.prescriptionservice.dto.recipe.CreateRecipeDto;
import org.example.prescriptionservice.dto.recipe.RecipeDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RecipeService {
    Flux<RecipeDto> getRecipes();
    Mono<RecipeDto> getRecipeById(String id);
    Mono<RecipeDto> createRecipe(CreateRecipeDto recipe);
    Mono<Void> deleteRecipe(String id);
}
