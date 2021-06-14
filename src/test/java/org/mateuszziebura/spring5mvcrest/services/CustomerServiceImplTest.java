package org.mateuszziebura.spring5mvcrest.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mateuszziebura.spring5mvcrest.api.v1.mapper.CustomerMapper;
import org.mateuszziebura.spring5mvcrest.api.v1.model.CustomerDTO;
import org.mateuszziebura.spring5mvcrest.domain.Customer;
import org.mateuszziebura.spring5mvcrest.repositories.CustomerRepository;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


class CustomerServiceImplTest {

    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE,customerRepository);
    }

    @Test
    void getCustomers() {
        when(customerRepository.findAll()).thenReturn(Arrays.asList(new Customer(), new Customer()));

        List<CustomerDTO> customerDTOS = customerService.getCustomers();

        assertEquals(2, customerDTOS.size());
    }

    @Test
    void getCustomerByUrl() {
        Customer customer = new Customer();
        customer.setCustomerUrl("url");

        when(customerRepository.findByCustomerUrl(anyString())).thenReturn(customer);

        CustomerDTO customerDTO = customerService.getCustomerByUrl("lol");

        assertEquals("url",customerDTO.getCustomerUrl());
    }
    @Test
    public void createNewCustomer() throws Exception {

        //given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("Jim");

        Customer savedCustomer = new Customer();
        savedCustomer.setFirstName(customerDTO.getFirstName());
        savedCustomer.setLastName(customerDTO.getLastName());
        savedCustomer.setCustomerUrl("someUrl");

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        //when
        CustomerDTO savedDto = customerService.createNewCustomer(customerDTO);

        //then
        assertEquals(customerDTO.getFirstName(), savedDto.getFirstName());
        assertEquals("someUrl", savedDto.getCustomerUrl());
    }
}