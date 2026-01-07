package org.example.prescriptionservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.prescriptionservice.dto.recipe.CreateRecipeDto;
import org.example.prescriptionservice.dto.recipe.RecipeDto;
import org.example.prescriptionservice.service.contracts.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@PreAuthorize("hasAuthority('admin')")
@RestController
@RequestMapping("/api/recipes")
@RequiredArgsConstructor
public class RecipeController {
    private final RecipeService recipeService;

    @GetMapping
    public Flux<RecipeDto> getRecipes() {
        return recipeService.getRecipes();
    }

    @PreAuthorize("hasAuthority('doctor')")
    @GetMapping("/{id}")
    public Mono<RecipeDto> getRecipeById(@PathVariable String id) {
        return recipeService.getRecipeById(id);
    }

    @PreAuthorize("hasAuthority('doctor')")
    @PostMapping
    public Mono<ResponseEntity<RecipeDto>> createRecipe(@RequestBody @Valid CreateRecipeDto recipeDto) {
        return recipeService.createRecipe(recipeDto)
                .map(createdRecipe -> new ResponseEntity<>(createdRecipe, HttpStatus.CREATED));
    }

    @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<ResponseEntity<Void>> deleteRecipe(@PathVariable String id) {
        return recipeService.deleteRecipe(id)
                .then(Mono.just(ResponseEntity.noContent().<Void>build()))
                .onErrorResume(Exception.class, e ->
                        Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()));
    }
}
