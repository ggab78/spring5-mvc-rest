package guru.springfamework.services;

import guru.springfamework.api.v1.model.CategoryDTO;
import guru.springfamework.domain.Category;
import guru.springfamework.repositories.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class CategoryServiceImplTest {

    public static final String NAME = "some name";
    public static final Long ID = 1L;

    CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        categoryService = new CategoryServiceImpl(categoryRepository);
    }

    @Test
    public void findByName() {
        Category category = new Category();
        category.setName(NAME);
        category.setId(ID);

        when(categoryRepository.findByName(anyString())).thenReturn(category);

        CategoryDTO foundCategoryDTO = categoryService.findByName(NAME);

        assertEquals(ID, foundCategoryDTO.getId());
        assertEquals(NAME, foundCategoryDTO.getName());

    }

    @Test
    public void findAll() {

        Category category = new Category();
        category.setName(NAME);
        category.setId(ID);
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(category);

        when(categoryRepository.findAll()).thenReturn(categoryList);

         List<CategoryDTO> foundList= categoryService.findAll();

        assertEquals(1, foundList.size());


    }
}