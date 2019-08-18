package com.belong.customerservice.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Entity class for CustomerPhone data
 * @author rajesh
 */
@Entity
@Table(name = "customer_phone")
public class CustomerPhone implements Serializable {

	/**
	 * default value for serial version uid
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	
	@NotBlank
	private String phone;
	
	@Column(name = "is_active")
	private boolean isActive;
	
	@ManyToOne(optional = false)
    @JoinColumn(name = "customer_id")
    private Customer customer;

	public Long getId() {
		return id;
	}
	public CustomerPhone setId(Long id) {
		this.id = id;
		return this;
	}

	public String getPhone() {
		return phone;
	}
	public CustomerPhone setPhone(String phone) {
		this.phone = phone;
		return this;
	}

	public boolean isActive() {
		return isActive;
	}
	public CustomerPhone setActive(boolean isActive) {
		this.isActive = isActive;
		return this;
	}

	public Customer getCustomer() {
		return customer;
	}
	public CustomerPhone setCustomer(Customer customer) {
		this.customer = customer;
		return this;
	}
	
	@Override
	public int hashCode() {
	    return Objects.hashCode(getId());
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Customer entity = (Customer) o;
        if (entity.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), entity.getId());
    }
	
	@Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}