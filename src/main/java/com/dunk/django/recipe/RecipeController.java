package com.dunk.django.recipe;

import com.dunk.django.member.MemberAdapter;
import com.dunk.django.recipe.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Controller
public class RecipeController {
    private final RecipeRepository recipeRepository;
    private final RecipeService recipeService;
    private final RecipeQueryRepository recipeQueryRepository;

    @GetMapping("/")
    public String index(@PageableDefault(size = 9, value = 9, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                        String keyword,
                        Model model,
                        @AuthenticationPrincipal MemberAdapter memberAdapter) {
        log.info("keyword : {} ", keyword);

        if (Objects.nonNull(memberAdapter))
            model.addAttribute("member", memberAdapter.getMember());

        model.addAttribute("recipes",
                recipeQueryRepository.findByRecipeTitle(keyword, pageable));
        model.addAttribute("maxPage" , 9);

        return "index";
    }

    @GetMapping("/recipe")
    public String findAll(Long id, Model model) {
        log.info("id : {}", id);
        model.addAttribute("recipe", recipeRepository.findWithAllById(id));

        return "recipe/recipe";
    }

    @GetMapping("/register")
    public String save(@AuthenticationPrincipal MemberAdapter memberAdapter, Model model) {
        model.addAttribute("member", memberAdapter.getMember());

        return "recipe/register";
    }

    @GetMapping("/modify/{id}")
    public String modifiy(@PathVariable Long id, Model model) {
        log.info("id : {}" , id);

        model.addAttribute("recipe", recipeService.getRecipeForm(id));

        return "recipe/modify";
    }
}