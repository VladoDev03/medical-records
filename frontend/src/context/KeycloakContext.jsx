import React, { createContext, useContext, useEffect, useState } from 'react';
import keycloak from '../config/keycloak';

const AuthContext = createContext();

export const useAuth = () => useContext(AuthContext);

export const AuthProvider = ({ children }) => {
  const [keycloakReady, setKeycloakReady] = useState(false);
  const [authenticated, setAuthenticated] = useState(false);

  useEffect(() => {
    keycloak.init({ onLoad: 'login-required' }).then((auth) => {
      setAuthenticated(auth);
      setKeycloakReady(true);
    });
  }, []);

  return keycloakReady ? (
    <AuthContext.Provider value={{ keycloak, authenticated }}>
      {children}
    </AuthContext.Provider>
  ) : (
    <div>Loading...</div>
  );
};
