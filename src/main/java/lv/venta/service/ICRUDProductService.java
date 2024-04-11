package lv.venta.service;

import java.util.ArrayList;

import lv.venta.model.Product;

public interface ICRUDProductService {
	public Product createProduct(String title, String description, float price, int quantity) throws Exception;
	public ArrayList<Product> retrieveAll() throws Exception;
	public Product retrieveById(int id) throws Exception;
	public void updateById(int id, String title, String description, float price, int quantity) throws Exception;
	public void deleteById(int id) throws Exception;
}
