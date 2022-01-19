package br.edu.ifpb.api.lodjena.model;

/**
 * 
 * @author Ronei Oliveira
 *
 */
public class ResourceDbpediaPropertie {
	
	private String propertie;
	private String value;
	
	public ResourceDbpediaPropertie(String propertie, String value) {
		super();
		this.propertie = propertie;
		this.value = value;
	}

	public ResourceDbpediaPropertie() {
		super();
		// TODO Auto-generated constructor stub
	}

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

	@Override
	public String toString() {
		return "ResourceDbpediaPropertie [propertie=" + propertie + ", value=" + value + "]";
	}
}
