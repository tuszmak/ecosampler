# About the project

Ecosampler is a full-stack surveying tool, intended for scientists and institutions.
You can create new projects, with forms, that includes questions. 
The general user can fill out the forms and gather information in a database.

# Technologies used

> - **React-js 18.2.0** for frontend: It provides a responsive option, that is very popular nowadays
> - **AntDesign 5.4.6**: A React framework with bulit-in components. 
> It has components that the team really likes and suits the theme of the program
> - **Java Spring 3.1.0** for backend: A popular choice among developers in both commercial and personal use.
> It also has a lot of history, so there are already answered questions for it.
> - **PostgreSQL 15.2** for the database: Our dataflow revolves around connections so a relational database seems to fit the case.
> We also have the most experience with PostgreSQL, and Java Spring has an adapter for it as well.

# Install
## Deploy with docker compose
> ### Quick test ride: 
> With a set initial data you can use this code in the terminal: ```docker-compose --project-name eco-sampler-test up```

> ### Proper Production Build:
> 1. Setup the proper environment variables 
>   - 'JWT_SECRET' 
>     - 512bit Hex Key
>   - 'DB_PASSWORD'
>     - Always use strong production password
>   - 'DB_USER'
>     - Always use non-standard username
> 2. Run: ``` docker-compose -f docker-compose-production.yml --project-name eco-sampler-production up -d```
>    - Make a coffee until the build is finished 
> 3. When the containers are running you need to initialize with the provided INIT-TABLES.sql

## Run In IntelliJ

> 1. Install Postgres
>   - Create new database: ecosampler
> 2. Setup the proper environment variables
>    - 'JWT_SECRET'
>      - 512bit Hex Key
>    - 'DB_PASSWORD'
>      - 'DB_USER'
>    - 'SPRING_PROFILES_ACTIVE=idea,dev'
> 3. Run the application. The starter class is called EcoSamplerApp
> Optional: If you want to access the default emails, search for the DBInitializer.


# Usage

> 1. Visit the website.
> 2. Log in

## Director

> The director is the head of the institute. Has all permissions
> The credentials for the director are:
> - Email: director@codecool.com
> - Password: director
> 
> ### How to add a new user?
> 1. Click on the register button on the hotbar.
> 2. Provide the information.
> 3. Done!
> 
> ### How to create a project?
> 1. Click on the projects tab on the hotbar.
> 2. Click on the small "+" sign in the bottom right corner.
> 3. Fill in the Project name, description and assign project leader(s) to it.
> 4. Click submit.

## Project Leader
> 
> The project leader manages the project. The director also has these permissions.
> 
> ### How to create a form?
> 1. Click on the project you want to add the form to from the project list.
> 2. Click on the small "+" sign in the bottom right corner.
> 3. Fill in the form name.
> 4. You can add a new question with the "add field" button
> 5. Type in the question description and select the question types.
> 6. When you're done, press submit.
> 
> ### How to assign a user to a project?
> 1. Visit the project list.
> 2. Find the project you want to assign the user to, and click the human portrait button.
> 3. Assign people in the menu.

## Scientist
> 
> The scientist is the one who fills the forms.
> 
> ### How to fill a form?
> 1. Select the form you want to fill from the project.
> 2. Press submit.
> 