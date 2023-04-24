package neu.edu.yesstyle.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import neu.edu.yesstyle.dto.ProductDTO;
import neu.edu.yesstyle.dto.UserDTO;
import neu.edu.yesstyle.entity.Product;
import neu.edu.yesstyle.entity.User;
import neu.edu.yesstyle.repository.ProductRepository;
import java.util.UUID;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public ArrayList<Product> getAllProducts() {
		ArrayList<Product> products = new ArrayList<>();
		productRepository.findAll().forEach(products::add);
		return products;	
	}
	
//	public ArrayList<Product> getAllProductsByCompany(String company) {	
//		ArrayList<Product> products = new ArrayList<>();
//		if(company == null) {
//			productRepository.findAll().forEach(products::add);
//		} else {
//			productRepository.findByCompany(company).forEach(products::add);
//		}
//		return products;
//	}
	
	public ArrayList<Product> getAllProductsByCategory(String category) {	
		ArrayList<Product> products = new ArrayList<>();
		if(category == null) {
			productRepository.findAll().forEach(products::add);
		} else {
			productRepository.findByCategory(category).forEach(products::add);
		}
		return products;
	}
	
	public ArrayList<Product> getAllProductsByBrand(String brand) {	
		ArrayList<Product> products = new ArrayList<>();
		if(brand == null) {
			productRepository.findAll().forEach(products::add);
		} else {
			productRepository.findByBrand(brand).forEach(products::add);
		}
		return products;
	}
	
	public Product getProductByProductid(String productid) {	
		Optional<Product> product = productRepository.findByProductid(productid);
		if(product.isPresent()) {
			return product.get();
		} else {
			return new Product();
		}
	}
	
	public String getProductId() {
		String uuid = UUID.randomUUID().toString();
		uuid = "yesstyle-" + uuid;
		return uuid;
	}
	
//	public Product insertProduct(ProductDTO productDTO) {
//		
//		Product product = new Product();
//		product.setProductid(getProductId());
//		product.setName(productDTO.getName());
//		product.setDescription(productDTO.getDescription());
//		product.setCompany(productDTO.getCompany());
//		product.setCategory(productDTO.getCategory());
//		product.setImage(productDTO.getImage());
//		product.setBrand(productDTO.getBrand());
//		product.setRating(productDTO.getRating());
//		product.setQuantityml(productDTO.getQuantityml());
//		product.setSold(productDTO.getSold());
//		product.setPrice(productDTO.getPrice());
//		product.setIsfav(productDTO.getIsfav());
//		product.setInstock(productDTO.getInstock());
//		product.setDiscount(productDTO.getDiscount());
//		product.setImagefile(productDTO.getImagefile());
//		
//		Product savedProduct = null;
//		
//		try {
//			savedProduct = productRepository.save(product);
//		} catch(Exception ex) {
//			System.out.println("Exception here - " + ex.getMessage());
//		}
//		return savedProduct;
//	}
	
	
	public Product insertProduct(ProductDTO productDTO,MultipartFile imageFile) throws SerialException, SQLException, IOException {
		
		Product product = new Product();
		product.setProductid(getProductId());
		product.setName(productDTO.getName());
		product.setDescription(productDTO.getDescription());
		product.setCompany(productDTO.getCompany());
		product.setCategory(productDTO.getCategory());
		product.setImage(productDTO.getImage());
		product.setBrand(productDTO.getBrand());
		product.setRating(productDTO.getRating());
		product.setQuantityml(productDTO.getQuantityml());
		product.setSold(productDTO.getSold());
		product.setPrice(productDTO.getPrice());
		product.setIsfav(productDTO.getIsfav());
		product.setInstock(productDTO.getInstock());
		product.setDiscount(productDTO.getDiscount());
		product.setCreatedby(productDTO.getCreatedby());
		product.setCreatedat(productDTO.getCreatedat());
		if (imageFile != null) {
			product.setImagefile(new SerialBlob(imageFile.getBytes()));
		} else {
			product.setImagefile(productDTO.getImagefile());
		}
		Product savedProduct = null;
		
		try {
			savedProduct = productRepository.save(product);
		} catch(Exception ex) {
			System.out.println("Exception here - " + ex.getMessage());
		}
		return savedProduct;
	}
	
	public boolean deleteProduct(String productid) {
		Optional<Product> product = productRepository.findByProductid(productid);
		if(product.isPresent()) {
			productRepository.delete(product.get());
			return true;
		}else {
			return false;
		}
	}
	
	public Product updateProduct(ProductDTO productDTO,MultipartFile imageFile) throws SerialException, SQLException, IOException {
		Optional<Product> product = productRepository.findByProductid(productDTO.getProductid());
		if(product.isPresent()) {
			Product _product = product.get();
			_product.setProductid(productDTO.getProductid());
			_product.setName(productDTO.getName());
			_product.setDescription(productDTO.getDescription());
			_product.setCompany(productDTO.getCompany());
			_product.setCategory(productDTO.getCategory());
			_product.setImage(productDTO.getImage());
			_product.setBrand(productDTO.getBrand());
			_product.setRating(productDTO.getRating());
			_product.setQuantityml(productDTO.getQuantityml());
			_product.setSold(productDTO.getSold());
			_product.setPrice(productDTO.getPrice());
			_product.setIsfav(productDTO.getIsfav());
			_product.setInstock(productDTO.getInstock());
			_product.setDiscount(productDTO.getDiscount());
			_product.setCreatedby(productDTO.getCreatedby());
			_product.setCreatedat(productDTO.getCreatedat());
			if (imageFile != null) {
				_product.setImagefile(new SerialBlob(imageFile.getBytes()));
			}
			_product = productRepository.save(_product);
			return _product;
		}
		return null;
		
	}
}
