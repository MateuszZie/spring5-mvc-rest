package org.mateuszziebura.spring5mvcrest.controllers.v1;

import org.mateuszziebura.spring5mvcrest.api.v1.model.CustomerDTO;
import org.mateuszziebura.spring5mvcrest.api.v1.model.CustomerListDTO;
import org.mateuszziebura.spring5mvcrest.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/v1/customers/")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<CustomerListDTO> getCustomersList(){
        return new ResponseEntity<>(new CustomerListDTO(customerService.getCustomers()), HttpStatus.OK);
    }
    @GetMapping("{url}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable String url){
        return new ResponseEntity<>(customerService.getCustomerByUrl(url), HttpStatus.OK);
    }
}
