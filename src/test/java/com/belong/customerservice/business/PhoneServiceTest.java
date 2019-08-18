package com.belong.customerservice.business;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.belong.customerservice.CustomerserviceApplication;
import com.belong.customerservice.business.impl.PhoneServiceImpl;
import com.belong.customerservice.entity.Customer;
import com.belong.customerservice.entity.CustomerPhone;
import com.belong.customerservice.repository.PhoneRepository;
import com.belong.customerservice.util.TestUtil;

/**
 * Test class for PhoneService class 
 * @author rajesh
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CustomerserviceApplication.class)
public class PhoneServiceTest {
	
	@Mock
	private PhoneRepository phoneRepository;
	
	@InjectMocks
	private PhoneServiceImpl phoneService;
	
	private CustomerPhone customerPhone;
	
	private Customer customer;
	
	private Pageable pageable;
	
	private static final Long ID = 1L;
	private static final String PHONE = "100-911";
	private static final boolean IS_ACTIVE = false;
	private static final String NAME = "TESTER";
	
	@Before 
	public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Before
    public void initTest() {
    	customerPhone = createCustomerPhone();
    	pageable = TestUtil.createPageable();
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
	
	private List<CustomerPhone> getCustomerPhoneList() {
		List<CustomerPhone> phones = new ArrayList<>();
		phones.add(customerPhone);
		return phones;
	}
	
	private void assertRecord(CustomerPhone customerPhone) {
		assertThat(customerPhone.getId()).isEqualTo(ID);
		assertThat(customerPhone.getPhone()).isEqualTo(PHONE);
		assertThat(customerPhone.isActive()).isEqualTo(IS_ACTIVE);
		assertThat(customerPhone.getCustomer().getId()).isEqualTo(ID);
		assertThat(customerPhone.getCustomer().getName()).isEqualTo(NAME);	
	}
	
	@Test
	public void findAll() {
		Page<CustomerPhone> page = new PageImpl<>(getCustomerPhoneList());
		doReturn(page).when(phoneRepository).findAll(pageable);
		Page<CustomerPhone> phones = phoneService.findAll(pageable);
		assertTrue(phones.hasContent());
		assertRecord(phones.getContent().get(0));
	}
	
	@Test
	public void findByCustomerId() {
		doReturn(getCustomerPhoneList()).when(phoneRepository).findByCustomerId(ID);
		List<CustomerPhone> phones = phoneService.findByCustomerId(ID);
		assertTrue((phones != null) && (phones.size() > 0));
		assertRecord(phones.get(0));
	}
	
	@Test
	public void activateCustomerPhoneSuccess() {
		doReturn(Optional.of(customerPhone)).when(phoneRepository).findById(ID);
		doReturn(customerPhone).when(phoneRepository).saveAndFlush(customerPhone);
		Optional<CustomerPhone> optCustomerPhone = phoneService.activateCustomerPhone(ID);
		assertTrue(optCustomerPhone.isPresent());
		assertThat(optCustomerPhone.get().isActive()).isEqualTo(true);	
	}
	
	@Test
	public void activateCustomerPhoneFail() {
		doReturn(Optional.empty()).when(phoneRepository).findById(1000L);
		Optional<CustomerPhone> optCustomerPhone = phoneService.activateCustomerPhone(1000L);
		assertTrue(!optCustomerPhone.isPresent());
	}
}