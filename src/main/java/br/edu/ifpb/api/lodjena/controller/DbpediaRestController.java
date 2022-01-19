package br.edu.ifpb.api.lodjena.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.api.lodjena.dto.ResourceDbpediaDTO;
import br.edu.ifpb.api.lodjena.mapper.ResourceDbpediaMapper;
import br.edu.ifpb.api.lodjena.service.ResourceDbpediaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 
 * @author Ronei Oliveira
 *
 */

@RestController
@RequestMapping("/dbpedia")
@Tag(name = "DBPedia", description = "Repositório")
public class DbpediaRestController {

	@Autowired
	private ResourceDbpediaService resourceService;
	
	@Operation(summary = "Buscar recurso", responses = {
			@ApiResponse(description = "Operação realizada com sucesso.", responseCode = "200", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "Não encontrado.", content = @Content)})
	@GetMapping("/buscar/{idioma}/{titulo}")
	public ResponseEntity<List<ResourceDbpediaDTO>> getResourcesByLabel(@PathVariable("idioma") String lang, @PathVariable("titulo") String label) {
		return ResponseEntity.ok().body(ResourceDbpediaMapper.INSTANCE.toDTO(resourceService.findDbpediaByLabel(lang, label)));
	}
	
	@Operation(summary = "Buscar recurso pela URL", responses = {
			@ApiResponse(description = "Operação realizada com sucesso.", responseCode = "200", content = @Content(mediaType = "application/json")),
			@ApiResponse(responseCode = "404", description = "Não encontrado.", content = @Content)})
	@PostMapping("/buscar")
	public ResponseEntity<List<ResourceDbpediaDTO>> getResourcesByURL(@RequestParam(name = "url") String url) {
		return ResponseEntity.ok().body(ResourceDbpediaMapper.INSTANCE.toDTO(resourceService.findDbpediaByUrl(url)));
	}
}