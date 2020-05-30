package guru.springfamework.controllers;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.api.v1.model.CustomerListDTO;
import guru.springfamework.services.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(description = "This is Customer Controller")
@Controller
@AllArgsConstructor
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {


    public static final String BASE_URL="/api/v1/customers";
    private CustomerService customerService;

    @ApiOperation(value = "this will get a list of Customers", notes = "Some notes come here")
    @GetMapping
    public ResponseEntity<CustomerListDTO> getAllCustomers(){
        return new ResponseEntity<CustomerListDTO>(
                new CustomerListDTO(customerService.findAll()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable("id") String id) throws Exception{
        return new ResponseEntity<CustomerDTO>(
                customerService.findById(Long.parseLong(id)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createNewCustomer(@RequestBody CustomerDTO customerDTO) throws Exception{
        return new ResponseEntity<CustomerDTO>(
                customerService.createNewCustomer(customerDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@RequestBody CustomerDTO customerDTO, @PathVariable("id") String id) throws Exception{
        return new ResponseEntity<CustomerDTO>(
                customerService.updateCustomer(customerDTO, Long.parseLong(id)), HttpStatus.ACCEPTED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomerDTO> patchCustomer(@RequestBody CustomerDTO customerDTO, @PathVariable("id") String id) throws Exception{
        return new ResponseEntity<CustomerDTO>(
                customerService.patchCustomer(customerDTO, Long.parseLong(id)), HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteCustomer(@PathVariable("id") String id) throws Exception{
        customerService.deleteCustomerById(Long.parseLong(id));
        return new ResponseEntity(HttpStatus.OK);
    }

}
