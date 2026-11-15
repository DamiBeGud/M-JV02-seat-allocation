# seat-allocation

Spring Boot4.0.3/Maven, Java17–25. `mvn -B test`, `mvn -B package` (includes tests),
`java -jar target/seat-allocation-0.1.0.jar`. PORT defaults to8302, listener0.0.0.0.
POST /allocations {id,seats}; POST /allocations/:id/release; GET /status. Twelve in-memory contiguous seats; duplicate/exhaustion conflicts409, missing/repeated release404.
No external state; process replacement loses in-memory allocations where applicable.
Liveness /health/live. 
Native tests include actual ephemeral loopback HTTP plus domain boundary checks.
AI-assisted researcher-created source, common scaffold disclosed separately. No deployment files or runtime credential values.
