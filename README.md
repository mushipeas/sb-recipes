# Recipe Lookup Site (Spring Boot)

A Spring Boot application for finding recipes. Links to bbc-goodfood recipes.

The data is loaded from a postgres database, but this could be changed by replacing the Postgres datasource driver with another one.
Data has been pre-scraped with BS4 and requires pre-populating into the database. A backup of the data can be found under db-backup. 

A demo can be seen at:
https://sb-recipes.herokuapp.com/


## Recommended Setup for Development:

Open the project with vs-code and activate the dev-container.

Tests currently make use of Mockito and MockMVC modules.

## TODO:

- Add tests for Thymeleaf templates
- Full front-end testing using Selenium