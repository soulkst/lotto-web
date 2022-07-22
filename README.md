# Lotto Toy Web application

## Summary
- JDK 1.8, Spring-boot 2.6.x, embedded-tomcat
- jQuery 3.6.x, Bootstrap 4.5.x, requirejs 2.3.x
- Using grunt
- Dockerizing support by 'jib'

## Swagger (Spring-openapi)
- **[Application URL]**/docs/api/ui

## Docker Environment

| Variable                 | Default Value         | Description                                                                                                                          |
|--------------------------|-----------------------|--------------------------------------------------------------------------------------------------------------------------------------|
| SERVER_PORT              | 8080                  | Tomcat service port                                                                                                                  |
| CONTEXT_PATH             | lotto                 | Servlet context Path                                                                                                                 |
| ENABLE_API_DOCS          | false                 | Enable Swagger api Documents                                                                                                         |
| DATABASE_URL             | jdbc:h2:~/lotto       | Database JDBC URL                                                                                                                    |
| DATABASE_USERNAME        | sa                    | Database user name                                                                                                                   |
| DATABASE_PASSWORD        |                       | Database user password                                                                                                               |
| DATABASE_PLATFORM        | H2Dialect             | Hibernate Dialect(name only) [JavaDoc](https://docs.jboss.org/hibernate/orm/5.6/javadocs/org/hibernate/dialect/package-summary.html) |
| DATASOURCE_MAX_POOL_SIZE | 10                    | Database connection pool size                                                                                                        |
| LOGGING_ROOT_LEVEL       | warn                  | Application root logging level                                                                                                       |
| LOGGING_APP_LEVEL        | info                  | Application logging level                                                                                                            |
| ENCRYPT_KEY_FILE         | classpath:default.key | Encryption key file [About](https://www.baeldung.com/spring-boot-jasypt)                                                             |
