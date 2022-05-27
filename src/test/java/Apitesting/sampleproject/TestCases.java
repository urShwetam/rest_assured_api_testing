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
	
	@Test(description="Fetching the user",testName="testFetchUser")
	public void testFetchUser() {
		ExtentReportManager.test = ExtentReportManager.getTest();
		ExtentReportManager.test.log(LogStatus.INFO, "Specifying the base URI","https://reqres.in");
		ExtentReportManager.test.log(LogStatus.INFO, "API call","GET");
		ExtentReportManager.test.log(LogStatus.INFO, "Resource route","api/users?page=2");
		ExtentReportManager.test.log(LogStatus.INFO, "Value compared","page");
		RestAssured.baseURI = "https://reqres.in";
		given().when().get("api/users?page=2").then().spec(res).assertThat().body("page", IsEqual.equalTo(2));
		
	}
	
	@Test(description="Creating the user",testName="testCreateUser")
	public void testCreateUser() {
		ExtentReportManager.test = ExtentReportManager.getTest();
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
	
	@Test(description="Registering the user",testName="testRegisterUser")
	public void testRegisterUser() {
		ExtentReportManager.test = ExtentReportManager.getTest();
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
	
	@Test(description="Getting list of the user details",testName="testGetListResourceUser")
	public void testGetListResourceUser() {
		ExtentReportManager.test = ExtentReportManager.getTest();
		ExtentReportManager.test.log(LogStatus.INFO, "Specifying the base URI","https://reqres.in");
		ExtentReportManager.test.log(LogStatus.INFO, "API call","GET");
		ExtentReportManager.test.log(LogStatus.INFO, "Resource route","/api/unknown");
		ExtentReportManager.test.log(LogStatus.INFO, "Value compared","page, data[0].id and data[1].year");
		RestAssured.baseURI = "https://reqres.in";
		given().when().get("/api/unknown").then().assertThat().body("page", IsEqual.equalTo(1)).body("data[0].id", IsEqual.equalTo(1)).body("data[1].year", IsEqual.equalTo(2001));
	}
	
	
	@Test(description="Getting single user details",testName="testGetListSingleUser")
	public void testGetListSingleUser() {
		ExtentReportManager.test = ExtentReportManager.getTest();
		ExtentReportManager.test.log(LogStatus.INFO, "Specifying the base URI","https://reqres.in");
		ExtentReportManager.test.log(LogStatus.INFO, "API call","GET");
		ExtentReportManager.test.log(LogStatus.INFO, "Resource route","api/unknown/2");
		ExtentReportManager.test.log(LogStatus.INFO, "Value compared","data.name and data.id");
		RestAssured.baseURI = "https://reqres.in";
		given().when().get("api/unknown/2").then().assertThat().body("data.name", IsEqual.equalTo("fuchsia rose")).body("data.id", IsEqual.equalTo(2));
	}
	
	@Test(description="Logging in user",testName="testLoginUser")
	public void testLoginUser() {
		ExtentReportManager.test = ExtentReportManager.getTest();
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
	
	@Test(description="Checking login success/fail",testName="testLoginUserUnsuccessfull")
	public void testLoginUserUnsuccessfull() {
		ExtentReportManager.test = ExtentReportManager.getTest();
		ExtentReportManager.test.log(LogStatus.INFO, "Specifying the base URI","https://reqres.in");
		ExtentReportManager.test.log(LogStatus.INFO, "API call","POST");
		ExtentReportManager.test.log(LogStatus.INFO, "Body passed","email=peter@klaven");
		ExtentReportManager.test.log(LogStatus.INFO, "Resource route","api/login");
		ExtentReportManager.test.log(LogStatus.INFO, "Value compared","error message");
		RestAssured.baseURI = "https://reqres.in";
		HashMap<String,String> params = new HashMap<>();
		params.put("email", "peter@klaven");
		given().when().contentType("application/json").body(params).post("api/login").then().assertThat().body("error", IsEqual.equalTo("jhgj password"));
	}
	
	@AfterClass
	public void closeReport() {
		ExtentReportManager.reports.flush();
	}
}
