# Roadmap

## Core Spring Framework

- [x] Dependency Injection
- [x] Spring IOC
- [x] Spring AOP
- [x] Spring Beans, Bean Life Cycle, and Bean Scope

## Spring Boot Fundamentals

- [x] Spring Boot Starters
- [-] Important Annotations
  - [x] (`@SpringBootApplication`
  - [x] `@Bean`
  - [x] `@Component`
  - [x] `@Service`
  - [x] `@Component vs @Service`
  - [x] `@Autowired`
  - [x] `@Configuration`
  - [ ] `@Transactional`
  - [ ] `@EnableTransactionManagement`
  - [x] `@MongoTransactionManager`
  - [x] `@Entity`
  - [x] `@Document`
  - [x] `@DBRef`
  - [x] `@Value`
  - [x] `@PostConstruct`
  - [x] `@Scheduled`
  - [x] `@EnableWebSecurity`)
- [-] Autoconfiguration
- [ ] Elegant Configuration Management
- [-] Spring Boot Actuator
- [-] Embedded Server
- [x] ApplicationContext
- [-] ORM in Spring Boot

## RESTful Services Development

- [-] REST APIs
- [x] (GET, POST, PUT, DELETE)
- [x] (`@RestController`, `@RequestBody`, `@PathVariable`, `@Mappings`)
- [x] ResponseEntity
- [x] Handling Path Variables and Request Parameters
- [-] (JPA
- [-] JDBC
- [-] MongoDB)
- [-] MongoTemplate
- [-] Criteria and Query

## Spring Security

- [-] Spring Security
- [-] (Authentication
- [-] Authorization
- [ ] OAuth2
- [ ] JWT Authentication)
- [x] WebSecurityConfigurerAdapter

## Microservices Architecture

- [ ] Microservices
- [ ] Microservices Design Patterns
- [ ] Spring Cloud Modules
- [ ] (Gateway
- [ ] Zuul Proxy
- [ ] Config Server
- [ ] Service Registry and Discovery
- [ ] Circuit Breaker
- [ ] Distributed Tracing
- [ ] OpenFeign
- [ ] Sleuth
- [ ] Eureka)
- [ ] Inter-Service Communication
- [ ] Fault Tolerance with Resilience4j
- [ ] RestTemplate

## Testing and DevOps

- [-] Testing Spring Boot Application (MOCKMVC), (`@SpringBootTest`, `@Test`, `@BeforeEach`, `@AfterEach`, `@BeforeAll`, `@AfterAll`, `@BeforeTestClass`, `@AfterTestClass`, `@BeforeTestMethod`, `@AfterTestMethod`, `@BeforeSuite`, `@AfterSuite`, `@ParameterizedTest`, `@Disabled`, `@Mock`, `@InjectMocks`)
- [-] DevOps (Profiles)
- [ ] Exception Handling (Using @ControllerAdvice and @ExceptionHandler
- [ ] Custom Error Responses
- [ ] Global Exception Handling)

## Hibernate, Persistence and Database Access

- [ ] Transactions
- [ ] Relationships
- [ ] Entity Lifecycle
- [ ] PlatformTransactionManager

## Additional Topics

- [x] DevTools
- [x] Lombok(`@Data`, `@Indexed`, `@NonNull`)
- [ ] Logging and Monitoring
- [ ] Spring Batch
- [-] Scheduling and cron expressions
- [-] Email Sending
- [-] Redis
- [-] Kafka


git msg = Author | Project Name | Category | Topic name
example = git commit -m "A-Suresh Sehrawat | Spring Boot | Spring Boot Fundamentals | Spring Security"