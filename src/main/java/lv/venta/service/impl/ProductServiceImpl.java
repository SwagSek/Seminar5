package lv.venta.service.impl;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.stereotype.Service;

import lv.venta.model.Product;
import lv.venta.service.ICRUDProductService;
import lv.venta.service.IFilterProductService;

@Service
public class ProductServiceImpl implements ICRUDProductService, IFilterProductService{

	private Product p1 = new Product("Eggs", "Chicken eggs", 3.00f, 16);
	private Product p2 = new Product("Apples", "Sour Green Apples", 1.50f, 8);
	private Product p3 = new Product("Cola", "Coca-cola", 1.30f, 20);
	private ArrayList<Product> productList = new ArrayList<Product>(Arrays.asList(p1, p2, p3));
	
	@Override
	public Product createProduct(String title, String description, float price, int quantity) throws Exception {
		if(title == null || description == null || price < 0 || quantity < 0)
			throw new Exception("Bad input values.");
		
		for (Product p : productList) {
			if (p.getTitle().equals(title) && p.getDescription().equals(description) && p.getPrice() == price) {
				p.setQuantity(p.getQuantity() + quantity);
				return p;
			}
		}
		Product newProduct = new Product(title, description, price, quantity);
		productList.add(newProduct);
		return newProduct;
	}

	@Override
	public ArrayList<Product> retrieveAll() throws Exception {
		if (productList.isEmpty()) throw new Exception("Product list is empty.");
		return productList;
	}

	@Override
	public Product retrieveById(int id) throws Exception {
		if (productList.isEmpty()) throw new Exception("Product list is empty.");
		if (id <= 0) throw new Exception("ID must be positive.");
		
		for (Product p : productList) {
			if(p.getId() == id) {
				return p;
			}
		}
		throw new Exception("Product with given ID not found");
	}

	@Override
	public void updateById(int id, String title, String description, float price, int quantity) throws Exception {
		Product currentProduct = retrieveById(id);
		if (title != null) currentProduct.setTitle(title);
		if (description != null) currentProduct.setDescription(description);
		if (price >= 0 && price <= 10000) currentProduct.setPrice(price);
		if (quantity >= 0 && quantity <= 100) currentProduct.setQuantity(quantity);
	}

	@Override
	public void deleteById(int id) throws Exception {
		productList.remove(retrieveById(id));
	}
	
	@Override
	public ArrayList<Product> filterByPriceLessThanThreshold(float threshold) throws Exception {
		if (threshold < 0 || threshold > 10000) throw new Exception("Bad price threshold.");
		
		ArrayList<Product> products = new ArrayList<Product>();
		for (Product p : productList) {
			if(p.getPrice() < threshold) {
				products.add(p);
			}
		}
		return products;
	}

	@Override
	public ArrayList<Product> filterByQuantityLessThanThreshold(int threshold) throws Exception {
		if (threshold < 0 || threshold >= 100) throw new Exception("Bad quantity threshold.");
		
		ArrayList<Product> products = new ArrayList<Product>();
		for (Product p : productList) {
			if(p.getQuantity() < threshold) {
				products.add(p);
			}
		}
		return products;
	}

	@Override
	public ArrayList<Product> filterByTitleOrDescription(String text) throws Exception {
		if (text == null) throw new Exception("Bad input value.");
		
		ArrayList<Product> products = new ArrayList<Product>();
		for (Product p : productList) {
			if(p.getTitle().toLowerCase().contains(text.toLowerCase()) || p.getDescription().toLowerCase().contains(text.toLowerCase())) {
				products.add(p);
			}
		}
		return products;
	}

	@Override
	public float calculateProductsTotalValue() throws Exception {
		if (productList.isEmpty()) throw new Exception("Product list is empty.");
		
		float sum = 0;
		for (Product p : productList) {
			sum += p.getPrice()*p.getQuantity();
		}
		return sum;
	}
	
}
