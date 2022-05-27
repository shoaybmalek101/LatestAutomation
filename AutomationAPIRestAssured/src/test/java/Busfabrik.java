import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;



public class Busfabrik {
   
	
	@Test
	public void TC01_GetAllCampers()
	{
		//specify base URI
		RestAssured.baseURI="http://203.88.157.74:1111/campers";
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
