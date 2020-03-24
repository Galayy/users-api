# Users API

## Architecture

PostgreSql was chosen as the database. It's is an open source object-relational database system with over 30 years of 
active development that has earned it a strong reputation for reliability, feature robustness, and performance.
                                                                                  
Swagger was chosen for designing, building, documenting and consuming REST APIs.

Groovy was chosen for configuring Gradle. 

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

Project requires Java 11, Make, Docker, PostgreSql(optional if you launch database via docker).

### Installing

Instructions for Java: [AdoptOpenJDK installation](https://adoptopenjdk.net/installation.html?variant=openjdk11)

Instructions for Docker: [Docker installation](https://docs.docker.com/v17.12/install/)

Instructions for Make: [Install make on Windows](http://gnuwin32.sourceforge.net/packages/make.htm)

Instructions for PostgreSql: [Install PostgreSql](https://www.postgresql.org/download/)

### Running

Project requires running PostgreSql database. You can connect it via `docker-compose up` or by adding it as datasource to
Intellij Idea. To configure datasource follow next steps:
 1. In the **Database** tool window **(View | Tool Windows | Database)**, click the Data Source Properties icon.
 2. In the **Data Sources and Drivers** dialog select PostgreSQL.
 3. Specify database connection details. You can find them in *application.yaml*.
 4. To ensure that the connection to the data source is successful, click **Test Connection**.

Then the project should be started, with the following command:

`./gradlew bootRun` for MacOS or Linux and `gradlew bootRun` for Windows.

## Deploying

To deploy on Heroku run `make heroku-deploy`
