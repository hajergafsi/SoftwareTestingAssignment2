/**
*
* @author Hajer Gafsi hajer.gafsi@ogr.sakarya.edu.tr
* @since 16/05/2021
* <p>
* 	This class is the class responsible for simulating cart operations upon excecution in the website it holds
*   a cart with products and costs like it's supposed to be in the website's backend, calculates totals and executes cart operations.
* </p>
*/

package webTester;

import java.util.ArrayList;

public class Cart {
	private ArrayList<Product> products;
	private double total;
	
	public Cart() {
		this.products = new ArrayList<Product>();
		this.total = 0;
	}
	
	public void addProduct(Product product) {
		products.forEach(item -> {
			if(item.getName() == product.getName()) {
				item.setQty(item.getQty() + product.getQty());
				this.total += product.getPrice()*product.getQty();
				return;
			}
		});
		this.products.add(product);
		this.total += product.getPrice()*product.getQty();
	}
	
	
	public void removeProduct(Product product) {
		products.forEach(item -> {
			if(item.getName() == product.getName()) {
				if(item.getQty() == product.getQty())products.remove(product);
				else item.setQty(item.getQty() - product.getQty());
			}
		});
		
	}
	
	public double calculateTotal() {
		products.forEach(element -> {
			this.total += element.getQty()*element.getPrice();
		});
		return total;
	}
	
	public double getTotal() {
		return this.total;
	}
	
	public ArrayList<Product> getProducts() {
		return this.products;
	}
	
	

}
