import { Link } from 'react-router-dom';
import { useAuth } from '../context/KeycloakContext';

export default function NavBar() {
    const { keycloak } = useAuth();
    const roles = keycloak.tokenParsed?.realm_access?.roles || [];

    const isLoggedIn = keycloak.authenticated;

    return (
        <nav style={{ padding: 10, borderBottom: '1px solid gray' }}>
            {roles.includes('admin') && (<Link to="/admin">Admin</Link>)}{" "}
            {roles.includes('doctor') && (<Link to="/doctor">Doctor</Link>)}{" "}
            {roles.includes('patient') && (<Link to="/patient">Patient</Link>)}{" "}
            {isLoggedIn && (
                <button onClick={() => keycloak.logout()} style={{ marginLeft: 10 }}>
                    Logout
                </button>
            )}
        </nav>
    );
}
