package com.belong.customerservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.belong.customerservice.entity.CustomerPhone;


/**
 * Repository interface for CustomerPhone Entity
 * @author rajesh
 */
@Repository
public interface PhoneRepository extends JpaRepository<CustomerPhone, Long> {
	
	/**
	 * interface method to find CustomerPhone records based on customerId
	 * @param customer id
	 * @return CustomerPhone records list
	 */
	List<CustomerPhone> findByCustomerId(Long customerId);
	
}