package com.customer.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

import com.customer.dto.Customer;


public interface AnnotationCustomerMapper {
	@Select("Select * from customer order by Id")
	public List<Customer> findAll();
	@Select("select * from customer where id=#{id}")
	public Customer findById(int pk);
	@Insert("insert into customer(id, name, address, email) values(#{id}, #{name}, #{address}, #{email})")
	@SelectKey(statement="select id1.nextval from dual", keyProperty="id", before=true, resultType=int.class)
	public int save(Customer customer);
	@Update("update customer set address = #{address}, email = #{email} where id = #{id} ")
	public int modify(Customer customer);
	@Delete("delete customer  where id  = #{id}")
	public int remove(int id);
	@Select("select * from customer where name like '%'||#{name}||'%'")
	public List<Customer> selectLike(String name);
	@Select("select count(*) from customer")
	public int pageCount();
	@Select("Select * from customer order by Id")
	public List<Customer> findByPage(RowBounds rowBounds);
}