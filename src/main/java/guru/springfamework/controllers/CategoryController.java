package guru.springfamework.controllers;


import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.api.v1.model.CategoryListDTO;
import guru.springfamework.services.CategoryService;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CategoryController.BASE_URL)

@AllArgsConstructor
public class CategoryController {

    private CategoryService categoryService;
    public static final String BASE_URL = "/api/v1/categories";

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CategoryListDTO getAllCategories() {
        return new CategoryListDTO(categoryService.findAll());
    }

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO getCategoryByName(@PathVariable("name") String name) {
        return categoryService.findByName(name);
    }

}
