package com.customer.dto;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.customer.dto.Customer;
import com.customer.dto.CustomerRepository;
import com.customer.mapper.AnnotationCustomerMapper;
import com.customer.mapper.CustomerMapper;

@Repository("MybatisCustomerRepository")
public class MybatisCustomerRepository implements CustomerRepository{
	
	
	
	@Autowired
	private AnnotationCustomerMapper mapper;
	//private CustomerMapper mapper;
	
	@Override
	public int insert(Customer customer) {
		// TODO Auto-generated method stub
		return mapper.save(customer);
	}

	@Override
	public List<Customer> select() {
		// TODO Auto-generated method stub
		return mapper.findAll();
	}

	@Override
	public int update(Customer customer) {
		// TODO Auto-generated method stub
		return mapper.modify(customer);
	}

	@Override
	public int delete(int pk) {
		// TODO Auto-generated method stub
		return mapper.remove(pk);
	}

	@Override
	public Customer selectById(int pk) {
		// TODO Auto-generated method stub
		return mapper.findById(pk);
	}

	@Override
	public List<Customer> Like(String name) {
		// TODO Auto-generated method stub
		System.out.println(name);
		return mapper.selectLike(name);
	}

	@Override
	public int pageCount() {
		// TODO Auto-generated method stub
		return mapper.pageCount();
	}

	@Override
	public List<Customer> selectAll(RowBounds rowBounds) {
		
		List<Customer> lists = mapper.findByPage(rowBounds);
		return mapper.findByPage(rowBounds);
	}

}
