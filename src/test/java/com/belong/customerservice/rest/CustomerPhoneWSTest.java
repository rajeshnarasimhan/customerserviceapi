package com.belong.customerservice.rest;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.belong.customerservice.CustomerserviceApplication;
import com.belong.customerservice.business.PhoneService;
import com.belong.customerservice.entity.Customer;
import com.belong.customerservice.entity.CustomerPhone;
import com.belong.customerservice.repository.CustomerRepository;
import com.belong.customerservice.repository.PhoneRepository;

/**
 * Test class for CustomerServiceWSTest
 * @author rajesh
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CustomerserviceApplication.class)
public class CustomerPhoneWSTest {
	
	private MockMvc restCustomerPhoneMockMvc;
	
    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;
	
	@Autowired
	private PhoneService phoneService;
	
	@Autowired
	private PhoneRepository phoneRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	private CustomerPhone customerPhone;
	
	private Customer customer;
	
	private static final Long ID = 1L;
	private static final String PHONE = "100-911";
	private static final boolean IS_ACTIVE = false;
	private static final String NAME = "TESTER";
	
	@Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CustomerPhoneWS customerPhoneWS = new CustomerPhoneWS();
        customerPhoneWS.setPhoneService(phoneService);
        this.restCustomerPhoneMockMvc = MockMvcBuilders.standaloneSetup(customerPhoneWS)
        								.setCustomArgumentResolvers(pageableArgumentResolver).build();
    }
	
	@Before
    public void initTest() {
    	customerPhone = createCustomerPhone();
    }

	private CustomerPhone createCustomerPhone() {
		customer = new Customer()
					.setId(ID)
					.setName(NAME);
		customerPhone = new CustomerPhone()
					.setId(ID)
					.setPhone(PHONE)
					.setActive(IS_ACTIVE)
					.setCustomer(customer);
		return customerPhone;
	}
	
	@Test
    @Transactional
    @WithMockUser(username = "tester", password = "testpassword")
    public void getAll() throws Exception {
		// Initialize the data
		customer = customerRepository.saveAndFlush(customer);
		customerPhone = phoneRepository.saveAndFlush(customerPhone);
		
        // Get all customerphone
        restCustomerPhoneMockMvc.perform(get("/api/v1/customerphones"))
	        .andExpect(status().isOk())
	        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
	        .andExpect(jsonPath("$.[0].id").value(customerPhone.getId().intValue()))
	        .andExpect(jsonPath("$.[0].phone").value(customerPhone.getPhone()))
	        .andExpect(jsonPath("$.[0].active").value(customerPhone.isActive()))
	        .andExpect(jsonPath("$.[0].customer.id").value(customerPhone.getCustomer().getId().intValue()))
	        .andExpect(jsonPath("$.[0].customer.name").value(customerPhone.getCustomer().getName()));
    }
    
    @Test
    @Transactional
    @WithMockUser(username = "tester", password = "testpassword")
    public void getByCustomerId() throws Exception {
		// Initialize the data
		customer = customerRepository.saveAndFlush(customer);
		customerPhone = phoneRepository.saveAndFlush(customerPhone);
		
        // Get all customerphone by customer id
        restCustomerPhoneMockMvc.perform(get("/api/v1/customerphones/customer/{customerId}", customer.getId()))
	        .andExpect(status().isOk())
	        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
	        .andExpect(jsonPath("$.[0].id").value(customerPhone.getId().intValue()))
	        .andExpect(jsonPath("$.[0].phone").value(customerPhone.getPhone()))
	        .andExpect(jsonPath("$.[0].active").value(customerPhone.isActive()))
	        .andExpect(jsonPath("$.[0].customer.id").value(customerPhone.getCustomer().getId().intValue()))
	        .andExpect(jsonPath("$.[0].customer.name").value(customerPhone.getCustomer().getName()));
    }
    
    @Test
    @Transactional
    @WithMockUser(username = "tester", password = "testpassword")
    public void activateCustomerPhoneSuccess() throws Exception {
		// Initialize the data
		customer = customerRepository.saveAndFlush(customer);
		customerPhone = phoneRepository.saveAndFlush(customerPhone);
		
        // activate customer phone by id
        restCustomerPhoneMockMvc.perform(get("/api/v1/customerphone/activate/{id}", customerPhone.getId()))
	        .andExpect(status().isOk())
	        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
	        .andExpect(jsonPath("$.id").value(customerPhone.getId().intValue()))
	        .andExpect(jsonPath("$.phone").value(customerPhone.getPhone()))
	        .andExpect(jsonPath("$.active").value(true))
	        .andExpect(jsonPath("$.customer.id").value(customerPhone.getCustomer().getId().intValue()))
	        .andExpect(jsonPath("$.customer.name").value(customerPhone.getCustomer().getName()));
    }
    
    @Test
    @Transactional
    @WithMockUser(username = "tester", password = "testpassword")
    public void activateCustomerPhoneFail() throws Exception {
		// Initialize the data
		customer = customerRepository.saveAndFlush(customer);
		customerPhone = phoneRepository.saveAndFlush(customerPhone);
		
        // activate customer phone by id
        restCustomerPhoneMockMvc.perform(get("/api/v1/customerphone/activate/{id}", 1000L))
	        .andExpect(status().isNotFound());
    }
}