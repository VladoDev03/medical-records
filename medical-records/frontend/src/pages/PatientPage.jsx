import { useState } from 'react';
import { Link } from 'react-router-dom'
import { getPatients } from '../services/service';

export default function PatientPage() {
    const [patients, setPatients] = useState([]);

    const clickHandler = async () => {
        const result = await getPatients();
        setPatients(result);
    };

    return (
        <>
            <h1 onClick={clickHandler}>Patient Page</h1>
            <div>
                <ul>
                    {patients.map(x => <li key={x.id}>{x.keycloakId}</li>)}
                </ul>
            </div>
            <Link to="/">Return to home page</Link>
        </>
    );
}
