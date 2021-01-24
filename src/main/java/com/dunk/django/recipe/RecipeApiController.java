package com.dunk.django.recipe;

import com.dunk.django.member.MemberAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
public class RecipeApiController {
    private final RecipeService recipeService;

    @PostMapping("/recipe")
    public ResponseEntity<Long> save(@RequestBody RecipeSaveForm recipeSaveForm,
                                     @AuthenticationPrincipal MemberAdapter memberAdapter) {
        log.info("recipeSaveForm : {}", recipeSaveForm);
        log.info("member : {}", memberAdapter);

        Long id = recipeService.save(recipeSaveForm, memberAdapter.getMember());

        return ResponseEntity.ok(id);
    }

    @PutMapping("/recipe/{id}")
    public ResponseEntity<Long> update(@RequestBody RecipeSaveForm recipeSaveForm, @PathVariable Long id) {
        log.info("recipeSaveForm : {}", recipeSaveForm);
        log.info(recipeSaveForm.getIngredients());

        recipeService.update(recipeSaveForm, id);

        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/recipe/{id}")
    public ResponseEntity<String> remove(@PathVariable Long id) {
        recipeService.remove(id);
        return ResponseEntity.ok().build();
    }
}
