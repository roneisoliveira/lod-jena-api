package br.edu.ifpb.api.lodjena.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.edu.ifpb.api.lodjena.dto.ResourceDTO;
import br.edu.ifpb.api.lodjena.model.Resource;

@Mapper
public interface ResourceMapper {
	
	public ResourceMapper INSTANCE = Mappers.getMapper(ResourceMapper.class);
	
	public List<ResourceDTO> toDTO(List<Resource> resources);
}
