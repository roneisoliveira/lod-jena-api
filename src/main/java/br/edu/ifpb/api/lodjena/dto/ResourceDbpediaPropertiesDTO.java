package br.edu.ifpb.api.lodjena.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author Ronei Oliveira
 *
 */

@Schema(name = "ResourceProperties")
public class ResourceDbpediaPropertiesDTO {
	
	@JsonProperty(value = "propriedade")
	private String propertie;
	
	@JsonProperty(value = "valor")
	private String value;

	public String getPropertie() {
		return propertie;
	}

	public void setPropertie(String propertie) {
		this.propertie = propertie;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}	
}
