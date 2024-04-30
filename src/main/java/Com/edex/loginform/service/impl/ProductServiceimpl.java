package Com.edex.loginform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Com.edex.loginform.model.Product;
import Com.edex.loginform.repo.ProductRepo;
import Com.edex.loginform.service.ProductService;

@Service
public class ProductServiceimpl implements ProductService {
	@Autowired
	private ProductRepo productRepo;
	
	
	@Override
	public List<Product> getAllProducts(){
		return productRepo.findAll();
	}
	
	
}
