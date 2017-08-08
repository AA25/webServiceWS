/*
* @Author: Ade Akingbade	
* @Date: 1st April 2017
* @IDE: Eclipse Java EE IDE Neon.3
* 
* Basic user class with useful public methods
* 
*/

package whatscoreWebService;

//import java.util.*;
 
public class user {
	
	private String userName = "";
	private String password = "";
	private String firstName = "";
	private String lastName = "";
	private String favTeam = "";
	
	public user(String un, String pw, String fn, String ln, String ft){
		this.firstName = fn;
		this.lastName = ln;
		this.userName = un;
		this.favTeam = ft;
		this.password = pw;
	}
	
	public String getFN(){
		return this.firstName;
	}
	
	public String getLN(){
		return this.lastName;
	}
	
	public String getUN(){
		return this.userName;
	}
	
	public String getFT(){
		return this.favTeam;
	}
	
	public String getPW(){
		return this.password;
	}
}
