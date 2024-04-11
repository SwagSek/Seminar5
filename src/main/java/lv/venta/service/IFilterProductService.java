package lv.venta.service;

import java.util.ArrayList;

import lv.venta.model.Product;

public interface IFilterProductService {
	public ArrayList<Product> filterByPriceLessThanThreshold(float threshold) throws Exception;
	public ArrayList<Product> filterByQuantityLessThanThreshold(int threshold) throws Exception;
	public ArrayList<Product> filterByTitleOrDescription(String text) throws Exception;
	public float calculateProductsTotalValue() throws Exception; 
}
