package br.edu.ifpb.api.lodjena.model;

/**
 * 
 * @author ronei
 *
 */

public class Resource {
	private String label;
	private String abstractText;
	private String uri;
	
	public Resource() {
		
	}

	public Resource(String label, String abstractText, String uri) {
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

	@Override
	public String toString() {
		return "Resource [label=" + label + ", abstractText=" + abstractText + ", uri=" + uri + "]";
	}
}
