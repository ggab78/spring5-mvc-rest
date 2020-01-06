package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
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
                .map(customer -> CustomerMapper.INSTANCE.customerToCustomerDTO(customer))
                .orElseThrow(()-> new Exception("User Id"+id+"can not be found"));
    }

    @Override
    public List<CustomerDTO> findAll() {
        return customerRepository.findAll()
                .stream()
                .map(CustomerMapper.INSTANCE::customerToCustomerDTO)
                .collect(Collectors.toList());
    }
}
