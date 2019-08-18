package com.belong.customerservice.business;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.belong.customerservice.entity.CustomerPhone;

/**
 * Service interface for CustomerPhone Entity
 * @author rajesh
 */
public interface PhoneService {
	
	/**
	 * find all CustomerPhone records page by page
	 * @param pagination info
	 * @return page of CustomerPhone records
	 */
	Page<CustomerPhone> findAll(Pageable pageable);
	
	/**
	 * find all CustomerPhone records for a customerId
	 * @param customerId
	 * @return list of CustomerPhone records
	 */
	List<CustomerPhone> findByCustomerId(Long customerId);
	
	/**
	 * activate a CustomerPhone record by Id
	 * @param id
	 * @return Optional containing activated CustomerPhone or Empty option if CustomerPhone not found for the id
	 */
	Optional<CustomerPhone> activateCustomerPhone(Long id);
}
