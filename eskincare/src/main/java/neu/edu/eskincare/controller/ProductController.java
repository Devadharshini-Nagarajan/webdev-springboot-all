package neu.edu.eskincare.controller;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;

import neu.edu.eskincare.dto.ProductDTO;
import neu.edu.eskincare.dto.UserDTO;
import neu.edu.eskincare.entity.Product;
import neu.edu.eskincare.entity.User;
import neu.edu.eskincare.repository.ProductRepository;
import neu.edu.eskincare.service.ProductService;
import neu.edu.eskincare.service.UserService;

@RestController
@RequestMapping("/eskincare")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductRepository productRepository;
	
	@RequestMapping(value= "/getProducts", method= RequestMethod.GET)
	public ResponseEntity<ArrayList<Product>> getAllUsers() {
		ArrayList<Product> products = productService.getAllProducts();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}
	
	
//	@RequestMapping(value= "/getProductsByCompany/{company}", method= RequestMethod.GET)
//	public ResponseEntity<ArrayList<Product>> getAllProductsByCompany(@PathVariable String company) {
//		ArrayList<Product> products = productService.getAllProductsByCompany(company);
//		return new ResponseEntity<>(products, HttpStatus.OK);
//
//	}
	
	@RequestMapping(value= "/getProductsByCategory/{category}", method= RequestMethod.GET)
	public ResponseEntity<ArrayList<Product>> getAllProductsByCategory(@PathVariable String company) {
		ArrayList<Product> products = productService.getAllProductsByCategory(company);
		return new ResponseEntity<>(products, HttpStatus.OK);

	}
	
	@RequestMapping(value= "/getProductsByBrand/{brand}", method= RequestMethod.GET)
	public ResponseEntity<ArrayList<Product>> getAllProductsByBrand(@PathVariable String brand) {
		ArrayList<Product> products = productService.getAllProductsByBrand(brand);
		return new ResponseEntity<>(products, HttpStatus.OK);

	}
	
	@RequestMapping(value= "/getProductByProductid/{productid}", method= RequestMethod.GET)
	public ResponseEntity<Product> getProductByProductid(@PathVariable String productid) {
		System.out.println("productid" + productid);
		Product product = productService.getProductByProductid(productid);
		return new ResponseEntity<>(product, HttpStatus.OK);

	}
	
//	old 
//	@RequestMapping(value= "/addProduct", method= RequestMethod.POST)
//	public ResponseEntity<Product> createProduct(@RequestBody ProductDTO productDTO) {
//		Product products = productService.insertProduct(productDTO);
//		return new ResponseEntity<>(products, HttpStatus.OK);
//	}

	@RequestMapping(value= "/addProduct", method= RequestMethod.POST)
	public ResponseEntity<Product> createProduct(MultipartHttpServletRequest request) throws SerialException, SQLException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		String productJson = request.getParameter("productdata");
		ProductDTO productDTO = objectMapper.readValue(productJson, ProductDTO.class);
	    MultipartFile imageFile = request.getFile("imagefile");
	    
		Product products = productService.insertProduct(productDTO, imageFile);
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@GetMapping("/products/{productid}/image")
	public ResponseEntity<byte[]> getProductImage(@PathVariable String productid) throws SQLException {
	    Optional<Product> optionalProduct = productRepository.findByProductid(productid);
	    if (optionalProduct.isPresent()) {
	        Blob imageBlob = optionalProduct.get().getImagefile();
	        if (imageBlob != null) {
	            byte[] imageBytes = imageBlob.getBytes(1, (int) imageBlob.length());
	            String base64Image = Base64.getEncoder().encodeToString(imageBytes);
	            HttpHeaders headers = new HttpHeaders();
	            headers.setContentType(MediaType.IMAGE_JPEG);
	            return new ResponseEntity<>(Base64.getDecoder().decode(base64Image), headers, HttpStatus.OK);
	        }
	    }
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	
	@RequestMapping(value= "/deleteProduct/{productid}", method= RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteProduct(@PathVariable String productid){
		boolean isDeleted = productService.deleteProduct(productid);
		return new ResponseEntity<>(isDeleted, HttpStatus.OK);
	}
	
	
	@RequestMapping(value= "/updateProduct", method= RequestMethod.PUT)
	public ResponseEntity<Product> updateProduct(MultipartHttpServletRequest request) throws SerialException, SQLException, IOException{
		ObjectMapper objectMapper = new ObjectMapper();
		String productJson = request.getParameter("productdata");
		ProductDTO productDTO = objectMapper.readValue(productJson, ProductDTO.class);
	    MultipartFile imageFile = request.getFile("imagefile");
		Product product = productService.updateProduct(productDTO, imageFile);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}
}
