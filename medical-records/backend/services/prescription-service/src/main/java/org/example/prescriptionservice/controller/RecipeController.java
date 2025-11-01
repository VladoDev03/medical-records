package org.example.prescriptionservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.prescriptionservice.dto.recipe.CreateRecipeDto;
import org.example.prescriptionservice.dto.recipe.RecipeDto;
import org.example.prescriptionservice.service.contracts.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/recipes")
@RequiredArgsConstructor
public class RecipeController {
    private final RecipeService recipeService;

    @GetMapping
    public Flux<RecipeDto> getRecipes() {
        return recipeService.getRecipes();
    }

    @GetMapping("/{id}")
    public Mono<RecipeDto> getRecipeById(@PathVariable String id) {
        return recipeService.getRecipeById(id);
    }

    @PostMapping
    public Mono<ResponseEntity<RecipeDto>> createRecipe(@RequestBody @Valid CreateRecipeDto recipeDto) {
        return recipeService.createRecipe(recipeDto)
                .map(createdRecipe -> new ResponseEntity<>(createdRecipe, HttpStatus.CREATED));
    }
}
