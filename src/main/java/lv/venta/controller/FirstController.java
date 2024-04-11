package lv.venta.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import lv.venta.model.Product;
import lv.venta.service.ICRUDProductService;
import lv.venta.service.IFilterProductService;

import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class FirstController {
	
	@Autowired
	private ICRUDProductService crudService;
	
	@Autowired
	private IFilterProductService filterService;
	
	@GetMapping("/hello") //localhost:8080/hello
	public String getHello() {
		System.out.println("Hello World");
		return "hello-page"; //show hello-page.html page
	}
	
	@GetMapping("/hello/msg")
	public String getHelloMsg(Model model) {
		System.out.println("Msg controller here");
		model.addAttribute("mydata", "This is a test message.");
		return "hello-msg-page"; 
	}
	@GetMapping("/product/test")
	public String getProduct(Model model) {
		try {
			model.addAttribute("product", crudService.retrieveById(1));
			return "product-page";
		} 
		catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";
		}
	}
	
	@GetMapping("/product/getList")
	public String getProductList(Model model) {
		try {
			model.addAttribute("productList", crudService.retrieveAll());
			return "productList-page";
		} 
		catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";
		}
	}
	
	@GetMapping("/product/one") //localhost:8080/product/one?id=x
	public String getProductOneId(@RequestParam("id")int id, Model model) {
		try {
			model.addAttribute("product", crudService.retrieveById(id));
			return "product-page";
		}
		catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";
		}
	}	
	
	@GetMapping("product/insert")
	public String getProductInsert(Model model) {
		model.addAttribute("product", new Product());
		return "product-insert-page";
	}
	
	@PostMapping("product/insert")
	public String postProductInsert(@Valid Product product, BindingResult result) {
		
		if(result.hasErrors()) {
			return "product-insert-page";
		}
		else {
			try {
				crudService.createProduct(product.getTitle(), product.getDescription(), product.getPrice(), product.getQuantity());
				return "redirect:/product/getList";
			} 
			catch (Exception e) {
				return "redirect:/error";
			}
		}
	}
	
	@GetMapping("/error")
	public String getError() {
		return "error-page";
	}
}
