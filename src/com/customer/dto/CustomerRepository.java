package com.customer.dto;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

public interface CustomerRepository {
	public int insert(Customer customer);
	public List<Customer> select();
	public int update(Customer customer);
	public int delete(int pk);
	public Customer selectById(int pk);
	public List<Customer> Like(String name);
	public int pageCount();
	public List<Customer> selectAll(RowBounds rowBounds);
}
