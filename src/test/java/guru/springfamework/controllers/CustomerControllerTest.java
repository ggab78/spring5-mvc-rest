package guru.springfamework.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.services.CustomerService;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest {

    public static final long ID = 1L;
    public static final String FIRSTNAME = "gab";
    public static final String LASTNAME = "xxx";
    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void getAllCustomers() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(ID);
        customerDTO.setFirstname(FIRSTNAME);

        List<CustomerDTO> customerDTOList = Arrays.asList(customerDTO, new CustomerDTO());
        when(customerService.findAll()).thenReturn(customerDTOList);

        mockMvc.perform(get(CustomerController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));

    }

    @Test
    public void getCategoryByName() throws Exception {

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(ID);
        customerDTO.setFirstname(FIRSTNAME);

        when(customerService.findById(anyLong())).thenReturn(customerDTO);

        mockMvc.perform(get(CustomerController.BASE_URL+"/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo(FIRSTNAME)));
    }

    @Test
    public void createNewCustomer() throws Exception {

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setLastname(LASTNAME);
        customerDTO.setFirstname(FIRSTNAME);

//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("firstname",customerDTO.getFirstname());
//        jsonObject.put("lastname",customerDTO.getLastname());
//        String jsonString = jsonObject.toString();

        String jsonString = new ObjectMapper().writeValueAsString(customerDTO);


        when(customerService.createNewCustomer(any())).thenReturn(customerDTO);

        mockMvc.perform(post(CustomerController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstname", equalTo(FIRSTNAME)));
    }




    @Test
    public void updateCustomer() throws Exception {

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setLastname(LASTNAME);
        customerDTO.setFirstname(FIRSTNAME);

        String jsonString = new ObjectMapper().writeValueAsString(customerDTO);


        when(customerService.updateCustomer(any(), anyLong())).thenReturn(customerDTO);

        mockMvc.perform(put(CustomerController.BASE_URL+"/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString)
        )
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.firstname", equalTo(FIRSTNAME)));

    }

    @Test
    public void deleteCustomer() throws Exception {

        mockMvc.perform(delete(CustomerController.BASE_URL+"/1")
                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());

        verify(customerService, times(1)).deleteCustomerById(anyLong());

    }
}