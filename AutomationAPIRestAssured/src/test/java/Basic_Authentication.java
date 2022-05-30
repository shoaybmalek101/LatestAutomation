import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Basic_Authentication {

	
	@Test
	public void Authorization()
	{
		//specify base URI
		RestAssured.baseURI="https://postman-echo.com/basic-auth";
		
		//Basic Authentication
		PreemptiveBasicAuthScheme authscheme= new PreemptiveBasicAuthScheme();
		authscheme.setUserName("postman");
		authscheme.setPassword("password");
		
		RestAssured.authentication=authscheme;
		//Request object
		RequestSpecification httprequest=RestAssured.given();
		//Response object
		Response response=httprequest.request(Method.GET,"/");
		//print response in console window
		String responsebody=response.getBody().asString();
		System.out.println("Response Body is:"+responsebody);
		
		//status code validation
		int statuscode = response.getStatusCode();	
		AssertJUnit.assertEquals(statuscode,200);
		
	}
}
