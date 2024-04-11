package lv.venta.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lv.venta.model.Product;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class FirstController {
	
	/*
	private Product p1 = new Product("Eggs", "Chicken eggs", 3.00f, 16);
	private Product p2 = new Product("Apples", "Sour Green Apples", 1.50f, 8);
	private Product p3 = new Product("Cola", "Coca-cola", 1.30f, 20);
	private ArrayList<Product> productList = new ArrayList<Product>(Arrays.asList(p1, p2, p3));
	*/
	
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
	@GetMapping("/product")
	public String getProduct(Model model) {
		model.addAttribute("product", new Product("Eggs", "Chicken eggs", 3.00f, 16));
		return "product-page";
	}
	
	@GetMapping("/product/getList")
	public String getProductList(Model model) {
		
	
		model.addAttribute("productList", productList);
		return "productList-page";
	}
	
	@GetMapping("/product/one") //localhost:8080/product/one?id=x
	public String getProductOneId(@RequestParam("id")int id, Model model) {
		if(id >= 0) {
			for (Product p : productList) {
				if(p.getId() == id) {
					model.addAttribute("product", p);
					return "product-page";
				}
			}
			model.addAttribute("errormsg", "Product is not found.");
			return "error-page";
		}
		else {
			model.addAttribute("errormsg", "Expected positive ID.");
			return "error-page";
		}
	}
	
	
	
}
