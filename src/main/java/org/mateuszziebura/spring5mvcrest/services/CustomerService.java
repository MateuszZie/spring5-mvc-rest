package org.mateuszziebura.spring5mvcrest.services;

import org.mateuszziebura.spring5mvcrest.api.v1.model.CustomerDTO;

import java.util.List;

public interface CustomerService {
    List<CustomerDTO> getCustomers();

    CustomerDTO getCustomerByUrl(String Url);

    CustomerDTO createNewCustomer(CustomerDTO customerDTO);
}
