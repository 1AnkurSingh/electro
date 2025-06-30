# Electro

## Database Configuration

The application reads database credentials from environment variables instead of hardcoded values. The `application.properties` file expects `DB_USER` and `DB_PASS` to be provided at runtime:

```
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASS}
```

Set these variables before starting the application. Example using a Unix shell:

```bash
export DB_USER=myuser
export DB_PASS=mypassword
./mvnw spring-boot:run
```

Alternatively, place the variables in a `.env` file or your deployment environment's configuration so they are available to the application process.
