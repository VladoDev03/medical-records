import { Link } from 'react-router-dom'

export default function Unauthorized() {
    return (
        <div>
            <h1>Unauthorized Access</h1>
            <Link to="/">Return to home page</Link>
        </div>
    );
}
