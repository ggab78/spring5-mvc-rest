package guru.springfamework.controllers;


import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.api.v1.model.CategoryListDTO;
import guru.springfamework.services.CategoryService;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/categories/")
@AllArgsConstructor
public class CategoryController {

private CategoryService categoryService;


    @GetMapping
    public ResponseEntity<CategoryListDTO> getAllCategories(){

        return new ResponseEntity<CategoryListDTO>(
          new CategoryListDTO(categoryService.findAll()), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<CategoryDTO> getCategoryByName(@PathVariable("name") String name){

        return new ResponseEntity<CategoryDTO>(
                categoryService.findByName(name), HttpStatus.OK);
    }

}
