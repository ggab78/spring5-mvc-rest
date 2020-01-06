package guru.springfamework.bootstrap;

import guru.springfamework.domain.Category;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import guru.springfamework.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 9/24/17.
 */
@Component
@AllArgsConstructor
public class Bootstrap implements CommandLineRunner{

    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;


    @Override
    public void run(String... args) throws Exception {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        Customer gab = new Customer();
        gab.setFirstname("gab");
        gab.setLastname("gam");

        Customer mat = new Customer();
        mat.setFirstname("mat");
        mat.setLastname("pat");

        customerRepository.save(gab);
        customerRepository.save(mat);

    }
}
