import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.*;

import static org.hamcrest.core.IsEqual.equalTo;

import org.json.JSONObject;
import org.junit.*;
import org.openqa.selenium.Cookie;

import com.fasterxml.jackson.databind.ObjectMapper;

import DTO.*;
import Pages.*;
import WebDriverManager.WebDriverManager;
import Endpoints.Endpoints;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import Specifications.RequestSpec;
import utils.FindElementInJsonByRootPath;
import utils.TemplateBuilder;


public class CreateCartEntry {

	ObjectMapper mapper = new ObjectMapper();
	int expectedProductQty = 5;
	String expectedProductCode = "2876350";

	@Test
	public void createCartEntryWithPojo() {
		Cart createCartResponse = given().spec(RequestSpec.requestSpecification).when().post(
				Endpoints.getCreateCartEndpoint()).then().extract().body().as(Cart.class);

		HashMap<String, String> sessionStorage = new HashMap<>();
		sessionStorage.put("guid", createCartResponse.getGuid());

		Product product = new Product();
		product.setCode("2876350");

		Map<String, Object> productInfo = new HashMap<>();
		productInfo.put("product", product);
		productInfo.put("quantity", "5");

		Entry entryPayload = mapper.convertValue(productInfo, Entry.class);
		JSONObject requestPayload = new JSONObject(entryPayload);

		Response addProductToCartResponse = given().spec(RequestSpec.requestSpecification).body(
				requestPayload.toString()).request().post(Endpoints.getAddProductToCartEndpoint(),
				sessionStorage.get("guid"));

		addProductToCartResponse.getBody().equals(JsonSchemaValidator.matchesJsonSchemaInClasspath("JSONschema.json"));
		validateQuantityAndProductCode(addProductToCartResponse);

		executeUiSteps(sessionStorage);
	}

	@Test
	public void createCartEntryWithTemplateBuilder() throws IOException {
		Cart createCartResponse = given().spec(RequestSpec.requestSpecification).when().post(
				Endpoints.getCreateCartEndpoint()).then().extract().body().as(Cart.class);

		HashMap<String, String> sessionStorage = new HashMap<>();
		sessionStorage.put("guid", createCartResponse.getGuid());

		Product product = new Product();
		product.setCode("2876350");

		Map<String, Object> productInfo = new HashMap<>();
		productInfo.put("code", product.getCode());
		productInfo.put("quantity", "5");

		String template = TemplateBuilder.resolveTemplate("src/test/resources/jsonTemplate.txt", productInfo);
		Response addProductToCartResponse = given().spec(RequestSpec.requestSpecification).body(template).request().post(
				Endpoints.getAddProductToCartEndpoint(), sessionStorage.get("guid"));

		addProductToCartResponse.getBody().equals(JsonSchemaValidator.matchesJsonSchemaInClasspath("JSONschema.json"));
		validateQuantityAndProductCode(addProductToCartResponse);

		executeUiSteps(sessionStorage);
	}

	@After
	public void executeAfterTest() {
		WebDriverManager.quitDriver();
	}

	private void validateQuantityAndProductCode(Response response) {
		FindElementInJsonByRootPath.validateResponseBodyElement(response, "quantity", "",
				equalTo(expectedProductQty));
		FindElementInJsonByRootPath.validateResponseBodyElement(response, "", "entry.product.code",
				equalTo(expectedProductCode));
	}

	private void executeUiSteps(Map<String, String> sessionStorage) {
		WebDriverManager.setupDriver();
		HomePage homePage = new HomePage();
		BasketPage basketPage = new BasketPage();

		PageNavigation.openPage(WebDriverManager.getDriver(), "https://www.kruidvat.nl/");
		WebDriverManager.getDriver().manage().deleteAllCookies();
		WebDriverManager.getDriver().manage().addCookie(new Cookie("kvn-cart", sessionStorage.get("guid")));
		WebDriverManager.getDriver().navigate().refresh();
		homePage.clickOnBasketButton();
		basketPage.clickOnAcceptCookiesButton();
		basketPage.clickOnContinueButton();
		Assert.assertEquals("Product description does not coincide", basketPage.getTextOfProductDescription(),
				"Kruidvat Sensitive Handzeep Navulling");
	}
}
