package com.customer.mapper;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.customer.dto.Customer;

public interface CustomerMapper {
	public List<Customer> findAll();
	
	public Customer findById(int pk);
	public int save(Customer customer);
	public int modify(Customer customer);
	public int remove(int id);
	public List<Customer> selectLike(String name);
	
	public int pageCount();
	public List<Customer> findByPage(RowBounds rowBounds);
}