package neu.edu.mainapp.dto;

public class PurchaseProductDTO {
	private String purchaseid;
	private String purchasedate;
	private String username;
	private String productid;
	private String productname;
	private String category;
	
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPurchaseid() {
		return purchaseid;
	}
	public void setPurchaseid(String purchaseid) {
		this.purchaseid = purchaseid;
	}
	public String getPurchasedate() {
		return purchasedate;
	}
	public void setPurchasedate(String purchasedate) {
		this.purchasedate = purchasedate;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	
}
