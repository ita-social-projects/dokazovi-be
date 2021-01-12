# Dokazovo
![Build Status](https://github.com/ita-social-projects/dokazovi-be/workflows/Build%20&%20Test/badge.svg)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=ita-social-projects_dokazovi-be&metric=coverage&)](https://sonarcloud.io/dashboard?id=ita-social-projects_dokazovi-be)
[![Github Issues](https://img.shields.io/github/issues/ita-social-projects/dokazovi-be)](https://github.com/ita-social-projects/dokazovi-be/issues)
[![Pending Pull-Requests](https://img.shields.io/github/issues-pr/ita-social-projects/dokazovi-be)](https://github.com/ita-social-projects/dokazovi-be/pulls)
[![License](http://img.shields.io/:license-mit-blue.svg)](https://github.com/ita-social-projects/dokazovi-be/blob/develop/LICENSE)

The "Dokazovo" project is a platform where proven experts will provide only verified information about COVID-19 disease in Ukraine and other scientific and medical topics. The main aim of the "Dokazovo" project is to prevent the spread of infodemic. 
А user will be able to learn and use only verified and reliable medical information, advice and recommendations. It will be a platform for verified speakers and their recommendations.


## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Running the application locally

Required software:

* Java 11
* PostgreSQL (9.5.9 or higher)
* IntelliJ IDEA

Clone this repository to your local machine using:

```shell
git clone https://github.com/ita-social-projects/dokazovi-be
```
Create a database and set values in environment variables:

<img src="https://i.imgur.com/SUspHsj.png" alt="environment" width="500"/>

Run application and open [Swagger](http://localhost:8080/api/swagger-ui/)

## Running the tests

Run all tests for this system

```
./gradlew test
```

### Check code coverage

Run:

```
./gradlew jacocoTestReport
```

Open the report file: /build/reports/jacoco/test/index.html


### Coding style tests

Check code style for the application:

```
./gradlew checkstyleMain
```

Check code style for tests:

```
./gradlew checkstyleTest
```

## Deployed Apps and Additional Links

[RC Java](https://dokazovi-be.herokuapp.com/api/swagger-ui/) - the latest version from the release branch is automatically deployed to Heroku

[RC React](https://dokazovi-fe.herokuapp.com/) - release candidate from react team

[React team repository](https://github.com/ita-social-projects/dokazovi-fe)

## Built With

* [Spring](https://spring.io/) - The framework used
* [Gradle](https://gradle.org/) - Build tool
* [SpringFox](http://springfox.github.io/springfox/) - Automating the generation of specifications for JSON APIs
* [MapStruct](https://mapstruct.org/) - Java Bean mapper
* [FlyWay](https://flywaydb.org/) - Migration tool

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/ita-social-projects/dokazovi-be/tags). 


## License

This project is licensed under the MIT License - see the [LICENSE](https://github.com/ita-social-projects/dokazovi-be/blob/develop/LICENSE) file for details
