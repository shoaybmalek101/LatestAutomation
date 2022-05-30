import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.http.Header;
import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Basic {
	
	public String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
	String email1 =getSaltString()+"@yopmail.com";   
	
	
	
	
	@Test(priority=1)
	public void TestCase_02_SignUpSuccessfull()
	{
		//specify base URI
		RestAssured.baseURI="http://203.88.157.74:1111";
		//Request object
		RequestSpecification httprequest=RestAssured.given();
	
		
		//Request payload sending along with post request
		JSONObject request = new JSONObject();
		request.put("first_name", "Test");
        request.put("last_name", "CK");
        request.put("email_id", email1);
        request.put("password", "Test@1234");
        request.put("mobile", "9876543211");
        request.put("plan_name", "626008c0ab651784a568a600");
        httprequest.header("Content-type","application/json");
        httprequest.body(request.toJSONString());  //Attach data to the request
		
    	//Response object
    	Response response=httprequest.request(Method.POST,"/users/signup");
		//print response in console window
		String responsebody=response.getBody().asString();
		System.out.println("Response Body is:"+responsebody);
		
		//status code validation
		int statuscode = response.getStatusCode();	
		AssertJUnit.assertEquals(statuscode,201);
		
		//success msg validation
		String Confirm_msg=response.jsonPath().get("msg");
		AssertJUnit.assertEquals(Confirm_msg,"User registered successfully!");
		
		//validating headers from response
		String ContentType=response.header("Content-Type");
		AssertJUnit.assertEquals(ContentType,"application/json; charset=utf-8");
		
		//Print all headers
		Headers allheaders=response.headers();  //capture all the headers from the response
		for(Header header:allheaders)
		{
			System.out.println(header.getName()+"   "+header.getValue());
		}
		
		//Verify single value in response body data
		AssertJUnit.assertEquals(responsebody.contains("CK"),true);
		
		/*Extract each nodes in json response
		JsonPath jsonpath=response.jsonPath();
		System.out.println(jsonpath.get("payload"));
		
		
		Assert.assertEquals(jsonpath.get("payload"),"Test","CK");
	
		*/
		
	}	
		
	
	@Test(priority=2)
	public void TC03_Login()
	{
		
		//specify base URI
		RestAssured.baseURI="http://203.88.157.74:1111";
		//Request object
		RequestSpecification httprequest=RestAssured.given();
		JSONObject request = new JSONObject();
        request.put("email_id",email1);
        request.put("password", "Test@1234");
        httprequest.header("Content-type","application/json");
        httprequest.body(request.toJSONString());  //Attach data to the request
		
    	//Response object
    	Response response=httprequest.request(Method.POST,"/users/login");
		//print response in console window
		String responsebody=response.getBody().asString();
		System.out.println("Response Body is:"+responsebody);
		
		//status code validation
		int statuscode = response.getStatusCode();	
		AssertJUnit.assertEquals(statuscode,200);
		
		//success msg validation
		String Confirm_msg1=response.jsonPath().get("msg");
		AssertJUnit.assertEquals(Confirm_msg1,"Login successful");
		
		//validating headers from response
		String ContentType=response.header("Content-Type");
		AssertJUnit.assertEquals(ContentType,"application/json; charset=utf-8");
        
	}
	
	@Test(priority=3)
	public void TC03_ForgotPassword()
	{
		
		//specify base URI
		RestAssured.baseURI="http://203.88.157.74:1111";
		//Request object
		RequestSpecification httprequest=RestAssured.given();
		JSONObject request = new JSONObject();
        request.put("email_id",email1);
      
        httprequest.header("Content-type","application/json");
        httprequest.body(request.toJSONString());  //Attach data to the request
		
    	//Response object
    	Response response=httprequest.request(Method.POST,"/users/forgotPassword");
		//print response in console window
		String responsebody=response.getBody().asString();
		System.out.println("Response Body is:"+responsebody);
		
		//status code validation
		int statuscode = response.getStatusCode();	
		AssertJUnit.assertEquals(statuscode,200);
		
		//success msg validation
		String Confirm_msg1=response.jsonPath().get("msg");
		AssertJUnit.assertEquals(Confirm_msg1,"Link to reset your password has been sent to your email. Please check.");
		
		//validating headers from response
		String ContentType=response.header("Content-Type");
		AssertJUnit.assertEquals(ContentType,"application/json; charset=utf-8");
        
	}
	

}
