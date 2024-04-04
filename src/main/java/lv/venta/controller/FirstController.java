package lv.venta.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lv.venta.model.Product;

@Controller
public class FirstController {
	
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
		
		ArrayList<Product> productList = new ArrayList<Product>();
		productList.add(new Product("Eggs", "Chicken eggs", 3.00f, 16));
		productList.add(new Product("Apples", "Sour Green Apples", 1.50f, 8));
		productList.add(new Product("Cola", "Coca-cola", 1.30f, 20));
		model.addAttribute("productList", productList);
		return "productList-page";
	}
	
	
}
