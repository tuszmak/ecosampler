# Install
## Deploy with docker compose
> ### Quick test ride: 
> With initial data you can try: ```docker-compose --project-name eco-sampler-test up```

> ### Proper Production Build:
> 1. Setup the proper environment variables 
>   - 'JWT_SECRET' 
>     - 512bit Hex Key
>   - 'DB_PASSWORD'
>     - Always use strong production password
>   - 'DB_USER'
>     - Always use non-standard username
> 1. Run: ``` docker-compose -f docker-compose-production.yml --project-name eco-sampler-production up -d```
>    - Make a coffee until the build is finish 
> 3. When the containers is running you need to initialize with the provided INIT-TABLES.sql

## Run In IntelliJ

> 1. Install Postgres
>   - Create new database: ecosampler
> 1. Setup the proper environment variables
>    - 'JWT_SECRET'
>      - 512bit Hex Key
>    - 'DB_PASSWORD'
>    - 'DB_USER'
>    - 'SPRING_PROFILES_ACTIVE=idea,dev'
> 1. Run the application


