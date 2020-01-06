package guru.springfamework.services;

import guru.springfamework.api.v1.model.CustomerDTO;

import java.util.List;

public interface CustomerService {

    CustomerDTO findById(Long id) throws Exception;

    List<CustomerDTO> findAll();
}
