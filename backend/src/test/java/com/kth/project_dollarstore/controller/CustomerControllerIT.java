package com.kth.project_dollarstore.controller;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kth.project_dollarstore.model.Customer;
import com.kth.project_dollarstore.service.CustomerService;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CustomerService customerService;

    @Test
    public void http_Post_RegisterCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setName("Joar Gerlov");
        customer.setEmail("gerlov@kth.se");
        customer.setPassword("password");

        mockMvc.perform(post("/api/v1/customers/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isCreated())
                .andExpect(content().string("Customer registered successfully"));
    }

    @Test
    public void http_Get_GetCustomerById() throws Exception {
        Customer customer = new Customer();
        customer.setName("Joar Gerlov");
        customer.setEmail("gerlov@kth.se");
        customer.setPassword("password");

        String result = customerService.addCustomer(customer);
        assert result.equals("Customer registered successfully");

        Optional<Customer> savedCustomer = customerService.getCustomerByEmail("gerlov@kth.se");

        mockMvc.perform(get("/api/v1/customers/{id}", savedCustomer.get().getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Joar Gerlov"))
                .andExpect(jsonPath("$.email").value("gerlov@kth.se"));
    }

    @Test
    public void http_Delete_DeleteCustomerById() throws Exception {
        Customer customer = new Customer();
        customer.setName("Joar Gerlov");
        customer.setEmail("gerlov@kth.se");
        customer.setPassword("password");

        String result = customerService.addCustomer(customer);
        assert result.equals("Customer registered successfully");

        Optional<Customer> savedCustomer = customerService.getCustomerByEmail("gerlov@kth.se");

        mockMvc.perform(delete("/api/v1/customers/{id}", savedCustomer.get().getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void http_Put_UpdateCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setName("Joar Gerlov");
        customer.setEmail("gerlov@kth.se");
        customer.setPassword("password");

        String result = customerService.addCustomer(customer);
        assert result.equals("Customer registered successfully");

        Optional<Customer> savedCustomer = customerService.getCustomerByEmail("gerlov@kth.se");

        Customer updatedCustomer = new Customer();
        updatedCustomer.setName("Julia Zubko");

        mockMvc.perform(put("/api/v1/customers/{id}", savedCustomer.get().getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedCustomer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Julia Zubko"));
    }

    @Test
    public void shouldLoginCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setName("Joar Gerlov");
        customer.setEmail("gerlov@kth.se");
        customer.setPassword("password");

        String result = customerService.addCustomer(customer);
        assert result.equals("Customer registered successfully");

        Customer loginDetails = new Customer();
        loginDetails.setEmail("gerlov@kth.se");
        loginDetails.setPassword("password");

        Optional<Customer> savedCustomer = customerService.getCustomerByEmail("gerlov@kth.se");

        mockMvc.perform(post("/api/v1/customers/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginDetails)))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(savedCustomer.get().getId())));
    }

}
