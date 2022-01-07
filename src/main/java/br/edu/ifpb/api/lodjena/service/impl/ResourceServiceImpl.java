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
import org.springframework.stereotype.Service;

import br.edu.ifpb.api.lodjena.model.Resource;
import br.edu.ifpb.api.lodjena.service.ResourceService;

/**
 * 
 * @author ronei
 *
 */
@Service
public class ResourceServiceImpl implements ResourceService {
	
	private String uriDbpedia = "https://dbpedia.org/sparql"; 
	
	@Override
	public List<Resource> findDbpediaByLabel(String lang, String label) {
		try {
			List<Resource> resources = new ArrayList<>();
			
			SelectBuilder builder = new SelectBuilder()
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
			
			//Imprime, no console, a consulta realizada 
			System.out.println(builder.buildString());

			Query query = builder.build(); 
			QueryExecution exec = QueryExecutionFactory.sparqlService(uriDbpedia, query);
			ResultSet results = exec.execSelect();
			
			while(results.hasNext()) {
				QuerySolution soln = results.nextSolution();
				
				//Imprime, no console, os valores encontrados
//				System.out.println(soln.getResource("resource"));
//				System.out.println(soln.getLiteral("label"));
//				System.out.println(soln.getLiteral("abstract"));
				
				Resource resource = new Resource();
				resource.setLabel(soln.getLiteral("label").toString());
				resource.setUri(soln.getResource("resource").toString());
				resource.setAbstractText(soln.getLiteral("abstract").toString());
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
	public List<Resource> findDbpediaByUrl(String url) {
		try {
			List<Resource> resources = new ArrayList<>();
			
			String lang = getLang(url);
			String label = getLabel(url);
			
			SelectBuilder builder = new SelectBuilder()
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
			
			//Imprime, no console, a consulta realizada 
			System.out.println(builder.buildString());

			Query query = builder.build(); 
			QueryExecution exec = QueryExecutionFactory.sparqlService(uriDbpedia, query);
			ResultSet results = exec.execSelect();
			
			while(results.hasNext()) {
				QuerySolution soln = results.nextSolution();
				
				//Imprime, no console, os valores encontrados
//				System.out.println(soln.getResource("resource"));
//				System.out.println(soln.getLiteral("label"));
//				System.out.println(soln.getLiteral("abstract"));
				
				Resource resource = new Resource();
				resource.setLabel(soln.getLiteral("label").toString());
				resource.setUri(soln.getResource("resource").toString());
				resource.setAbstractText(soln.getLiteral("abstract").toString());
				resources.add(resource);
			}
			
			return resources;
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Ocorreu um problema de execução da consulta.");
			return null;
		}		
	}

	private String getLang(String url) {
		return url.substring(url.indexOf("https://") + 8, url.indexOf("."));
	}

	private String getLabel(String url) {
		return url.substring(url.lastIndexOf('/') + 1).replaceAll("_", " ");
	}
	
}
