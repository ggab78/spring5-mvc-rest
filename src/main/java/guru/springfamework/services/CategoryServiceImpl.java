package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CategoryMapper;
import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.repositories.CategoryRepository;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Override
    public CategoryDTO findByName(String name) {
        return CategoryMapper.INSTANCE.categoryToCategoryDTO(categoryRepository.findByName(name));
    }

    @Override
    public List<CategoryDTO> findAll() {
        return categoryRepository.findAll()
                .stream()
//                .map(category -> categoryMapper.categoryToCategoryDTO(category))
//              same as below
                .map(CategoryMapper.INSTANCE::categoryToCategoryDTO)
                .collect(Collectors.toList());
    }

}
