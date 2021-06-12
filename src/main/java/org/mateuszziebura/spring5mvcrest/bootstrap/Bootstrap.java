package org.mateuszziebura.spring5mvcrest.bootstrap;

import org.mateuszziebura.spring5mvcrest.domain.Category;
import org.mateuszziebura.spring5mvcrest.domain.Customer;
import org.mateuszziebura.spring5mvcrest.repositories.CategoryRepository;
import org.mateuszziebura.spring5mvcrest.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        Customer adam = new Customer();
        adam.setFirstName("Adam");
        adam.setLastName("Ryt");
        adam.setCustomerUrl("adamryt");

        Customer mateusz = new Customer();
        mateusz.setFirstName("Mateusz");
        mateusz.setLastName("Ziebura");
        mateusz.setCustomerUrl("mateuszziebura");

        Customer krystian = new Customer();
        krystian.setFirstName("Krystian");
        krystian.setLastName("Lis");
        krystian.setCustomerUrl("krystianlis");

        customerRepository.save(adam);
        customerRepository.save(mateusz);
        customerRepository.save(krystian);
        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        System.out.println("Data Loaded = " + customerRepository.count() );
        System.out.println("Data Loaded = " + categoryRepository.count() );
    }
}
