package org.mateuszziebura.spring5mvcrest.controllers.v1;

import org.mateuszziebura.spring5mvcrest.api.v1.model.CustomerDTO;
import org.mateuszziebura.spring5mvcrest.api.v1.model.CustomerListDTO;
import org.mateuszziebura.spring5mvcrest.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<CustomerListDTO> getCustomersList(){
        return new ResponseEntity<>(new CustomerListDTO(customerService.getCustomers()), HttpStatus.OK);
    }
    @GetMapping("/{url}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable String url){
        return new ResponseEntity<>(customerService.getCustomerByUrl(url), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO){
        return new ResponseEntity<>(customerService.createNewCustomer(customerDTO), HttpStatus.CREATED);
    }
    @PutMapping({"/{url}"})
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable String url,@RequestBody CustomerDTO customerDTO){
        return new ResponseEntity<>(customerService.uploadCustomer(url,customerDTO), HttpStatus.OK);
    }
    @PatchMapping({"/{url}"})
    public ResponseEntity<CustomerDTO> pathCustomer(@PathVariable String url,@RequestBody CustomerDTO customerDTO){
        return new ResponseEntity<>(customerService.patchCustomer(url,customerDTO), HttpStatus.OK);
    }
    @DeleteMapping({"/{url}"})
    public ResponseEntity<Void> deleteCustomer(@PathVariable String url){
        customerService.deleteByUrl(url);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
