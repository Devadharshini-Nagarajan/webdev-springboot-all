package neu.edu.mainapp.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import neu.edu.mainapp.dto.PurchaseProductDTO;
import neu.edu.mainapp.entity.PurchaseProduct;
import neu.edu.mainapp.service.PurchaseProductService;

@RestController
@RequestMapping("/main")
public class PurchaseProductController {
	@Autowired
	private PurchaseProductService purchaseProductService;
	
	
	@RequestMapping(value= "/getPurchaseProducts", method= RequestMethod.GET)
	public ResponseEntity<ArrayList<PurchaseProduct>> getAllPurchaseProducts() {
		ArrayList<PurchaseProduct> users = purchaseProductService.getAllPurchaseProducts();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@RequestMapping(value= "/addPurchaseProduct", method= RequestMethod.POST)
	public ResponseEntity<PurchaseProduct> insertPurchaseProduct(@RequestBody PurchaseProductDTO purchaseProductDTO) {
		PurchaseProduct purchaseProducts = purchaseProductService.insertPurchaseProduct(purchaseProductDTO);
		return new ResponseEntity<>(purchaseProducts, HttpStatus.OK);
	}
}
