# medical-records

## Local Development
### Ports (Clients)
- React - 5173
- Eureka Server - 8761
- Keycloak - 8081
- PgAdmin - 5050
- Zipkin - 9411
- PhpMyAdmin - 8082
- Mongo Express - 8083
- Redis Insight - 5540
- Kafka UI - 8085
- ZooNavigator - 9090
### Ports (Services)
- Configuration Server - 8888
- API Gateway - 8080
- PostgreSQL - 5432
- MySql - 3307
- MongoDb - 27017
- Redis - 6379
- Kafka - 9092
- Zookeeper - 2181
### Setup
Log in to Keycloak
- Username or Email: admin
- Password: admin

Log in to PgAdmin
- Username or Email: admin@admin.com
- Password: admin

Connect to server
- Click Add new connection
- name: kafka
- Host name/address: keycloak-postgres
- Maintenance database: keycloak
- Username: keycloak
- password: keycloak
- Click save

Log in to MongoExpress
- admin
- pass

Log in to ZooNavigator
- Connection string: zookeeper:2181