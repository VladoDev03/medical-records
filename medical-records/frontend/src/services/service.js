import keycloak from '../config/keycloak'

const BASE_URL = 'http://localhost:8080/api/patients';

export const getPatients = async () => {
    const keycloakToken = keycloak.token;

    const response = await fetch(`${BASE_URL}`, {
        headers: {
            'Authorization': `Bearer ${keycloakToken}`
        },
    });

    if (response.ok) {
        return await response.json();
    } else {
        console.error('Failed to get roles', response.status, await response.text());
        throw new Error('Failed to get roles');
    }
};

export const createPatient = async () => {
    const keycloakToken = keycloak.token;

    const response = await fetch(`${BASE_URL}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${keycloakToken}`,
        }
    });

    if (response.ok) {
        return await response.json();
    } else {
        console.error('Failed to create user', response.status, await response.text());
    }
};
