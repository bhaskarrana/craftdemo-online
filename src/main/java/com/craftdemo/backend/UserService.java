package com.craftdemo.backend;

import com.craftdemo.executor.CypherExecutor;
import com.craftdemo.executor.BoltCypherExecutor;
import org.neo4j.helpers.collection.Iterators;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import static org.neo4j.helpers.collection.MapUtil.map;

public class UserService {

    private final CypherExecutor cypher;

    public UserService(String uri) {
        cypher = createCypherExecutor(uri);
    }

    private CypherExecutor createCypherExecutor(String uri) {
        try {
            String auth = new URL(uri.replace("bolt","http")).getUserInfo();
            if (auth != null) {
                String[] parts = auth.split(":");
                return new BoltCypherExecutor(uri,parts[0],parts[1]);
            }
            return new BoltCypherExecutor(uri);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Invalid Neo4j-ServerURL " + uri);
        }
    }

    
    @SuppressWarnings("unchecked")
    public Iterable<Map<String,Object>> search(String query) {
        if (query==null || query.trim().isEmpty())
        	return Collections.emptyList();
        
        return Iterators.asCollection(cypher.query(
                "MATCH (user:Users)-[:HAS_PRODUCT]->(product:Product)\n" +
                " WHERE user.fullName  CONTAINS {part} \n" +
                "MATCH (user)-[:HAS_CUSTOMER]->(customer:Customer)"+
                "MATCH(otheruser:Users)-[:HAS_PRODUCT]->(product)"+
                " RETURN user,  product, customer, otheruser",
                map("part", query)));
    }

}
