package com.customer.dto;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly=true, propagation=Propagation.SUPPORTS)
public class CustomerService {
	
	@Autowired
	@Qualifier("MybatisCustomerRepository")
	private CustomerRepository CustomerRepository;
	
	@Transactional(propagation=Propagation.REQUIRED)
	public int saveCustomer(Customer customer) {
		
		
		return CustomerRepository.insert(customer);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public int modifiyCustomer(Customer customer) {
		
		
		return CustomerRepository.update(customer);
	}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public int removeCustomer(int pk) {
		
		
		return CustomerRepository.delete(pk);
	}

	public List<Customer> getCustomers() {
		
		List<Customer> customers =CustomerRepository.select();
		
		return customers;
	}
	
	public Customer getCustomersPk(int pk) {
		
		Customer customer = CustomerRepository.selectById(pk);
		
		return customer;
	}
	
	
	public List<Customer> getlike(String name) {
		
		List<Customer> customer = CustomerRepository.Like(name);
		
		return customer;
	}
	
	public int getpageCount() {
		
		int cnt = CustomerRepository.pageCount();
		
		return cnt;
	}

	public List<Customer> getCustomerByPage(int selectPage) {
		RowBounds rowBounds = new RowBounds((selectPage-1)*Page.size, Page.size);
		//                                    1 - 1 * 5 =  0, 5(페이지 인덱스는 0부터 시작) 0부터 5까지 출력하라 
		//                                    1 - 1 * 5 =  5, 5
		//                                    1 - 1 * 5 = 10, 5
		//위 객체만 있으면 select해서 영역을 잘라올 필요가 없음.
		List<Customer> customers = CustomerRepository.selectAll(rowBounds);
		
		return customers;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	public void transactionTest(){
		Customer customer = new Customer();
		customer.setName("조한규6");
		customer.setAddress("강남6");
		customer.setEmail("aa@a.a6");
		
		CustomerRepository.insert(customer);
		
		if(true){
			throw new RuntimeException();
		}
		//런타임 익셉션 실행
		
		CustomerRepository.delete(300);
		
		
	}
	
	
}
