package org.mythogyan.intent;

import org.springframework.stereotype.Service;
import java.io.InputStream;
import java.util.*;
import org.yaml.snakeyaml.Yaml;

@Service
public class QueryTemplateService {
    private final Map<String, Template> templates = new HashMap<>();

    @SuppressWarnings("unchecked")
    public QueryTemplateService() {
        try (InputStream in = getClass().getResourceAsStream("/QUERY_TEMPLATE_LIBRARY.yml")) {
            if (in != null) {
                Yaml yaml = new Yaml();
                Map<String, Object> data = yaml.load(in);
                Map<String, Object> t = (Map<String, Object>) data.get("templates");
                for (Map.Entry<String, Object> e : t.entrySet()) {
                    Map<String, Object> val = (Map<String, Object>) e.getValue();
                    templates.put(e.getKey(), new Template((String) val.get("cypher"), (List<String>) val.get("params")));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Template getTemplate(Intent intent) { return templates.get(intent.name()); }
    public static record Template(String cypher, List<String> params) {}
}
