package guru.springfamework.services;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.bootstrap.Bootstrap;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerServiceImplIT {

    public static final String LASTNAME = "some new lastname";
    public static final long ID = 1L;
    public static final String FIRSTNAME = "new firstname";
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CategoryRepository categoryRepository;

    CustomerService customerService;

    @Before
    public void setUp() throws Exception {
        Bootstrap bootstrap = new Bootstrap(categoryRepository, customerRepository);
        bootstrap.run();
        customerService = new CustomerServiceImpl(customerRepository);
    }

    @Test
    public void patchCustomerUpdateFirstName() throws Exception {

        CustomerDTO customerDTO=new CustomerDTO();
        customerDTO.setLastname(LASTNAME);

        Long id = customerService.findAll().get(0).getId();

        CustomerDTO persistedCustomer =  customerService.patchCustomer(customerDTO, id);

        assertEquals(LASTNAME, persistedCustomer.getLastname());
        assertEquals(customerService.findById(id).getFirstname(), persistedCustomer.getFirstname());
        assertThat(LASTNAME, equalTo(persistedCustomer.getLastname()));
        assertThat(LASTNAME,not(equalTo(" ")));
    }

    @Test
    public void patchCustomerUpdateLastName() throws Exception {
        CustomerDTO customerDTO=new CustomerDTO();
        customerDTO.setFirstname(FIRSTNAME);

        Long id = customerService.findAll().get(0).getId();

        CustomerDTO persistedCustomer =  customerService.patchCustomer(customerDTO, id);

        assertEquals(FIRSTNAME, persistedCustomer.getFirstname());
        assertEquals(customerService.findById(id).getLastname(), persistedCustomer.getLastname());
    }

}
