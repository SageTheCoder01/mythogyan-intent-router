# Mythogyan Intent Router (Spring Boot)

This Spring Boot service maps natural-language queries to Cypher queries and executes them on Neo4j.

### 🚀 Run Locally
```bash
mvn spring-boot:run
Then open:

bash
Copy code
POST http://localhost:8080/api/v1/query
Body: { "text": "Who are the avatars of Vishnu?" }
Environment variables:

NEO4J_URI

NEO4J_USER

NEO4J_PASS


---