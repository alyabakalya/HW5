package utils;

import org.assertj.core.api.Assertions;
import org.hamcrest.Matcher;

import io.restassured.response.Response;


public class FindElementInJsonByRootPath {
	public static void validateResponseBodyElement(Response response, String rootPath, String pathToElement, Matcher<?> matcher) {
		Assertions.assertThat(response.then().rootPath(rootPath).body(pathToElement, matcher)).as(
				"Body element is wrong or not found");
	}
}

