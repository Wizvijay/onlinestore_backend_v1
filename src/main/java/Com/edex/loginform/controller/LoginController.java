	package Com.edex.loginform.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Com.edex.*;
import Com.edex.loginform.LoginformApplication;
import Com.edex.loginform.model.LoginForm;
import Com.edex.loginform.repo.LoginformRepo;
import Com.edex.*;

@RestController
@RequestMapping("/form")
public class LoginController {
	
	@Autowired
	private LoginformRepo loginformRepo;
	 
	@PostMapping("/signup")
	public ResponseEntity<?> signMapping(@RequestBody LoginForm loginform){
		System.out.print(loginform.getUsername());
		 
		 LoginForm login = loginformRepo.saveAndFlush(loginform);
				 return ResponseEntity.status(HttpStatus.OK)
				      		.body(login);
	}
	@GetMapping("/getuser")
	public ResponseEntity<?> GetMapping(@RequestParam String username, @RequestParam String password){
		LoginForm login = loginformRepo.findBy(username, password);
		return ResponseEntity.status(HttpStatus.OK)
	      		.body(login);
	}
}


