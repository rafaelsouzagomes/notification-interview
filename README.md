# Notification Application

This is a Spring Boot application designed to handle notifications. The application is containerized using Docker and can be run both in Docker and directly using Maven.

![image](https://github.com/user-attachments/assets/12972bf8-9a0b-4616-9ad5-5f1d4cc85a0c)

 **Build and run the Docker containers**:

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

