package neu.edu.mainapp.service;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import neu.edu.mainapp.dto.PurchaseProductDTO;
import neu.edu.mainapp.entity.PurchaseProduct;
import neu.edu.mainapp.entity.User;
import neu.edu.mainapp.repository.PurchaseProductRepository;

@Service
public class PurchaseProductService {
	@Autowired
	private PurchaseProductRepository purchaseProductRepository;
	
	public ArrayList<PurchaseProduct> getAllPurchaseProducts() {
		ArrayList<PurchaseProduct> purchaseproducts = new ArrayList<>();
		purchaseProductRepository.findAll().forEach(purchaseproducts::add);
		return purchaseproducts;	
	}
	
	
	public String getPurchaseId() {
		String uuid = UUID.randomUUID().toString();
		return uuid;
	}
	
	public PurchaseProduct insertPurchaseProduct(PurchaseProductDTO purchaseProductDTO) {
		PurchaseProduct purchaseproduct = new PurchaseProduct();
		purchaseproduct.setPurchaseid(getPurchaseId());
		purchaseproduct.setPurchasedate(purchaseProductDTO.getPurchasedate());
		purchaseproduct.setUsername(purchaseProductDTO.getUsername());
		purchaseproduct.setProductid(purchaseProductDTO.getProductid());
		purchaseproduct.setProductname(purchaseProductDTO.getProductname());
		purchaseproduct.setCategory(purchaseProductDTO.getCategory());
		
		PurchaseProduct savedPurchaseProduct = null;
		
		try {
			savedPurchaseProduct = purchaseProductRepository.save(purchaseproduct);
		} catch(Exception ex) {
			System.out.println("Exception here - " + ex.getMessage());
		}
		return savedPurchaseProduct;
	}

}
