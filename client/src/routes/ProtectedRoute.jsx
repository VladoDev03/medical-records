import React from 'react';
import { Navigate } from 'react-router-dom';
import { useAuth } from '../context/KeycloakContext';

const ProtectedRoute = ({ children, roles = [] }) => {
  const { keycloak, authenticated } = useAuth();

  if (!authenticated) return <Navigate to="/" />;

  const userRoles = keycloak.tokenParsed?.realm_access?.roles || [];
  const hasAccess = roles.length === 0 || roles.some((r) => userRoles.includes(r));

  return hasAccess ? children : <Navigate to="/unauthorized" />;
};

export default ProtectedRoute;
