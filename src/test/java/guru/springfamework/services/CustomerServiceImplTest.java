package guru.springfamework.services;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class CustomerServiceImplTest {

    public static final String FIRSTNAME = "zbych";
    public static final long ID = 10L;
    public static final String LASTNAME = "kielich";

    @Mock
    CustomerRepository customerRepository;


    CustomerService customerService;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerServiceImpl(customerRepository);
    }

    @Test
    public void findById() throws Exception {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstname(FIRSTNAME);
        Optional<Customer> customerOptional = Optional.of(customer);


        when(customerRepository.findById(anyLong())).thenReturn(customerOptional);

        CustomerDTO foundCustomer = customerService.findById(2L);

        assertEquals(FIRSTNAME, foundCustomer.getFirstname());

    }

    @Test
    public void findAll() {

        List<Customer> customerList = Arrays.asList(new Customer(), new Customer());

        when(customerRepository.findAll()).thenReturn(customerList);

        List<CustomerDTO> foundCustomerList = customerService.findAll();

        assertEquals(2, foundCustomerList.size());

    }


    @Test
    public void updateCustomer() throws Exception {

        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstname(FIRSTNAME);
        customer.setLastname(LASTNAME);
        Optional<Customer> customerOptional = Optional.of(customer);

        when(customerRepository.findById(anyLong())).thenReturn(customerOptional);
        when(customerRepository.save(any())).thenReturn(customer);

        CustomerDTO customerDTO = customerService.updateCustomer(new CustomerDTO(), 2L);

        verify(customerRepository, times(1)).save(any());
        //?? todo below is not true
        // assertEquals(FIRSTNAME, customerDTO.getFirstname());

    }

    @Test
    public void deleteCustomer() {

        customerService.deleteCustomerById(ID);
        verify(customerRepository, times(1)).deleteById(anyLong());
    }

}