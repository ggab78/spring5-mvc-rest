package guru.springfamework.services;

import guru.springfamework.api.v1.model.CategoryDTO;

import java.util.List;

public interface CategoryService{

    CategoryDTO findByName(String name);

    List<CategoryDTO> findAll();

}
