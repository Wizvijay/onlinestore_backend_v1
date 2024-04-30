package Com.edex.loginform.Exception;

	public class ProductNotFoundException extends RuntimeException {
	    public ProductNotFoundException(int id) {
	        super("Could not found the user with id "+id);
	    }

	}


