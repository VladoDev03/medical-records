import Keycloak from "keycloak-js";

const keycloak = new Keycloak({
 url: "http://localhost:8081",
 realm: "medical-records",
 clientId: "medical-records-frontend",
});

export default keycloak;
