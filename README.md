# Bills - simple system for personal bills.

## This is my first simple Spring Boot application. ##

This application is my main project that allows to track expenses and bills. For this moment it has features as:

* Creating a bill
* Modifying a bill
* Deleting one or all bills
* Reverting deleted bills

Project concept is to use Spring Boot with MVC.

For generating view Thymeleaf is used.

Sample application is hosted on [HEROKU](https://personalbills.herokuapp.com).

### Tech used: ###

* Spring Boot
* Spring Data JPA with Hibernate
* SQLite
* Thymeleaf
* Bootstrap
* jQuery DataTables

### Still todo : ###

* Spring Security - User access with user rights
* User registeration
* Import and export for reports
* Better validation of form fields
* Testing

### Future plans : ###

* Rest api with frontend in Angular
* SPA
* Internationalization
* No DB reset after application awake
* Responsive page for mobile phones
* Alerts on e-mail for upcoming bills
* Integration with calendar (google or outlook)