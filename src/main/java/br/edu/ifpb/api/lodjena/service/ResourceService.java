package br.edu.ifpb.api.lodjena.service;

import java.util.List;

import br.edu.ifpb.api.lodjena.model.Resource;

/**
 * 
 * @author ronei
 *
 */
public interface ResourceService {

	List<Resource> findDbpediaByLabel(String lang, String label);
	List<Resource> findDbpediaByUrl(String url);
}
