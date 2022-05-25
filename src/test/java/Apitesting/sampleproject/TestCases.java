package Apitesting.sampleproject;

import org.hamcrest.core.IsEqual;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.*;

import java.util.HashMap;


public class TestCases {

	ResponseSpecification res;
	@BeforeClass
	public void setSpecification() {
		res = RestAssured.expect();
		res.statusLine("HTTP/1.1 200 OK");
		res.contentType(ContentType.JSON);
	}
	
	@Test
	public void testFetchUser() {
		RestAssured.baseURI = "https://reqres.in";
		given().when().get("api/users?page=2").then().spec(res).assertThat().body("page", IsEqual.equalTo(2));
	}
	
	@Test
	public void testCreateUser() {
		RestAssured.baseURI = "https://reqres.in";
		HashMap<String,String> params = new HashMap<>();
		params.put("name", "Utkarsh");
		params.put("job", "Qa");
		given().when().contentType("application/json").body(params).post("api/users").then().assertThat().body("name", IsEqual.equalTo("Utkarsh"));
	}
	
	@Test
	public void testRegisterUser() {
		RestAssured.baseURI = "https://reqres.in";
		HashMap<String,String> params = new HashMap<>();
		params.put("email", "eve.holt@reqres.in");
		params.put("password", "pistol");
		given().when().contentType("application/json").body(params).post("api/register").then().assertThat().body("id", IsEqual.equalTo(4));
	}
	
	@Test
	public void testGetListResourceUser() {
		RestAssured.baseURI = "https://reqres.in";
		given().when().get("/api/unknown").then().assertThat().body("page", IsEqual.equalTo(1));
		given().when().get("/api/unknown").then().assertThat().body("data[0].id", IsEqual.equalTo(1));
		given().when().get("/api/unknown").then().assertThat().body("data[1].year", IsEqual.equalTo(2001));
	}
	
	
	@Test
	public void testGetListSingleUser() {
		RestAssured.baseURI = "https://reqres.in";
		given().when().get("api/unknown/2").then().assertThat().body("data.name", IsEqual.equalTo("fuchsia rose"));
		given().when().get("api/unknown/2").then().assertThat().body("data.id", IsEqual.equalTo(2));

	}
	
	@Test
	public void testLoginUser() {
		RestAssured.baseURI = "https://reqres.in";
		HashMap<String,String> params = new HashMap<>();
		params.put("email", "eve.holt@reqres.in");
		params.put("password", "pistol");
		given().when().contentType("application/json").body(params).post("api/login").then().assertThat().body("token", IsEqual.equalTo("QpwL5tke4Pnpja7X4"));
	}
	
	@Test
	public void testLoginUserUnsuccessfull() {
		RestAssured.baseURI = "https://reqres.in";
		HashMap<String,String> params = new HashMap<>();
		params.put("email", "peter@klaven");
		given().when().contentType("application/json").body(params).post("api/login").then().assertThat().body("error", IsEqual.equalTo("Missing password"));
	}
}
