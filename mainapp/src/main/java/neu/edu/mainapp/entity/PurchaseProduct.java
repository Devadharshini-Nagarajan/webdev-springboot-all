package neu.edu.mainapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="purchaseproducts")
public class PurchaseProduct {
	
	@Id
	@Column
	private String purchaseid;
	
	@Column
	private String purchasedate;
	
	@Column
	private String username;
	
	@Column
	private String productid;
	
	@Column
	private String productname;
	
	@Column
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
