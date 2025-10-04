import { Link } from 'react-router-dom'

export default function NotFoundPage() {
    return (
        <div>
            <h1>404 😒</h1>
            <Link to="/">Return to home page</Link>
        </div>
    );
}
