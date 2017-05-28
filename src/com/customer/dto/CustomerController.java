package com.customer.dto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public String inputcustomer(@ModelAttribute Customer customer){
		
		return "input";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insertcustomer(@ModelAttribute Customer customer){
		
		int cnt = customerService.saveCustomer(customer);
		
		return "redirect:list";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String updatecustomer(@RequestParam("id") int pk, Model model){
		Customer customer = customerService.getCustomersPk(pk);
		model.addAttribute("customer", customer);
		return "update";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String modifiycustomer(@ModelAttribute Customer customer){
		
		int cnt = customerService.modifiyCustomer(customer);
		System.out.println(cnt);
		return "redirect:list";
	}
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public List<Customer> List(
			@RequestParam(defaultValue="1") int selectPage, 
			@RequestParam(defaultValue="1") int beginPage,
			@RequestParam(defaultValue="5") int endPage, 
			@RequestParam(defaultValue="1") int nextPage, Model model){
		//@RequestParam(defaultValue="0") int beforePage
		//전체 행의 수
		int cnt = customerService.getpageCount();
		//전체 페이지 수
		int totalPage = (int)Math.ceil((double)cnt/Page.size);
		
		//시작 페이지(한번에 보이는 페이지 중에서)
		beginPage = selectPage + (beginPage+1-selectPage) - 1;//8+(6+1-8)-1= 6
		
		//끝 페이지(한번에 보이는 페이지 중에서)
		if(endPage >= totalPage){
			endPage = totalPage;
		}else{
			endPage = beginPage + Page.size -1 ;//6 + 5 - 1 = 10
		}
		
		//다음 페이지 이동 시 선택된 페이지
		nextPage = beginPage + Page.size;
		
		//다음 페이지의 첫페이지와 끝 페이지
		int nextPageB = beginPage + Page.size;
		int nextPageE = endPage + Page.size;
		
		//이전 페이지 이동 시 선택된 페이지
		
		//beforePage = endPage - Page.size;//13-5 = 8
		
		//이전 페이지의 첫페이지와 끝 페이지
		int beforePageB = beginPage - Page.size;
		int beforePageE = 0;
		
		if(endPage % Page.size == 0){
			beforePageE = endPage - Page.size;
		}else{
			beforePageE = totalPage -  (totalPage - ((totalPage/Page.size) * Page.size));
		}
		
		//첫페이지(맨 처음 페이지 1 ~ 5)
		int holdBiginPageB = Page.size - (Page.size-1);
		int holdBiginPageE = Page.size;
		
		//마지막 페이지(맨 마지막 페이지)
		int holdEndPageB = totalPage - (totalPage/Page.size) +1;
		int holdEndPageE = totalPage;
		
		//바인딩 처리
		
		//시작 페이지(for문의 조건식)
		model.addAttribute("beginPage", beginPage);
		model.addAttribute("endPage", endPage);
		
		//이전 혹은 다음 페이지 이동 시 보여지는 첫페이지와 끝페이지
		model.addAttribute("nextPage", nextPage);
		//model.addAttribute("beforePage", beforePage);
		
		//다음 페이지의 첫페이지와 끝페이지
		model.addAttribute("nextPageB", nextPageB);
		model.addAttribute("nextPageE", nextPageE);
		
		//이전 페이지의 첫페이지와 끝페이지
		model.addAttribute("beforePageB", beforePageB);
		model.addAttribute("beforePageE", beforePageE);
		
		//첫페이지(맨 처음 페이지 1 ~ 5)
		model.addAttribute("holdBiginPageB", holdBiginPageB);
		model.addAttribute("holdBiginPageE", holdBiginPageE);
		
		//마지막 페이지(맨 마지막 페이지)
		model.addAttribute("holdEndPageB", holdEndPageB);
		model.addAttribute("holdEndPageE", holdEndPageE);
		
		
		
		
		List<Customer> lists = customerService.getCustomerByPage(selectPage);
		model.addAttribute("lists", lists);
		
		return lists;
	}
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String delete(@RequestParam("id") int pk){
		
		int cnt = customerService.removeCustomer(pk);
		
		return "redirect:list";
	}
	
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public String likeput(@ModelAttribute Customer customer, Model model){
		
		List<Customer> lists = customerService.getCustomers();
		
		model.addAttribute("lists", lists);
		
		return "search";
	}
	
	@RequestMapping(value="/search", method=RequestMethod.POST)
	public String likeresult(@ModelAttribute Customer customer, Model model){
		
		List<Customer> lists = null;
		
		if(customer.getName()!=null){
			lists = customerService.getlike(customer.getName());
		}else{
			lists = customerService.getCustomers();
		}
		model.addAttribute("lists", lists);
		
		return "search";
	}
	
	@RequestMapping(value="/transaction", method=RequestMethod.GET)
	public String delete(){
		
		customerService.transactionTest();
		
		return "redirect:list";
	}
}
