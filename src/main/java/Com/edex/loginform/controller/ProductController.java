package Com.edex.loginform.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Com.edex.loginform.Exception.ProductNotFoundException;
import Com.edex.loginform.model.Addcart;
import Com.edex.loginform.model.LoginForm;
import Com.edex.loginform.model.Product;
import Com.edex.loginform.repo.AddcartRepo;
import Com.edex.loginform.repo.LoginformRepo;
import Com.edex.loginform.repo.ProductRepo;
import Com.edex.loginform.service.ProductService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/product")


public class ProductController {
	@Autowired
	 private ProductRepo productRepo;
	
	@Autowired
	private AddcartRepo addcartRepo;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	private LoginformRepo loginRepo;
		@PostMapping("/addproduct")
		public ResponseEntity<?> addProduct(@RequestBody Product product){
			Product savedEntity =productRepo.saveAndFlush(product);
			return ResponseEntity.status(HttpStatus.OK)
					.body(product);
		}
		@PostMapping("/addcart")
		public ResponseEntity<?> addcart(@RequestBody Addcart cart){
			Addcart cart1 =addcartRepo.saveAndFlush(cart);
			return ResponseEntity.status(HttpStatus.OK)
					.body(cart1);
		}
		@GetMapping("/listproduct")
		public ResponseEntity<List<Product>> getAllProducts(){
			List<Product> products=productService.getAllProducts();
			return new ResponseEntity<>(products,HttpStatus.OK);
			
		}
		
		@GetMapping("/getbrand")
		public ResponseEntity<?> GetBrand(@RequestParam String brandname, @RequestParam String categoryname){
			List<Product> product1 = productRepo.findByName(brandname, categoryname);
			return ResponseEntity.status(HttpStatus.OK)
		      		.body(product1);
		}
		
		@PutMapping("/update/{productid}")
        public ResponseEntity<?> updateProduct(@RequestBody Product updatedProduct, @PathVariable int id) {
            try {
                Product existingProduct = productRepo.findById(id)
                        .orElseThrow(() -> new ProductNotFoundException(id));

                existingProduct.setProductname(updatedProduct.getProductname());
                existingProduct.setDescription(updatedProduct.getDescription());
                existingProduct.setPrice(updatedProduct.getPrice());
                existingProduct.setImage(updatedProduct.getImage());
                existingProduct.setCategoryname(updatedProduct.getCategoryname());

                productRepo.save(existingProduct);

                return ResponseEntity.ok().body("Product updated successfully");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update product");
            }
        }
		@GetMapping("/productget/{productid}")
	     Product getProductById(@PathVariable int productid) {
	         return productRepo.findById(productid)
	                 .orElseThrow(()->new ProductNotFoundException(productid));
	     }
		@PutMapping("/updateproduct/{productid}")
	     public ResponseEntity<?> updateUser(@PathVariable int productid, @RequestBody Product product) {
	         Product product1 = productRepo.findById(productid).orElse(null);
	         if (product1 == null) {
	             return ResponseEntity.notFound().build(); 
	         }
	         product1.setProductname(product.getProductname());    
	         product1.setDescription(product.getDescription());
	         product1.setPrice(product.getPrice());
	         product1.setImage(product.getImage());
	         product1.setCategoryname(product.getCategoryname());
	         
	         Product updatedProduct = productRepo.save(product);
	         return ResponseEntity.ok(updatedProduct); 
	     }
		
		@GetMapping("/getcart")
	     public ResponseEntity<?> getWish(){
	            List<Addcart> add = addcartRepo.findAll();
	                    return ResponseEntity.status(HttpStatus.OK)
	                            .body(add);
	        }
		@DeleteMapping("/deleteproduct/{productid}")
        public ResponseEntity<?> deleteCart(@PathVariable int productid){
         Product product = productRepo.findById(productid).get();
            productRepo.delete(product);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(product);
        }

		@GetMapping("/getcustomer")
	     public ResponseEntity<?> getcustomer(){
	            List<LoginForm> signup = loginRepo.findAll();
	                    return ResponseEntity.status(HttpStatus.OK)
	                            .body(signup);
	        }

}  
