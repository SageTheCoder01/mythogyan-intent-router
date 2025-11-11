package org.mythogyan.neo4j;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.core.Neo4jClient;
import java.util.*;

@Service
public class Neo4jService {
    private final Neo4jClient neo4jClient;

    @Autowired
    public Neo4jService(Neo4jClient neo4jClient) { this.neo4jClient = neo4jClient; }

    public List<Map<String, Object>> runQuery(String cypher, Map<String, Object> params) {
        return (List<Map<String, Object>>) neo4jClient.query(cypher).bindAll(params).fetch().all();
    }

}
