/*
* @Author: Ade Akingbade	
* @Date: 1st April 2017
* @IDE: Eclipse Java EE IDE Neon.3
* 
* This is only a demonstration and only serves another project of mine.
* 
*/

// This is the main implementation of the web service. The service provides 3 endpoints that can be invoked by the client. 
// Oracle GlassFish Server is used to run the web service.

package whatscoreWebService;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService; 

@WebService(name="whatscoreCatalog", portName="whatscoreCatalogPort", serviceName="whatscoreCatalogService",
targetNamespace="http://www.whatscore.com")

public class WhatscoreWS {
	WhatscoreWSImpl productService = new WhatscoreWSImpl();
	
	//Endpoint that allows client to create a user
	@WebMethod(action="register_user", operationName="registerUser")
	public boolean insertUser(String un, String pw, String fn, String ln, String ft){
		return productService.createUserDB(un, pw, fn, ln, ft);
	}
	
	//Endpoint that verfies user and returns user details
	@WebMethod(action="login_user", operationName="loginUser")
	public List<String> loginUser(String un, String pw){
		 if(productService.checkLoginDetails(un, pw) == true){
			 return productService.userDetails(un);
		 }else{
			 return null;
		 }
	}
	
	//Endpoint that updates the status of the user in the DB
	@WebMethod(action="logout_user", operationName="logoutUser")
	public boolean logoutUser(String un){
		return productService.logoutUser(un);
	}

}
