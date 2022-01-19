package br.edu.ifpb.api.lodjena.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author ronei
 *
 */

@Schema(name = "Resource")
public class ResourceDbpediaDTO {
	
	@JsonProperty(value = "titulo")
	private String label;
	
	@JsonProperty(value = "descricao")
	private String abstractText;
	
	@JsonProperty(value = "uri")
	private String uri;
	
	@JsonProperty(value = "propriedades")
	private List<ResourceDbpediaPropertiesDTO> properties;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getAbstractText() {
		return abstractText;
	}

	public void setAbstractText(String abstractText) {
		this.abstractText = abstractText;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public List<ResourceDbpediaPropertiesDTO> getProperties() {
		return properties;
	}

	public void setProperties(List<ResourceDbpediaPropertiesDTO> properties) {
		this.properties = properties;
	}
	
	
}
