package br.edu.ifpb.api.lodjena.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.jena.arq.querybuilder.SelectBuilder;
import org.apache.jena.arq.querybuilder.WhereBuilder;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.shared.PrefixMapping;
import org.apache.jena.sparql.lang.sparql_11.ParseException;
import org.springframework.stereotype.Service;

import br.edu.ifpb.api.lodjena.model.ResourceDbpedia;
import br.edu.ifpb.api.lodjena.model.ResourceDbpediaPropertie;
import br.edu.ifpb.api.lodjena.service.ResourceDbpediaService;

/**
 * 
 * @author Ronei Oliveira
 *
 */
@Service
public class ResourceDbpediaServiceImpl implements ResourceDbpediaService {
	
	private String uriDbpedia = "https://dbpedia.org/sparql"; 
	
	@Override
	public List<ResourceDbpedia> findDbpediaByLabel(String lang, String label) {
		try {
			
			//Cria uma lista de recursos
			List<ResourceDbpedia> resources = new ArrayList<>();
			
			//Captura e executa a Query de consulta sparql
			SelectBuilder builder = getSelectLabelBuilder(lang, label);
			Query query = builder.build(); 
			QueryExecution exec = QueryExecutionFactory.sparqlService(uriDbpedia, query);
			ResultSet results = exec.execSelect();
			
			//Imprime, no console, a consulta realizada 
			System.out.println(builder.buildString());
			
			//Faz a iteração com os resultados da consulta
			while(results.hasNext()) {
				QuerySolution soln = results.nextSolution();
				
				//Recupera os valores retornados pela consulta
				ResourceDbpedia resource = new ResourceDbpedia();
				resource.setLabel(soln.getLiteral("label").toString());
				resource.setUri(soln.getResource("resource").toString());
				resource.setAbstractText(soln.getLiteral("abstract").toString());
				
				//Cria uma lista de propriedades do recurso
				List<ResourceDbpediaPropertie> properties = new ArrayList<>();
				
				//Busca as propriedades do recurso
				SelectBuilder builderProperties = getSelectPropertiesBuilder(resource.getUri());
				Query queryProperties = builderProperties.build(); 
				QueryExecution execProperties = QueryExecutionFactory.sparqlService(uriDbpedia, queryProperties);
				ResultSet resultsProperties = execProperties.execSelect();
				
				//Imprime, no console, a consulta realizada 
				System.out.println(builderProperties.buildString());
				
				while(resultsProperties.hasNext()) {
					QuerySolution solnProperties = resultsProperties.nextSolution();
					
					//Recupera os valores retornados pela consulta
					ResourceDbpediaPropertie propertie = new ResourceDbpediaPropertie();
					propertie.setPropertie(solnProperties.get("property").toString());
					propertie.setValue(solnProperties.get("hasValue").toString());
					
					properties.add(propertie);
					resource.setProperties(properties);
				}
				
				resources.add(resource);
			}
			
			return resources;
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Ocorreu um problema de execução da consulta.");
			return null;
		}		
	}
	
	@Override
	public List<ResourceDbpedia> findDbpediaByUrl(String url) {
		try {
			List<ResourceDbpedia> resources = new ArrayList<>();
			
			String lang = getLang(url);
			String label = getLabel(url);
			
			SelectBuilder builder = getSelectLabelBuilder(lang, label);
			Query query = builder.build(); 
			QueryExecution exec = QueryExecutionFactory.sparqlService(uriDbpedia, query);
			ResultSet results = exec.execSelect();
			
			//Imprime, no console, a consulta realizada 
			System.out.println(builder.buildString());
			
			while(results.hasNext()) {
				QuerySolution soln = results.nextSolution();
				
				//Recupera os valores retornados pela consulta
				ResourceDbpedia resource = new ResourceDbpedia();
				resource.setLabel(soln.getLiteral("label").toString());
				resource.setUri(soln.getResource("resource").toString());
				resource.setAbstractText(soln.getLiteral("abstract").toString());
				
				//Cria uma lista de propriedades do recurso
				List<ResourceDbpediaPropertie> properties = new ArrayList<>();
				
				//Busca as propriedades do recurso
				SelectBuilder builderProperties = getSelectPropertiesBuilder(resource.getUri());
				Query queryProperties = builderProperties.build(); 
				QueryExecution execProperties = QueryExecutionFactory.sparqlService(uriDbpedia, queryProperties);
				ResultSet resultsProperties = execProperties.execSelect();
				
				//Imprime, no console, a consulta realizada 
				System.out.println(builderProperties.buildString());
				
				while(resultsProperties.hasNext()) {
					QuerySolution solnProperties = resultsProperties.nextSolution();
					
					//Recupera os valores retornados pela consulta
					ResourceDbpediaPropertie propertie = new ResourceDbpediaPropertie();
					propertie.setPropertie(solnProperties.get("property").toString());
					propertie.setValue(solnProperties.get("hasValue").toString());
					
					properties.add(propertie);
					resource.setProperties(properties);
				}
				
				resources.add(resource);
			}
			
			return resources;
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Ocorreu um problema de execução da consulta: " + e.getMessage());
			return null;
		}		
	}

	private String getLang(String url) {
		return url.substring(url.indexOf("https://") + 8, url.indexOf("."));
	}

	private String getLabel(String url) {
		return url.substring(url.lastIndexOf('/') + 1).replaceAll("_", " ");
	}
	
	private SelectBuilder getSelectLabelBuilder(String lang, String label) throws ParseException {
		return new SelectBuilder()
				.addPrefixes( PrefixMapping.Standard )
				.addPrefix( "dbo", "http://dbpedia.org/ontology/" )
				.setDistinct(true)
				.addVar( "?resource")
				.addVar( "?abstract" )
				.addVar( "?label" )
				.addWhere( new WhereBuilder()
						.addPrefix( "rdfs",  "http://www.w3.org/2000/01/rdf-schema#" )
						.addPrefix( "dbo", "http://dbpedia.org/ontology/")
						.addWhere( "?resource", "rdfs:label", "'"+label+"'@"+lang )
						.addWhere( "?resource", "rdfs:label", "?label" )
						.addWhere( "?resource", "dbo:abstract", "?abstract" )
						.addFilter("lang(?label) = '"+lang+"'")
						.addFilter("lang(?abstract) = '"+lang+"'")
				);
	}
	
	private SelectBuilder getSelectPropertiesBuilder(String uri) throws ParseException {
		return new SelectBuilder()
				.addVar( "?property")
				.addVar( "?hasValue" )
				.addWhere( new WhereBuilder()
						.addWhere( "<" + uri + ">", "?property ", "?hasValue " )
				);
	}
	
}
