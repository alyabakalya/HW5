package DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Cart {

	private String guid;

	public String getGuid() {
		return guid;
	}

}


