package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.controllers.CustomerController;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    @Override
    public CustomerDTO findById(Long id) throws Exception {

        return customerRepository.findById(id)
                .map(CustomerMapper.INSTANCE::customerToCustomerDTO)
                .orElseThrow(()-> new Exception("User Id "+id+" can not be found."));
    }

    @Override
    public List<CustomerDTO> findAll() {
        return customerRepository.findAll()
                .stream()
                .map(customer -> {
                    CustomerDTO customerDTO = CustomerMapper.INSTANCE.customerToCustomerDTO(customer);
                    customerDTO.setCustomerUrl(getCustomerUrl(customer.getId()));
                    return customerDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {

        Customer customer = CustomerMapper.INSTANCE.customerDtoToCustomer(customerDTO);

        Customer savedCustomer = customerRepository.save(customer);

        CustomerDTO returnedCustomerDTO = CustomerMapper.INSTANCE.customerToCustomerDTO(savedCustomer);
        returnedCustomerDTO.setCustomerUrl(getCustomerUrl(returnedCustomerDTO.getId()));

        return returnedCustomerDTO;
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO, Long id) throws Exception {

       return customerRepository.findById(id)
                .map(customer -> {
                    customer.setFirstname(customerDTO.getFirstname());
                    customer.setLastname(customerDTO.getLastname());
                    Customer savedCustomer = customerRepository.save(customer);
                    CustomerDTO returnCustomerDTO = CustomerMapper.INSTANCE.customerToCustomerDTO(savedCustomer);
                    returnCustomerDTO.setCustomerUrl(getCustomerUrl(returnCustomerDTO.getId()));
                    return returnCustomerDTO;
                })
                .orElseThrow(()-> new Exception("Customer with id "+id+" not found."));
    }

    @Override
    public CustomerDTO patchCustomer(CustomerDTO customerDTO, Long id) throws Exception {

        return customerRepository.findById(id)
                .map(customer -> {
                    if(customerDTO.getFirstname()!=null){
                        customer.setFirstname(customerDTO.getFirstname());
                    }
                    if(customerDTO.getLastname()!=null){
                        customer.setLastname(customerDTO.getLastname());
                    }

                    Customer savedCustomer = customerRepository.save(customer);

                    return CustomerMapper.INSTANCE.customerToCustomerDTO(savedCustomer);
                })
                .orElseThrow(()-> new Exception("Customer with id "+id+" not found."));
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }


    private String getCustomerUrl(Long id){
        return CustomerController.BASE_URL+"/"+id;
    }

}
