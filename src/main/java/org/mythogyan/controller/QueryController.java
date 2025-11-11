package org.mythogyan.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.mythogyan.dto.*;
import org.mythogyan.intent.*;
import org.mythogyan.neo4j.Neo4jService;
import java.util.*;

@RestController
@RequestMapping("/api/v1/query")
public class QueryController {
    @Autowired IntentClassifier classifier;
    @Autowired QueryTemplateService templateService;
    @Autowired Neo4jService neo4jService;

    @PostMapping
    public QueryResponse handle(@RequestBody QueryRequest req) {
        String text = req.getText();
        Intent intent = classifier.classify(text, req.getLang());
        QueryTemplateService.Template tpl = templateService.getTemplate(intent);

        if (tpl == null)
            return new QueryResponse(Intent.UNKNOWN.name(), null, Collections.emptyList(), "Unknown intent.");

        String name = extractNameSimple(text);
        Map<String,Object> params = new HashMap<>();
        for (String p : tpl.params()) params.put(p, name);

        List<Map<String,Object>> results = neo4jService.runQuery(tpl.cypher(), params);
        return new QueryResponse(intent.name(), name, results, results.isEmpty() ? "No results." : "Found results.");
    }

    private String extractNameSimple(String text) {
        if (text == null || text.isBlank()) return "";
        String cleaned = text.replaceAll("[^\\p{L}\\p{N}\\s]", " ").trim();
        String[] toks = cleaned.split("\\s+");
        return toks[toks.length-1];
    }
}
