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
* With the usage of the `Bean Validation Framework`



# Authentication
## - Roles
There are three access types (user roles) in this application:
### Employee
By logging in as a user that has the role of `ROLE_EMPLOYEE` the user can issue new rentals. Also, the employee has the possibility to finish those. Employees can access all paths starting with `/employee/**`.

### Admin
Users with the role of `ROLE_ADMIN` have access to several REST endpoints. Admins can access all paths starting with `/admin/**`. Admins cannot visit employee roles and vice versa employees do not have access to the REST endpoints.

### Public
Anyone can call the root website. The user can see a form in which he can select a city. After submitting the form, the server will return a new website where all free cars in the selected city are displayed in a pretty way.

Public users do not have a role defined in the code. They have access to all paths different from the mentioned ones. Those would be `/**`.

## - In memory users
To keep things simple, two users were created with Spring Security HTTP basic auth. _(username, password)_
* `ROLE_EMPLOYEE`: This user has (employee, employee) as credentials.
* `ROLE_ADMIN`: This user has (admin, admin) as credentials. 

### Redirection
If unauthenticated users try to access paths of a specific role, which they are not part of, they will be redirected to the login screen.
