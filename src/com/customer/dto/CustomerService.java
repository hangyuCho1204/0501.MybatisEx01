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
		//                                    1 - 1 * 5 =  0, 5(������ �ε����� 0���� ����) 0���� 5���� ����϶� 
		//                                    1 - 1 * 5 =  5, 5
		//                                    1 - 1 * 5 = 10, 5
		//�� ��ü�� ������ select�ؼ� ������ �߶�� �ʿ䰡 ����.
		List<Customer> customers = CustomerRepository.selectAll(rowBounds);
		
		return customers;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	public void transactionTest(){
		Customer customer = new Customer();
		customer.setName("���ѱ�6");
		customer.setAddress("����6");
		customer.setEmail("aa@a.a6");
		
		CustomerRepository.insert(customer);
		
		if(true){
			throw new RuntimeException();
		}
		//��Ÿ�� �ͼ��� ����
		
		CustomerRepository.delete(300);
		
		
	}
	
	
}
