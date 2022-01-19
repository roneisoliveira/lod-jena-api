package br.edu.ifpb.api.lodjena.model;

import java.util.List;

/**
 * 
 * @author Ronei Oliveira
 *
 */

public class ResourceDbpedia {
	private String label;
	private String abstractText;
	private String uri;
	
	private List<ResourceDbpediaPropertie> properties;

	public ResourceDbpedia() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResourceDbpedia(String label, String abstractText, String uri, List<ResourceDbpediaPropertie> properties) {
		super();
		this.label = label;
		this.abstractText = abstractText;
		this.uri = uri;
		this.properties = properties;
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

	public List<ResourceDbpediaPropertie> getProperties() {
		return properties;
	}

	public void setProperties(List<ResourceDbpediaPropertie> properties) {
		this.properties = properties;
	}
}
