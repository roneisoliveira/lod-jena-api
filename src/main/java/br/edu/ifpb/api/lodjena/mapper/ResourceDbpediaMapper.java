package br.edu.ifpb.api.lodjena.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.edu.ifpb.api.lodjena.dto.ResourceDbpediaDTO;
import br.edu.ifpb.api.lodjena.model.ResourceDbpedia;

/**
 * 
 * @author Ronei Oliveira
 *
 */
@Mapper
public interface ResourceDbpediaMapper {
	
	public ResourceDbpediaMapper INSTANCE = Mappers.getMapper(ResourceDbpediaMapper.class);
	
	public List<ResourceDbpediaDTO> toDTO(List<ResourceDbpedia> resources);
}
