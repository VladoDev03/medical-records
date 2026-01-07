# medical-records

## Local Development
### Ports (Clients)
- Eureka Server - 8761
- Keycloak - 8081
- Zipkin - 9411
- PgAdmin - 5050
- PhpMyAdmin - 8082
- Mongo Express - 8083
### Ports (Services)
- PostgreSQL - 5432
- MySql - 3307
- MongoDb - 27018
### How to run the project
Docker containers
- Open the folder that contains the **docker-compose.yml** file in Visual Studio Code
- In the console, type in the following command:
```
docker-compose up --build -d
```
Microservice project
- Open the **backend** folder in IntelliJ IDEA
- In the **services** folder run the projects in the following order:
    1. config-server
    2. discovery-server
    3. api-gateway
    4. doctor-service
    5. patient-service
    6. visit-service
    7. prescription-service
### Setup
Log in to Keycloak
- Username or Email: admin
- Password: admin

Import keycloak realm
- Open the **realm-export.json** file
- Search for **authorizationSettings* and delete either the whole section or just the inner **policies** section.
- In the realm list press the dropdown on the top right side of the screen
- Click **Create realm**
- Drag and drop the **realm-export.json** file in the **Resource file** field
- In the **Realm name** enter the name of the realm - "medical-records" and click **Create**

Log in to PgAdmin
- Username or Email: admin@admin.com
- Password: admin

Connect to server - PgAdmin
- Click Add new connection
- name: keycloak
- Host name/address: keycloak-postgres
- Maintenance database: keycloak
- Username: keycloak
- password: keycloak
- Click save

Log in to MongoExpress
- admin
- pass