package neu.edu.sephora.dto;

import java.sql.Blob;



import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import neu.edu.sephora.*;

public class ProductDTO {
	private String productid;
	private String name;
	private String description;
	private String company;
	private String category;
	private String image;
	private String brand;
	private int rating;
	private int quantityml;
	private int sold;
	private int price;
	private boolean isfav;
	private boolean instock;
	private int discount;
	@JsonSerialize(using = BlobSerializer.class)
	private Blob imagefile;
	private String createdby;
	private String createdat;
	
	
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	public String getCreatedat() {
		return createdat;
	}
	public void setCreatedat(String createdat) {
		this.createdat = createdat;
	}
	public Blob getImagefile() {
		return imagefile;
	}
	public void setImagefile(Blob imagefile) {
		this.imagefile = imagefile;
	}
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public int getQuantityml() {
		return quantityml;
	}
	public void setQuantityml(int quantityml) {
		this.quantityml = quantityml;
	}
	public int getSold() {
		return sold;
	}
	public void setSold(int sold) {
		this.sold = sold;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public boolean getIsfav() {
		return isfav;
	}
	public void setIsfav(boolean isfav) {
		this.isfav = isfav;
	}
	public boolean getInstock() {
		return instock;
	}
	public void setInstock(boolean instock) {
		this.instock = instock;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
}
