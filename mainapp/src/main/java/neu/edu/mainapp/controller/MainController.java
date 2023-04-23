package neu.edu.mainapp.controller;


import org.springframework.beans.factory.annotation.Autowired; 


import org.springframework.beans.factory.annotation.Qualifier; 
import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RestController; 
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import neu.edu.mainapp.dto.ProductDTO;
import neu.edu.mainapp.entity.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;

@RestController
@RequestMapping("/main")
public class MainController {
	
	@Autowired 
	@Qualifier("eurekaTemplate") 
	private RestTemplate restEurekaTemplate;
	
	public ArrayList<Product> getAllProductsFromCompanies() {
		String url = "http://sephora-server/sephora/getProducts"; 
		
		@SuppressWarnings("unchecked") 
		ArrayList<Product> postForObject = restEurekaTemplate.getForObject(url, ArrayList.class); 
		
		return postForObject;	
	} 
	
	@GetMapping("/getProductById/{productid}") 
	public ResponseEntity<Product> getProductById(@PathVariable String productid) {
		String[] parts = productid.split("-");
		String company = parts[0];
		String url = "http://sephora-server/" + company + "/getProductByProductid/" + productid + "/"; 

		Product postForObject = restEurekaTemplate.getForObject(url, Product.class); 

		return new ResponseEntity<>(postForObject, HttpStatus.OK); 
	}
	
	@GetMapping("/getProducts") 
	public ResponseEntity<ArrayList<Product>> getAllProducts() {
//		String url = "http://sephora-server/sephora/getProducts"; 
//		
//		@SuppressWarnings("unchecked") 
//		ArrayList<ProductDTO> postForObject = restEurekaTemplate.getForObject(url, ArrayList.class); 
		ArrayList<Product> postForObject = getAllProductsFromCompanies();
		return new ResponseEntity<>(postForObject, HttpStatus.OK); 
	}
	
	
	@GetMapping("/getProducts/{company}") 
	public ResponseEntity<ArrayList<ProductDTO>> getAllProductsByCompany(@PathVariable String company) {
		String url = "http://sephora-server/" + company + "/getProducts/";
		
		@SuppressWarnings("unchecked") 
		ArrayList<ProductDTO> postForObject = restEurekaTemplate.getForObject(url, ArrayList.class); 
		return new ResponseEntity<>(postForObject, HttpStatus.OK); 
	}
	
	
}
