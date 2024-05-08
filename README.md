# Purchase Saga

This project, named "Purchase Saga", is a Java-based application that uses the Quarkus framework. It is currently under development in the `saga-camel-quarkus` branch.

The application is designed to demonstrate the Saga design pattern, which is a sequence of local transactions where each transaction updates data within a single service. The Saga pattern is a way to manage data consistency across services in a microservices architecture.

The project uses Apache Camel's Saga EIP (Enterprise Integration Pattern) to manage the transactions. The Saga EIP allows you to define a series of related actions in a Camel route that should be either completed successfully (all of them) or not-executed or compensated in case of an error.

## Running the Application

```shell script
./mvnw compile quarkus:dev
```

The application can be packaged using the following command:

```shell script
./mvnw package
```

To create a native executable of the application, you can use:

```shell script
./mvnw package -Dnative
```

## Further Reading

- [Quarkus Guides](https://quarkus.io/guides/)
- [Apache Camel Saga EIP](https://camel.apache.org/camel-quarkus/latest/reference/extensions/saga.html)
- [Spring Boot](https://spring.io/projects/spring-boot)
