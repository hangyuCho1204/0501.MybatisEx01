
package com.customer.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository("JdbcDaoSupportCustomerRepository")
public class JdbcDaoSupportCustomerRepository extends NamedParameterJdbcDaoSupport implements CustomerRepository{
	
	@Autowired
	public JdbcDaoSupportCustomerRepository(DataSource datasource){
		super.setDataSource(datasource);
	}
	
	@Override
	public int delete(int pk) {
	
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("id", pk);
		
		return getNamedParameterJdbcTemplate().update(CustomerSql.CUSTOMER_DELETE, params);
		
	}@Override
	public int insert(Customer customer) {
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("name", customer.getName());
		params.put("address", customer.getAddress());
		params.put("email", customer.getEmail());// TODO Auto-generated method stub
			return getNamedParameterJdbcTemplate().update(CustomerSql.CUSTOMER_INSERT, params);
	}@Override
	public List<Customer> select() {
		// TODO Auto-generated method stub
		return getNamedParameterJdbcTemplate().query(CustomerSql.CUSTOMER_SELECT_ALL, new RowMapper<Customer>() {
			@Override
			public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
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
	public Customer selectById(int pk) {
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("id", pk);
		
		return getNamedParameterJdbcTemplate().queryForObject(CustomerSql.CUSTOMER_SELECT_BY_PK, params, new RowMapper<Customer>(){
			@Override
			public Customer mapRow(ResultSet rs, int rowNum)
					throws SQLException {

				Customer customer = new Customer();
				
				customer.setName(rs.getString("name"));
				customer.setAddress(rs.getString("address"));
				customer.setEmail(rs.getString("email"));
				
				return customer;
			}
		});
	}
	
	@Override
	public int update(Customer customer) {
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("address", customer.getAddress());
		params.put("email", customer.getEmail());
		params.put("id", customer.getId());
		
		return getNamedParameterJdbcTemplate().update(CustomerSql.CUSTOMER_UPDATE, params);
	}public JdbcDaoSupportCustomerRepository() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Customer> Like(String name) {
		Map<String, Object> param = new HashMap<String, Object>();
		
		param.put("name", "%"+name+"%");		
		
		return getNamedParameterJdbcTemplate().query(CustomerSql.CUSTOMER_LIKE_BY_NAME, param, new RowMapper<Customer>() {
			@Override
			public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
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
