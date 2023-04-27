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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import neu.edu.mainapp.dto.ProductDTO;
import neu.edu.mainapp.entity.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping("/main")
public class MainController {
	
	@Autowired 
	@Qualifier("eurekaTemplate") 
	private RestTemplate restEurekaTemplate;
	
	public List<Product> getAllProductsFromCompanies() throws JsonMappingException, JsonProcessingException {
		String sephoraurl = "http://sephora-server/sephora/getProducts"; 
		
		@SuppressWarnings("unchecked") 
		ArrayList<Product> sephorapostForObject = restEurekaTemplate.getForObject(sephoraurl, ArrayList.class); 
		
		String yesstyleurl = "http://yesstyle-server/yesstyle/getProducts"; 
		
		@SuppressWarnings("unchecked") 
		ArrayList<Product> yesstyleurlpostForObject = restEurekaTemplate.getForObject(yesstyleurl, ArrayList.class); 
		
		String eskincareurl = "http://eskincare-server/eskincare/getProducts"; 
		
		
		@SuppressWarnings("unchecked") 
		ArrayList<Product> eskincareurlpostForObject = restEurekaTemplate.getForObject(eskincareurl, ArrayList.class); 
		
		// Convert ArrayList<Product> to JSON string
		ObjectMapper mapper = new ObjectMapper();
		String sjson = mapper.writeValueAsString(sephorapostForObject);
		String yjson = mapper.writeValueAsString(yesstyleurlpostForObject);
		String ejson = mapper.writeValueAsString(eskincareurlpostForObject);

		// Convert JSON string to List<Product>
		List<Product> sproductList = mapper.readValue(ejson, new TypeReference<List<Product>>(){});
		List<Product> yproductList = mapper.readValue(yjson, new TypeReference<List<Product>>(){});
		List<Product> eproductList = mapper.readValue(sjson, new TypeReference<List<Product>>(){});

		
		List<Product> combinedList = new ArrayList<Product>();
		combinedList.addAll(sproductList);
		combinedList.addAll(yproductList);
		combinedList.addAll(eproductList);
		
		Collections.sort(combinedList, Comparator.comparingDouble(Product::getPrice));
		return combinedList;	
	} 
	
	@GetMapping("/getProductById/{productid}") 
	public ResponseEntity<Product> getProductById(@PathVariable String productid) {
		String[] parts = productid.split("-");
		String company = parts[0];
		String url = "http://" + company + "-server/" + company + "/getProductByProductid/" + productid + "/";

		Product postForObject = restEurekaTemplate.getForObject(url, Product.class); 

		return new ResponseEntity<>(postForObject, HttpStatus.OK); 
	}
	
	@GetMapping("/getProducts") 
	public ResponseEntity<List<Product>> getAllProducts() throws JsonMappingException, JsonProcessingException {
		List<Product> postForObject = getAllProductsFromCompanies();
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
