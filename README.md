<a href="https://softserve.academy/"><img src="https://s.057.ua/section/newsInternalIcon/upload/images/news/icon/000/050/792/vnutr_5ce4f980ef15f.jpg" title="SoftServe IT Academy" alt="SoftServe IT Academy"></a>

# Dokazovo

![Build Status](https://github.com/ita-social-projects/dokazovi-be/workflows/Build%20&%20Test/badge.svg)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=ita-social-projects_dokazovi-be&metric=coverage&)](https://sonarcloud.io/dashboard?id=ita-social-projects_dokazovi-be)
[![Github Issues](https://img.shields.io/github/issues/ita-social-projects/dokazovi-be)](https://github.com/ita-social-projects/dokazovi-be/issues)
[![Pending Pull-Requests](https://img.shields.io/github/issues-pr/ita-social-projects/dokazovi-be)](https://github.com/ita-social-projects/dokazovi-be/pulls)
[![License](http://img.shields.io/:license-mit-blue.svg)](http://badges.mit-license.org)

## About the project

The "Dokazovo" project is a platform where proven experts will provide only evident information about coronavirus disease in Ukraine and other scientific and medical topics and prevent the spread of infodemia.
So there will be only verified information on the platform, only verified speakers and their recommendations.

## Start the project locally

### Required to install

* Java 11
* PostgreSQL (9.5.9 or higher)

### How to run

#### 1. Clone this repo to your local machine using:

```shell
git clone https://github.com/ita-social-projects/dokazovi-be
```

#### 2. Create a database: 

db name:`dokazovi` user: `dokazovi` password: `dokazovi`.

(Optional) You can enter other values in environmental variables:

```properties
spring.datasource.url=${DATASOURCE_URL}
spring.datasource.username=${DATASOURCE_USER}
spring.datasource.password=${DATASOURCE_PASSWORD}
```

#### 3. Start the app with IDE

If everything is ok you can run the [Swagger](#How-to-work-with-swagger-UI)

## How to work with swagger UI

1. [Run](#How-to-run) Dokazovo project.
2. Open this url: http://localhost:8080/api/swagger-ui/

## Documentation
* [Branching Strategy](https://github.com/ita-social-projects/dokazovi-be/wiki/Branching-Strategy)
* [Database](https://github.com/ita-social-projects/dokazovi-be/wiki/Database)
* [Setup CheckStyle to your IDE](https://github.com/ita-social-projects/dokazovi-be/wiki/Setup-CheckStyle-to-your-IDE)
* [Code Quality and Coverage](https://github.com/ita-social-projects/dokazovi-be/wiki/Code-Quality-and-Coverage)
* [Unit Testing](https://github.com/ita-social-projects/dokazovi-be/wiki/Unit-Testing)
---

## License

[![License](http://img.shields.io/:license-mit-blue.svg?style=flat-square)](http://badges.mit-license.org)

- **[MIT license](http://opensource.org/licenses/mit-license.php)**
- Copyright 2020 © <a href="https://softserve.academy/" target="_blank"> SoftServe IT Academy</a>.