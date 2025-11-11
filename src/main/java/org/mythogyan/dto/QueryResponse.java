package org.mythogyan.dto;

import java.util.List;
import java.util.Map;

public class QueryResponse {
    private String intent;
    private String resolvedEntity;
    private List<Map<String,Object>> results;
    private String message;

    public QueryResponse() {}
    public QueryResponse(String intent, String resolvedEntity, List<Map<String,Object>> results, String message) {
        this.intent = intent; this.resolvedEntity = resolvedEntity; this.results = results; this.message = message;
    }

    public String getIntent() { return intent; }
    public void setIntent(String intent) { this.intent = intent; }
    public String getResolvedEntity() { return resolvedEntity; }
    public void setResolvedEntity(String resolvedEntity) { this.resolvedEntity = resolvedEntity; }
    public List<Map<String,Object>> getResults() { return results; }
    public void setResults(List<Map<String,Object>> results) { this.results = results; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
