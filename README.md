# lagalt-back-end
## Introduction
This is the backend solution of a case project during the Noroff Accelerate course. The case project is called Lagalt and is a web application where people can find other people to cooperate on creative projects.
## Description
The backend part contains a database solution and an API. It is created using Java, Spring Framework, and Hibernate. The database is used to store projects and accounts and related information. The API is used to accsess, modify, and add data realting to projects or accounts. 
The github of the frontend is: https://github.com/SondreEMelhus/lagalt.
The following is the ER diagram of our database:
![ER](https://user-images.githubusercontent.com/48794099/197971066-55fcdbec-3d43-47ea-b6e5-8e13da952e3d.PNG)

## Deployment
We have deployed our back end api to Heroku. We use swagger to test our end points.
Here is a link to the swagger implementation of our API: 
https://lagalt-java-backend.herokuapp.com/swagger-ui/index.html

## Endpoints
Use the link above to see a complete list of all the endpoints of our back end api.
Most of our enteties in our database have their own controller in the api. 
Each controller has a list of endpoints for fetching and manipulating these enteties.
For instance project has GET endpoints for getting all projects, getting a project by its ID or getting a project by name. 
And GET endpoints for getting all the contributors, skills, keywords, industry of a project.
It also has a POST endpoints for adding a new project to the database. 
And it has PUT endpoints to update a project, add a skill to a project, remove a skill from a project or add a keyword to a project

## Installation
No installation is required to test our API on Heroku. 
If however you wish to run our back end api on your localhost:
1. You need to have inteliji, posgres and pgAdmin installed
2. Clone the spring boot project https://github.com/SondreEMelhus/lagalt-back-end
3. Open postgres, PostgreSQL 14 and create a new database called "lagalt"
4. Open the project in Inteliji, open the application.properties file. Set the database credentials to match you postgres database
5. Run the project in inteliji
6. Open http://localhost:8080/swagger-ui.html

## Authors
- Ulrik Lunde
- Karolie Øijorden
- Sondre Melhus
- Trygve Johannessen

## License
MIT
