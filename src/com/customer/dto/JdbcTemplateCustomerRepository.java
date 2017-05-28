package com.customer.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("JdbcTemplateCustomerRepository")
public class JdbcTemplateCustomerRepository implements CustomerRepository{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	

	public int insert(Customer customer){
		
	
		return jdbcTemplate.update(CustomerSql.CUSTOMER_INSERT, customer.getName(), customer.getAddress(), customer.getEmail());
		
	}
	
	@Override
	public List<Customer> select() {
		
		
		
		return jdbcTemplate.query(CustomerSql.CUSTOMER_SELECT_ALL, new RowMapper<Customer>(){
			@Override
			public Customer mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				Customer customer = new Customer();
				
					customer.setId(rs.getInt("id"));
					customer.setName(rs.getString("name"));
					customer.setAddress(rs.getString("address"));
					customer.setEmail(rs.getString("email"));
					
				return customer;
			}
		});
	}

	@Override
	public int update(Customer customer) {
		
		return jdbcTemplate.update(CustomerSql.CUSTOMER_UPDATE, customer.getAddress(), customer.getEmail(), customer.getId());
	}

	@Override
	public int delete(int pk) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update(CustomerSql.CUSTOMER_DELETE, pk);
	}
	
	@Override
	public Customer selectById(int pk) {
		Object[] params = new Object[]{pk};
		
		
		
		return jdbcTemplate.queryForObject(CustomerSql.CUSTOMER_SELECT_BY_PK, params, new RowMapper<Customer>(){
			@Override
			public Customer mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				Customer customer = new Customer();
				customer.setId(rs.getInt("id"));
				customer.setName(rs.getString("name"));
				customer.setAddress(rs.getString("address"));
				customer.setEmail(rs.getString("email"));
				return customer;
			}
		});
	}

	@Override
	public List<Customer> Like(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int pageCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Customer> selectAll(RowBounds rowBounds) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
