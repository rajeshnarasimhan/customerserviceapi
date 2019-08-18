package com.belong.customerservice.rest;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.belong.customerservice.business.PhoneService;
import com.belong.customerservice.entity.CustomerPhone;
import com.belong.customerservice.rest.util.PaginationUtil;
import com.belong.customerservice.rest.util.ReponseUtil;

/**
 * REST controller class for CustomerPhone Management
 * @author rajesh
 */
@RestController
@RequestMapping("/api/v1")
public class CustomerPhoneWS {
	
	private final Logger LOGGER = LoggerFactory.getLogger(CustomerPhoneWS.class);
	
	private PhoneService phoneService;

	@Autowired
	public void setPhoneService(PhoneService phoneService) {
		this.phoneService = phoneService;
	}
	
	/**
	* GET  /customerphones : get all the CustomerPhone page by page.
	* @param pageable the pagination information
	* @return the ResponseEntity with status 200 (OK) and the list of CustomerPhone in body
	*/
	@GetMapping("/customerphones")
	public ResponseEntity<List<CustomerPhone>> getAll(Pageable pageable, HttpServletRequest request) {
		LOGGER.info("REST request to get a page of Customer Phones");
		Page<CustomerPhone> page = phoneService.findAll(pageable);
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(request.getRequestURI());
		LOGGER.info("REST request to get a page of Customer Phones.");
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, uriBuilder.toUriString());
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}
	
	/**
	* GET  /customerphones/customer/{customerId} : get all the CustomerPhone by customerId.
	* @param customerId
	* @return the ResponseEntity with status 200 (OK) and the list of CustomerPhone in body
	*/
	@GetMapping("/customerphones/customer/{customerId}")
	public ResponseEntity<List<CustomerPhone>> getByCustomerId(@PathVariable Long customerId) {
		LOGGER.info("REST request to get a Customer Phones by CustomerId=[{}]", customerId);
		List<CustomerPhone> records = phoneService.findByCustomerId(customerId);
		return new ResponseEntity<>(records, HttpStatus.OK);
	}
	
	/**
	* GET  /customerphone/activate/{id} : activate CustomerPhone by id.
	* @param CustomerPhone id
	* @return the ResponseEntity with status 200 (OK) and activated CustomerPhone in body.
	* if CustomerPhone not available for the id, then ResponseEntity with status 404 (NOT_FOUND)
	*/
	@GetMapping("/customerphone/activate/{id}")
	public ResponseEntity<CustomerPhone> activateCustomerPhone(@PathVariable Long id) {
		LOGGER.info("REST request to activate a Customer Phone by Id=[{}]", id);
		Optional<CustomerPhone> customerPhone = phoneService.activateCustomerPhone(id);
		return ReponseUtil.wrapOrNotFound(customerPhone);
	}
}
