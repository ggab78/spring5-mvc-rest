package guru.springfamework.controllers;


import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.api.v1.model.CategoryListDTO;
import guru.springfamework.services.CategoryService;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

@RequestMapping(CategoryController.BASE_URL)

@AllArgsConstructor
public class CategoryController {

    private CategoryService categoryService;
    public static final String BASE_URL = "/api/v1/categories";

    @GetMapping
    public ResponseEntity<CategoryListDTO> getAllCategories() {

        return new ResponseEntity<CategoryListDTO>(
                new CategoryListDTO(categoryService.findAll()), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<CategoryDTO> getCategoryByName(@PathVariable("name") String name) {

        return new ResponseEntity<CategoryDTO>(
                categoryService.findByName(name), HttpStatus.OK);
    }

}
