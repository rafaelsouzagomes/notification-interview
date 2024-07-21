# Notification Application

This is a Full Stack project designed to handle notifications. The backend is developed with Spring Boot, and the frontend is a React. This project utilizes RabbitMQ for queuing, leveraging concurrency, and following good design patterns, OOP principles and unit testing.

![image](https://github.com/user-attachments/assets/12972bf8-9a0b-4616-9ad5-5f1d4cc85a0c)

2. **Update the application properties**:

    Update the `src/main/resources/application.properties` file with the appropriate configuration for your local PostgreSQL and RabbitMQ instances. For example:

    ```properties
    spring.application.name=notification

    # database
    spring.datasource.url=jdbc:postgresql://localhost:5432/NotificationInterview
    spring.datasource.username=postgres
    spring.datasource.password=POSTGRE
    spring.datasource.driver-class-name=org.postgresql.Driver
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

    # queue
    spring.rabbitmq.host=localhost
    spring.rabbitmq.port=5672
    spring.rabbitmq.username=guest
    spring.rabbitmq.password=guest
    spring.rabbitmq.listener.simple.concurrency=5
    spring.rabbitmq.listener.simple.max-concurrency=10

    # flyway migrations
    spring.flyway.enabled=true
    spring.flyway.url=jdbc:postgresql://localhost:5432/NotificationInterview
    spring.flyway.user=postgres
    spring.flyway.password=POSTGRE
    spring.flyway.baseline-on-migrate=true
    spring.flyway.locations=classpath:db/migration
    spring.jpa.hibernate.ddl-auto=none
    ```
 ### Running with DOCKER
 1. **Build and run the Docker containers**:

    ```sh
    docker-compose up --build
    ```

### Running with Maven
1. **Start PostgreSQL and RabbitMQ**:

    Ensure that your local PostgreSQL and RabbitMQ services are running and configured to match the properties in `application.properties`.

2. **Run the application using Maven**:

    ```sh
    mvn spring-boot:run
    ```

The frontend application is a React project using Vite, located in the `client/messenger` directory.

1. **Navigate to the frontend directory**:

    ```sh
    cd client/messenger
    ```

2. **Install the dependencies**:

    ```sh
    npm install
    ```

3. **Run the frontend application**:

    ```sh
    vite
    ```


