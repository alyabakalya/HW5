package Endpoints;

public class Endpoints {
	private static String createCartEndpoint = "/api/v2/kvn/users/anonymous/carts";
	private static String addProductToCartEndpoint = "/api/v2/kvn/users/anonymous/carts/{guid}/entries";

	public static String getCreateCartEndpoint() {
		return createCartEndpoint;
	}

	public static String getAddProductToCartEndpoint() {
		return addProductToCartEndpoint;
	}
}
