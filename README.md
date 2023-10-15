# Rent A Car
`Rent A Car` management system


## Technologies

* Spring Boot
* Spring Security
* Spring MVC
* Thymeleaf
* Bootstrap 4
* H2 in memory database
* JPA ORM (Hibernate impl.)

## Team members

- Tanvir Zobair Mahboob (615163)
- Md Siam Biswas (615195) 
- Md Atikur Rahman (615231)
- Md Shahab Uddin (615180)

## Installation

Simple installation from Github repository:

- Download or clone the project code from "https://github.com/miu-bd/rent-a-car"
- open cmd/terminal in the project folder
- execute the following command - "java -jar ./target/rent-a-car-0.0.1-SNAPSHOT.jar"
- open a web browser and visit - "http://localhost:8080/"


Install with Maven:

* If you received the project without JAR file then you need to follow this process 

- Make sure you have "Maven" installed in your system "https://maven.apache.org/index.html"
- open cmd/terminal in the project folder
- execute the following command - "mvn clean install"
- execute the following command - "java -jar ./target/rent-a-car-0.0.1-SNAPSHOT.jar"
- open a web browser and visit - "http://localhost:8080/"

## In memory users

To keep things simple, two users were created with Spring Security HTTP basic auth. _(username, password)_

* `ROLE_EMPLOYEE`: This user has (employee, employee) as credentials.
* `ROLE_ADMIN`: This user has (admin, admin) as credentials. 

