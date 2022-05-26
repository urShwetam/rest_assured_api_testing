package Apitesting.sampleproject;

import org.hamcrest.core.IsEqual;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

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
		ExtentReportManager.createReport();
	}
	
	@Test
	public void testFetchUser() {
		ExtentReportManager.test = ExtentReportManager.reports.startTest("testFetchUser","Fetching the user details");
		ExtentReportManager.test.log(LogStatus.INFO, "Specifying the base URI","https://reqres.in");
		ExtentReportManager.test.log(LogStatus.INFO, "API call","GET");
		ExtentReportManager.test.log(LogStatus.INFO, "Resource route","api/users?page=2");
		ExtentReportManager.test.log(LogStatus.INFO, "Value compared","page");
		RestAssured.baseURI = "https://reqres.in";
		given().when().get("api/users?page=2").then().spec(res).assertThat().body("page", IsEqual.equalTo(2));
		
	}
	
	@Test
	public void testCreateUser() {
		ExtentReportManager.test = ExtentReportManager.reports.startTest("testCreateUser","Creating the user");
		ExtentReportManager.test.log(LogStatus.INFO, "Specifying the base URI","https://reqres.in");
		ExtentReportManager.test.log(LogStatus.INFO, "API call","POST");
		ExtentReportManager.test.log(LogStatus.INFO, "Body passed","name=Utkarsh and job=Qa");
		ExtentReportManager.test.log(LogStatus.INFO, "Resource route","api/users");
		ExtentReportManager.test.log(LogStatus.INFO, "Value compared","name");
		RestAssured.baseURI = "https://reqres.in";
		HashMap<String,String> params = new HashMap<>();
		params.put("name", "Utkarsh");
		params.put("job", "Qa");
		given().when().contentType("application/json").body(params).post("api/users").then().assertThat().body("name", IsEqual.equalTo("Utkarsh"));
	}
	
	@Test
	public void testRegisterUser() {
		ExtentReportManager.test = ExtentReportManager.reports.startTest("testRegisterUser","Registering the user");
		ExtentReportManager.test.log(LogStatus.INFO, "Specifying the base URI","https://reqres.in");
		ExtentReportManager.test.log(LogStatus.INFO, "API call","POST");
		ExtentReportManager.test.log(LogStatus.INFO, "Body passed","email=eve.holt@reqres.in and password=pistol");
		ExtentReportManager.test.log(LogStatus.INFO, "Resource route","api/register");
		ExtentReportManager.test.log(LogStatus.INFO, "Value compared","id");
		RestAssured.baseURI = "https://reqres.in";
		HashMap<String,String> params = new HashMap<>();
		params.put("email", "eve.holt@reqres.in");
		params.put("password", "pistol");
		given().when().contentType("application/json").body(params).post("api/register").then().assertThat().body("id", IsEqual.equalTo(4));
	}
	
	@Test
	public void testGetListResourceUser() {
		ExtentReportManager.test = ExtentReportManager.reports.startTest("testGetListResourceUser","Fetching the list of user details");
		ExtentReportManager.test.log(LogStatus.INFO, "Specifying the base URI","https://reqres.in");
		ExtentReportManager.test.log(LogStatus.INFO, "API call","GET");
		ExtentReportManager.test.log(LogStatus.INFO, "Resource route","/api/unknown");
		ExtentReportManager.test.log(LogStatus.INFO, "Value compared","page, data[0].id and data[1].year");
		RestAssured.baseURI = "https://reqres.in";
		given().when().get("/api/unknown").then().assertThat().body("page", IsEqual.equalTo(1)).body("data[0].id", IsEqual.equalTo(1)).body("data[1].year", IsEqual.equalTo(2001));
	}
	
	
	@Test
	public void testGetListSingleUser() {
		ExtentReportManager.test = ExtentReportManager.reports.startTest("testGetListSingleUser","Fetching single user details");
		ExtentReportManager.test.log(LogStatus.INFO, "Specifying the base URI","https://reqres.in");
		ExtentReportManager.test.log(LogStatus.INFO, "API call","GET");
		ExtentReportManager.test.log(LogStatus.INFO, "Resource route","api/unknown/2");
		ExtentReportManager.test.log(LogStatus.INFO, "Value compared","data.name and data.id");
		RestAssured.baseURI = "https://reqres.in";
		given().when().get("api/unknown/2").then().assertThat().body("data.name", IsEqual.equalTo("fuchsia rose")).body("data.id", IsEqual.equalTo(2));
	}
	
	@Test
	public void testLoginUser() {
		ExtentReportManager.test = ExtentReportManager.reports.startTest("testLoginUser","Performing login for the user");
		ExtentReportManager.test.log(LogStatus.INFO, "Specifying the base URI","https://reqres.in");
		ExtentReportManager.test.log(LogStatus.INFO, "API call","POST");
		ExtentReportManager.test.log(LogStatus.INFO, "Body passed","email=eve.holt@reqres.in and password=pistol");
		ExtentReportManager.test.log(LogStatus.INFO, "Resource route","api/login");
		ExtentReportManager.test.log(LogStatus.INFO, "Value compared","token returned");
		RestAssured.baseURI = "https://reqres.in";
		HashMap<String,String> params = new HashMap<>();
		params.put("email", "eve.holt@reqres.in");
		params.put("password", "pistol");
		given().when().contentType("application/json").body(params).post("api/login").then().assertThat().body("token", IsEqual.equalTo("QpwL5tke4Pnpja7X4"));
	}
	
	@Test
	public void testLoginUserUnsuccessfull() {
		ExtentReportManager.test = ExtentReportManager.reports.startTest("testLoginUserUnsuccessfull","Checking login status for the user");
		ExtentReportManager.test.log(LogStatus.INFO, "Specifying the base URI","https://reqres.in");
		ExtentReportManager.test.log(LogStatus.INFO, "API call","POST");
		ExtentReportManager.test.log(LogStatus.INFO, "Body passed","email=peter@klaven");
		ExtentReportManager.test.log(LogStatus.INFO, "Resource route","api/login");
		ExtentReportManager.test.log(LogStatus.INFO, "Value compared","error message");
		RestAssured.baseURI = "https://reqres.in";
		HashMap<String,String> params = new HashMap<>();
		params.put("email", "peter@klaven");
		given().when().contentType("application/json").body(params).post("api/login").then().assertThat().body("error", IsEqual.equalTo("Missing password"));
	}
	
	@AfterClass
	public void closeReport() {
		ExtentReportManager.reports.flush();
	}
}
