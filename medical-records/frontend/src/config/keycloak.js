import Keycloak from "keycloak-js";

const keycloak = new Keycloak({
 url: "http://localhost:8080",
 realm: "medical-records",
 clientId: "medical-records-react",
});

export default keycloak;
