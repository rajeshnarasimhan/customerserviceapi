package com.belong.customerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.belong.customerservice.entity.Customer;

/**
 * Repository interface for Customer Entity
 * @author rajesh
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
