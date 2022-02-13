package com.ninjarmm.rmmservicesserverapp.repositories;

import com.ninjarmm.rmmservicesserverapp.model.customers.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CustomerDependentRepositoryITBase {
    public static final String CUSTOMER_1 = "customer1";
    public static final String CUSTOMER_2 = "customer2";
    public static final Customer CUSTOMER_1_ENTITY = Customer.builder()
            .id(CUSTOMER_1)
            .build();
    public static final Customer CUSTOMER_2_ENTITY = Customer.builder()
            .id(CUSTOMER_2)
            .build();

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    public void setUpCustomers(){
        customerRepository.save(CUSTOMER_1_ENTITY);
        customerRepository.save(CUSTOMER_2_ENTITY);
    }

    @AfterEach
    public void cleanUpCustomers(){
        customerRepository.deleteAll();
    }
}
