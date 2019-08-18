package com.belong.customerservice.business.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.belong.customerservice.business.PhoneService;
import com.belong.customerservice.entity.CustomerPhone;
import com.belong.customerservice.repository.PhoneRepository;

/**
 * Service class implementation for CustomerPhone Entity
 * @author rajesh
 */
@Service
@Transactional
public class PhoneServiceImpl implements PhoneService {
	
	private final Logger LOGGER = LoggerFactory.getLogger(PhoneServiceImpl.class);
	
	private PhoneRepository phoneRepository;
	
	@Autowired
	public void setPhoneRepository(PhoneRepository phoneRepository) {
		this.phoneRepository = phoneRepository;
	}

	/**
	 * find all CustomerPhone records page by page
	 * @param pagination info
	 * @return page of CustomerPhone records
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<CustomerPhone> findAll(Pageable pageable) {
		LOGGER.info("Request to find all Customer Phone");
		return phoneRepository.findAll(pageable);
	}

	/**
	 * find all CustomerPhone records for a customerId
	 * @param customerId
	 * @return list of CustomerPhone records
	 */
	@Override
	@Transactional(readOnly = true)
	public List<CustomerPhone> findByCustomerId(Long customerId) {
		LOGGER.info("Request to find all Customer Phone by customerId==[{}]", customerId);
		return phoneRepository.findByCustomerId(customerId);
	}

	/**
	 * activate a CustomerPhone record by Id
	 * @param id
	 * @return Optional containing activated CustomerPhone or Empty option if CustomerPhone not found for the id
	 */
	@Override
	@Transactional
	public Optional<CustomerPhone> activateCustomerPhone(Long id) {
		LOGGER.info("Request to activate Customer Phone by Id==[{}]", id);
		Optional<CustomerPhone> customerPhoneOpt = phoneRepository.findById(id);
		if(customerPhoneOpt.isPresent()) {
			LOGGER.info("Found Customer Phone to activate. Id==[{}]", id);
			CustomerPhone customerPhone = customerPhoneOpt.get();
			customerPhone.setActive(true);
			customerPhone = phoneRepository.saveAndFlush(customerPhone);
			return Optional.of(customerPhone);
		}
		return Optional.empty();
	}
}