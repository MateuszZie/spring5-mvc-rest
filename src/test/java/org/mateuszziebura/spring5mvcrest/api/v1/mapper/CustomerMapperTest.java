package org.mateuszziebura.spring5mvcrest.api.v1.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mateuszziebura.spring5mvcrest.api.v1.model.CustomerDTO;
import org.mateuszziebura.spring5mvcrest.domain.Customer;

import static org.junit.jupiter.api.Assertions.*;

class CustomerMapperTest {

    private final String NAME = "Mateusz";
    private final String LASTNAME = "Ziebura";
    private final String URL = "mateuszziebura";

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    void customerToCustomerDto() {

        Customer customer = new Customer();
        customer.setCustomerUrl(URL);
        customer.setFirstName(NAME);
        customer.setLastName(LASTNAME);

        CustomerDTO customerDTO = customerMapper.customerToCustomerDto(customer);

        assertEquals(NAME, customerDTO.getFirstName());
        assertEquals(LASTNAME, customerDTO.getLastName());
        assertEquals(URL,customerDTO.getCustomerUrl());
    }
}