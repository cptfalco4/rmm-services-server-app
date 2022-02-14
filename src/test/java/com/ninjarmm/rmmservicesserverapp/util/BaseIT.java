package com.ninjarmm.rmmservicesserverapp.util;

import com.ninjarmm.rmmservicesserverapp.model.customers.Customer;
import com.ninjarmm.rmmservicesserverapp.repositories.CustomerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@EnableAutoConfiguration(exclude = { SecurityAutoConfiguration.class })
public class BaseIT {
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
