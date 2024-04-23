# Products Rest API
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white) ![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=Spring&logoColor=white)  ![H2 Database](https://img.shields.io/badge/H2%20Database-018bff?style=for-the-badge&logoColor=white) ![Swagger](https://img.shields.io/badge/Swagger-6DB33F?style=for-the-badge&logo=swagger&logoColor=white)

I developed a Rest API to manage products, customers and their invoices in a store, built by using **Spring Boot and Java**, providing CRUD (Create, Read, Update, Delete) operations.
This API allows to store product information, such as name, product number, price, country, product section etc. 
Additionally, it supports the storage of customer information, including full name, address, city etc and finally it also supports the storage of invoice information (payment Method, payment Date and Purchase Total Price).

I used some libraries for this Rest API such **Spring Web, Spring Data JPA, Validation, H2 Database and SpringDoc OpenAPI Starter WebMVC UI » 2.3.0 (for the API documentation with Swagger)**.

## Database Config
For test this API, an external Database is not necessary because an embedded Database (H2 Database) was used with the following configuration properties:

- Name: products_db
- Username: sa
- Password:

## Development Tools
This Rest API was built with:

- Spring Boot version: 3.2.2
- Java version: 17

## System Class Diagram

![ProductsClassDiagram](https://github.com/MarcosTulioSDLV/Products/assets/41268178/efd0c972-f031-418b-8763-7d9f209683cf)
