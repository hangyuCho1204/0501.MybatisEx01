package com.customer.dto;

public class CustomerSql {
	//jdbc ���ø� �� ���� SQL��
	/*public static final String CUSTOMER_INSERT= " insert into customer( id, name, address, email ) values( 'abc'||id1.nextval, ?, ?, ? ) ";
	public static final String CUSTOMER_DELETE = " delete customer  where id  = ? ";
	public static final String CUSTOMER_UPDATE = " update customer set address = ?, email = ? where id = ? ";
	public static final String CUSTOMER_SELECT_ALL= " select  * from customer ";
	public static final String CUSTOMER_SELECT_BY_PK = " select * from customer where id = ? ";*/
	
	//jdbc DAO support SQL��
	public static final String CUSTOMER_INSERT= " insert into customer( id, name, address, email ) values( id1.nextval, :name, :address, :email ) ";
	public static final String CUSTOMER_DELETE = " delete customer  where id  = :id ";
	public static final String CUSTOMER_UPDATE = " mail where id =update customer set address = :address, email = :email where id = :id ";
	public static final String CUSTOMER_SELECT_ALL= " select  * from customer ";
	public static final String CUSTOMER_SELECT_BY_PK = " select * from customer where id = :id ";
	public static final String CUSTOMER_LIKE_BY_NAME = " select * from customer where name like :name ";
}
