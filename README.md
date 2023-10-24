# Recipe Lookup Site (Spring Boot)
![build](https://github.com/mushipeas/sb-recipes/actions/workflows/maven.yml/badge.svg)

A Spring Boot application for finding recipes. Links to bbc-goodfood recipes.

The data is loaded from a postgres database, but this could be changed by replacing the Postgres datasource driver with another one.
Data has been pre-scraped with BS4 and requires pre-populating into the database. A backup of the data can be found under db-backup.

## Recommended Setup for Development:

Open the project with vs-code and activate the dev-container.

Tests currently make use of Mockito and MockMVC modules.

## TODO:

- Update to SB3
- Add tests for Thymeleaf templates
- Add testContainers and sample data set for testing
