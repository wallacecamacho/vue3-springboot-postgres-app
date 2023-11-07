
# How it works


This application provides the following key features:

1. User registration/login/logout
2. Conta creation/viewing/editing/deletion
3. Conta list viewing
4. User profile viewing/editing

The project is implemented based on Java 17 and Spring Boot 3, utilizing various Spring technologies such as Spring MVC, Spring Data JPA, and Spring Security. It uses PostGreSQL as the database .

To run the project, JDK 17 must be installed first. Then, execute the ./gradlew bootRun command in the project root directory to run the application. After that, you can use the application by accessing http://localhost:8080 in your browser.

Taking a closer look at the project structure, the main code of the application is located in the src/main/java directory, while the test code is located in the src/test/java directory. Additionally, configuration files and such can be found in the
src/main/resources directory.

The core logic of the application is organized as follows:

- ~Controller: Processes HTTP requests, calls business logic, and generates responses.
- ~Service: Implements business logic and interacts with the database through Repositories.
- ~Repository: An interface for interacting with the database, implemented using Spring Data JPA.

Authentication and authorization management are implemented using Spring Security, with token-based authentication using JWT. Moreover, various features of Spring Boot are utilized to implement exception handling, logging, testing, and more.

Through this project, you can learn how to implement backend applications based on Spring and how to utilize various Spring technologies. Additionally, by implementing an application following the RealWorld specifications, it provides a basis for
deciding which technology stack to choose through comparisons with various other technology stacks.

# Database


# Getting started

> **Note:** You just need to have JDK 17 installed.
>
> **Note:** If permission denied occurs when running the gradle task, enter `chmod +x gradlew` to grant the permission.

## Run application

```shell
./gradlew bootRun
```


## Check code style

> **Note:** When you run the `build` task, this task runs automatically. If the code style doesn't match, the build will fail.

```shell
./gradlew spotlessCheck
```

## Apply code style

```shell
./gradlew spotlessApply
```

## Run build

```shell
./gradlew build
```
