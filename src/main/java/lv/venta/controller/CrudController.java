package lv.venta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import lv.venta.model.Product;
import lv.venta.service.ICRUDProductService;

@Controller
public class CrudController {
	@Autowired
	private ICRUDProductService crudService;
	
	@GetMapping("/product/getList")
	public String getProductList(Model model) {
		try {
			model.addAttribute("productList", crudService.retrieveAll());
			model.addAttribute("msg", "All products");
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
	
	@GetMapping("/product/update/{id}")
	public String getProductUpdateById(@PathVariable("id") int id, Model model) {
		try {
			Product updatedProduct = crudService.retrieveById(id);
			model.addAttribute("product", updatedProduct);
			return "product-update-page";
		} 
		catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";
		}
	}
	
	@PostMapping("product/update/{id}")
	public String postProductUpdate(@PathVariable("id") int id, @Valid Product product, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "product-update-page";
		}
		else {
			try {
				crudService.updateById(id, product.getTitle(), product.getDescription(), product.getPrice(), product.getQuantity());
				return "redirect:/product/one?id=" + id;
			} 
			catch (Exception e) {
				model.addAttribute("errormsg", e.getMessage());
				return "error-page";
			}
		}
	}
	
	@GetMapping("/product/delete/{id}")
	public String getProductDeleteById(@PathVariable("id") int id, Model model) {
		try {
			crudService.deleteById(id);
			model.addAttribute("productList", crudService.retrieveAll());
			//Refrain from using redirect: on get.
			return "productList-page";
		} 
		catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";
		}
	}
}
