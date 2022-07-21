# Lotto Toy Web application

## Swagger
- [Application URL]/docs/api/ui

## Docker Environment

| Variable                 | Default Value   | Description                    |
|--------------------------|-----------------|--------------------------------|
| SERVER_PORT              | 8080            | Tomcat service port            |
| CONTEXT_PATH             | lotto           | Servlet context Path           |
| ENABLE_API_DOCS          | false           | Enable Swagger api Documents   |
| DATABASE_URL             | jdbc:h2:~/lotto | Database JDBC URL              |
| DATABASE_USERNAME        | sa              | Database user name             |
| DATABASE_PASSWORD        |                 | Database user password         |
| DATABASE_PLATFORM        | lotto           | Hibernate Dialect Name         |
| DATASOURCE_MAX_POOL_SIZE | 10              | Database connection pool size  |
| LOGGING_ROOT_LEVEL       | warn            | Application root logging level |
| LOGGING_APP_LEVEL        | info            | Application logging level      |
