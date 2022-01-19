package br.edu.ifpb.api.lodjena.service;

import java.util.List;

import br.edu.ifpb.api.lodjena.model.ResourceDbpedia;

/**
 * 
 * @author Ronei Oliveira
 *
 */
public interface ResourceDbpediaService {

	List<ResourceDbpedia> findDbpediaByLabel(String lang, String label);
	List<ResourceDbpedia> findDbpediaByUrl(String url);
}
