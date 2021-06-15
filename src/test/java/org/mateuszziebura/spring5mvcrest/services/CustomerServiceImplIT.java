package org.mateuszziebura.spring5mvcrest.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mateuszziebura.spring5mvcrest.api.v1.mapper.CustomerMapper;
import org.mateuszziebura.spring5mvcrest.api.v1.model.CustomerDTO;
import org.mateuszziebura.spring5mvcrest.bootstrap.Bootstrap;
import org.mateuszziebura.spring5mvcrest.domain.Customer;
import org.mateuszziebura.spring5mvcrest.repositories.CategoryRepository;
import org.mateuszziebura.spring5mvcrest.repositories.CustomerRepository;
import org.mateuszziebura.spring5mvcrest.repositories.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;


@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CustomerServiceImplIT {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    VendorRepository vendorRepository;

    CustomerService customerService;

    @BeforeEach
    public void setUp() throws Exception {
        System.out.println("Loading Customer Data");
        System.out.println(customerRepository.findAll().size());

        //setup data for testing
        Bootstrap bootstrap = new Bootstrap(categoryRepository, customerRepository, vendorRepository);
        bootstrap.run(); //load data

        customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepository);
    }

    @Test
    public void patchCustomerUpdateFirstName() throws Exception {
        String updatedName = "UpdatedName";
        String url = getCustomerUrlValue();

        Customer originalCustomer = customerRepository.getById(url);
        assertNotNull(originalCustomer);
        //save original first name
        String originalFirstName = originalCustomer.getFirstName();
        String originalLastName = originalCustomer.getLastName();

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName(updatedName);

        customerService.patchCustomer(url, customerDTO);

        Customer updatedCustomer = customerRepository.findByCustomerUrl(url);

        assertNotNull(updatedCustomer);
        assertEquals(updatedName, updatedCustomer.getFirstName());
        assertThat(originalFirstName, not(equalTo(updatedCustomer.getFirstName())));
        assertThat(originalLastName, equalTo(updatedCustomer.getLastName()));
    }

    @Test
    public void patchCustomerUpdateLastName() throws Exception {
        String updatedName = "UpdatedName";
        String url = getCustomerUrlValue();

        Customer originalCustomer = customerRepository.getById(url);
        assertNotNull(originalCustomer);

        //save original first/last name
        String originalFirstName = originalCustomer.getFirstName();
        String originalLastName = originalCustomer.getLastName();

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setLastName(updatedName);

        customerService.patchCustomer(url, customerDTO);

        Customer updatedCustomer = customerRepository.findByCustomerUrl(url);

        assertNotNull(updatedCustomer);
        assertEquals(updatedName, updatedCustomer.getLastName());
        assertThat(originalFirstName, equalTo(updatedCustomer.getFirstName()));
        assertThat(originalLastName, not(equalTo(updatedCustomer.getLastName())));
    }

    private String getCustomerUrlValue(){
        List<Customer> customers = customerRepository.findAll();

        System.out.println("Customers Found: " + customers.size());

        //return first id
        return customers.get(0).getCustomerUrl();
    }
}