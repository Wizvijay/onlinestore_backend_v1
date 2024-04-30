package Com.edex.loginform.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import Com.edex.loginform.model.LoginForm;
import Com.edex.loginform.model.Product;

public interface ProductRepo extends JpaRepository<Product, Integer>{

	@Query(value = "Select * from Products Where brandname = :brandname AND categoryname = :categoryname", nativeQuery = true)
	List<Product> findByName(String brandname, String categoryname);
}
