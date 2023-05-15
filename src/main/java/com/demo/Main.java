package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication // This annotation is used to mark a class as a Spring Boot application
@RestController // This annotation is used to create RESTful web services using Spring MVC
@RequestMapping("api/v1/customers") // This annotation is used to map web requests onto specific handler classes and/or handler methods
public class Main {

    private final CustomerRepository customerRepository;

    public Main(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @GetMapping
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }


    record NewCustomerRequest(String name, Integer age, String email){
    }
    @PostMapping
    public void addCustomer(@RequestBody NewCustomerRequest request){
        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setAge(request.age());
        customer.setEmail(request.email());
        customerRepository.save(customer);
    }

    @DeleteMapping(path = "{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer customerId){
        customerRepository.deleteById(customerId);
    }

}