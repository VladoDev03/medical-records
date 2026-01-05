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
### Setup
Log in to Keycloak
- Username or Email: admin
- Password: admin

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