package AbstractComponents;

public class CartPageData {

	private String cartTableProdName ;
	private int singlePrice ;
	private int quantity ;
	private int totalPrice ;
	
	public CartPageData(String cartTableProdName , int singlePrice , int quantity , int totalPrice)
	{
		this.cartTableProdName = cartTableProdName ;
		this.singlePrice = singlePrice ;
		this.quantity = quantity ;
		this.totalPrice = totalPrice ;
		
	}
	
	public String getCartTableProdName()
	{
		return cartTableProdName ;
	}
	
	public int getsinglePrice()
	{
		return singlePrice ;
	}
	
	public int getquantity()
	{
		return quantity ;
	}
	
	public int gettotalPrice()
	{
		return totalPrice ;
	}
	
}
