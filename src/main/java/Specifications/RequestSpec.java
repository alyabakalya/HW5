package Specifications;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;


public class RequestSpec {

		public static RequestSpecification requestSpecification = new RequestSpecBuilder()
				.setBaseUri("https://www.kruidvat.nl")
				.setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON)
				.build();
	}

