package com.stackroute.orderservice.restclient;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.stackroute.orderservice.entity.Product;

public class ProductsRestClient {
	
	private static final String GET_PRODUCT_DETAIL_ENDPOINT_URL = "http://localhost:8091/product";
	private static RestTemplate restTemplate = new RestTemplate();
	public static Product productDetail(String id)
	{
		ProductsRestClient springRestClient = new ProductsRestClient();
		return springRestClient.getProductById(id);
	}
	private Product getProductById(String id) {
        Map < String, String > params = new HashMap < String, String > ();
        params.put("id", id.toString());
        RestTemplate restTemplate = new RestTemplate();
        Product result = restTemplate.getForObject(GET_PRODUCT_DETAIL_ENDPOINT_URL, Product.class, params);
        return result;
    }
}
