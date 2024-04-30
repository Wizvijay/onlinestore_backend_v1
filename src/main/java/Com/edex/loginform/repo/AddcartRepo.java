package Com.edex.loginform.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import Com.edex.loginform.model.Addcart;


public interface AddcartRepo extends JpaRepository<Addcart, Integer>{

	
}
