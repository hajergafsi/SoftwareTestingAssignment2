/**
*
* @author Hajer Gafsi hajer.gafsi@ogr.sakarya.edu.tr
* @since 16/05/2021
* <p>
* 	This interface defines the structure of a product or cart item
* </p>
*/

package webTester;

public interface IProduct {
	public String getName();
	public double getPrice();
	public int getQty();
	public void setQty(int qty);
	public void setName(String name);
	public void setPrice(double price);
}
