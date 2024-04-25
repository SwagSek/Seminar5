package lv.venta.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lv.venta.model.Product;
import lv.venta.service.IFilterProductService;

@Controller
public class FilterController {
	@Autowired
	private IFilterProductService filterService;
	
	@GetMapping("product/filter/price/{threshold}")
	public String getProductFilterByPrice(@PathVariable("threshold") float threshold, Model model) {
		try {
			ArrayList<Product> filteredProducts = filterService.filterByPriceLessThanThreshold(threshold);
			model.addAttribute("productList", filteredProducts);
			model.addAttribute("msg", "Products filtered by price: " + threshold + " eur");
			return "productList-page";
		} 
		catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";
		}
	}
	
	@GetMapping("product/filter/quantity/{threshold}")
	public String getProductFilterByQuantity(@PathVariable("threshold") int threshold, Model model) {
		try {
			ArrayList<Product> filteredProducts = filterService.filterByQuantityLessThanThreshold(threshold);
			model.addAttribute("productList", filteredProducts);
			model.addAttribute("msg", "Products filtered by quantity: " + threshold + " items");
			return "productList-page";
		} 
		catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";
		}
	}
	
	@GetMapping("product/filter/titleOrDesc/{text}")
	public String getProductFilterByTitleOrDescription(@PathVariable("text") String text, Model model) {
		try {
			ArrayList<Product> filteredProducts = filterService.filterByTitleOrDescription(text);
			model.addAttribute("productList", filteredProducts);
			model.addAttribute("msg", "Products filtered by title or description by string: " + text);
			return "productList-page";
		} 
		catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";
		}
	}
	
	@GetMapping("product/filter/totalValue")
	public String getProductTotalValue(Model model) {
		try {
			model.addAttribute("mydata", "Total sum of all products: " + filterService.calculateProductsTotalValue() + " eur");
			return "hello-msg-page";
		} 
		catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";
		}
	}
}
