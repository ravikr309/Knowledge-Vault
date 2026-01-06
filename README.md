# Knowledge-Vault
Spring Boot REST API for managing knowledge entries, designed with clear domain modeling, consistent APIs, and well-considered architectural tradeoffs.


## 🚀 Getting Started

### Prerequisites
* **JDK 21** (Required)
* **Maven 3.9+**

### Installation & Running
1. **Clone the repository:**
   ```bash
   git clone [https://github.com/your-username/knowledge-vault.git](https://github.com/your-username/knowledge-vault.git)
   cd knowledge-vault
Run the application:Bashmvn spring-boot:run
The API will be available at http://localhost:8080.Access Swagger UI at: http://localhost:8080/swagger-ui/index.html

Running Tests
Execute the test suite (including service logic and security checks)

🔐 Security & Users
The API uses HTTP Basic Authentication
Username	Password	Role
admin	admin123	ADMIN

Public Access: GET requests (Searching and viewing entries).
Secured Access: POST requests (Creating entries).

Example API Calls
1. Create a Link Entry (Secured)
Requires Authentication. Use the type field in the JSON body to specify the entry category.
curl -i -u admin:admin123 -X POST http://localhost:8080/api/v1/entries \
-H "Content-Type: application/json" \
-d '{
  "type": "LINK",
  "title": "Spring Security Docs",
  "url": "https://spring.io/projects/spring-security",
  "publishedAt": "2026-01-06T10:00:00"
}'

2. Search Entries (Public)No authentication required.Bashcurl -i -X GET curl -i -X GET "http://localhost:8080/api/v1/entries/querry?query=Spring"

## 🛠 Engineering Notes

**Polymorphic Modeling:**  
Implemented a Single Table Inheritance strategy for the `KnowledgeEntry` hierarchy. This optimizes query performance for searches across all entry types while maintaining a clean, extensible DTO structure via Jackson's `@JsonTypeInfo`.

**Polymorphic DTOs:**  
Utilized an interface-based DTO strategy combined with Java 21 pattern matching for `instanceof` in the service layer. This ensures controllers remain generic and clean, while providing a type-safe way to handle specific `Article` or `Link` logic.

**Deliberate Simplification:**  
For speed of delivery and ease of testing, an `InMemoryUserDetailsManager` was used. While not suitable for production, it allowed for an immediate demonstration of role-based access control and method-level security without the overhead of a full `User` entity management system.

**Future Improvements:**  
- Implement MapStruct to handle DTO-to-Entity mapping and reduce boilerplate code.  
- Transition to a PostgreSQL database with Flyway for robust, version-controlled database migrations.  
- Introduce **JWT-based authentication** for stateless and more secure user sessions.  
- Integrate **Apache Kafka** for asynchronous messaging and event-driven architecture.  
- Use **Redis** for caching frequently accessed data to improve performance and scalability.
