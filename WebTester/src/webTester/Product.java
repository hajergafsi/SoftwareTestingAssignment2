/**
*
* @author Hajer Gafsi hajer.gafsi@ogr.sakarya.edu.tr
* @since 16/05/2021
* <p>
* 	This class is the class responsible for simulating cart-item operations
* </p>
*/

package webTester;

public class Product implements IProduct {

	private String name;
	private double price;
	private int quantity;
	
	public Product() {
		
	}
	
	public Product(String name,double price,int quantity ) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public double getPrice() {
		return this.price;
	}
	
	@Override
	public int getQty() {
		return this.quantity;
	}	
	
	@Override
	public void setQty(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public void setName(String name) {	
		this.name = name;
	}

	@Override
	public void setPrice(double price) {
		this.price = price;
	}

}
