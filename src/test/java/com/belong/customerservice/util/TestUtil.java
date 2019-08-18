package com.belong.customerservice.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * Utility class for testing.
 * @author rajesh
 */
public class TestUtil {
	
	/**
     * create a Pageable object to use in test class
     * @return Pageable object
     */
    public static Pageable createPageable() {
    	return PageRequest.of(0, 1, new Sort(Sort.Direction.DESC, "id"));
    }
}