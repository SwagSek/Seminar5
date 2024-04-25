package lv.venta.service.impl;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.venta.model.Product;
import lv.venta.repo.IProductRepo;
import lv.venta.service.ICRUDProductService;
import lv.venta.service.IFilterProductService;

@Service
public class ProductServiceImpl implements ICRUDProductService, IFilterProductService{

	@Autowired
	private IProductRepo productRepo;
	
	@Override
	public Product createProduct(String title, String description, float price, int quantity) throws Exception {
		if(title == null || description == null || price < 0 || quantity < 0) throw new Exception("Bad input values.");
		
		Product foundProduct = productRepo.findByTitleAndDescriptionAndPrice(title, description, price);
		if(foundProduct != null) {
			foundProduct.setQuantity(foundProduct.getQuantity() + quantity);
			return productRepo.save(foundProduct);
		}
		
		Product newProduct = new Product(title, description, price, quantity);
		productRepo.save(newProduct);
		return newProduct;
	}

	@Override
	public ArrayList<Product> retrieveAll() throws Exception {
		if (productRepo.count() == 0) throw new Exception("Product list is empty.");
		return (ArrayList<Product>) productRepo.findAll();
	}

	@Override
	public Product retrieveById(int id) throws Exception {
		if (productRepo.count() == 0) throw new Exception("Product list is empty.");
		if (id <= 0) throw new Exception("ID must be positive.");
		
		if(productRepo.existsById(id)) {
			return productRepo.findById(id).get();
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
		productRepo.save(currentProduct);
	}

	@Override
	public void deleteById(int id) throws Exception {
		productRepo.deleteById(id);
	}
	
	@Override
	public ArrayList<Product> filterByPriceLessThanThreshold(float threshold) throws Exception {
		if (threshold < 0 || threshold > 10000) throw new Exception("Bad price threshold.");
		return productRepo.findByPriceLessThan(threshold);
	}

	@Override
	public ArrayList<Product> filterByQuantityLessThanThreshold(int threshold) throws Exception {
		if (threshold < 0 || threshold >= 100) throw new Exception("Bad quantity threshold.");
		return productRepo.findByQuantityLessThan(threshold);
	}

	@Override
	public ArrayList<Product> filterByTitleOrDescription(String text) throws Exception {
		if (text == null) throw new Exception("Bad input value.");
		return productRepo.findByTitleContainingOrDescriptionContaining(text);
	}

	@Override
	public float calculateProductsTotalValue() throws Exception {
		if (productRepo.count() == 0) throw new Exception("Product list is empty.");
		return productRepo.calculateTotalValueFromDB();
	}
	
}
