package br.edu.ifpb.api.lodjena.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author ronei
 *
 */

@Schema(name = "Resource")
public class ResourceDTO {
	private String label;
	
	@JsonProperty(value = "abstract")
	private String abstractText;
	
	private String uri;
	
	public ResourceDTO() {
		
	}

	public ResourceDTO(String label, String abstractText, String uri) {
		super();
		this.label = label;
		this.abstractText = abstractText;
		this.uri = uri;
	}

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
}
