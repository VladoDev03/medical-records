import { useEffect, useState } from 'react';
import { getPatients, createPatient } from '../services/service';

export default function HomePage() {
    const [patient, setPatient] = useState();
    const [patients, setPatients] = useState([]);

    useEffect(() => {
        getAll();
    }, []);

    const getAll = async () => {
        const result = await getPatients();
        setPatients(result);
    };

    const clickHandler = async () => {
        const result = await createPatient();
        setPatient(result);
    };

    return (
        <>
            <h1 onClick={clickHandler}>Welcome to the Home Page</h1>
            <div>
                <ul>
                    {patients.map(x => <li key={x.id}>{x.keycloakId}</li>)}
                </ul>
            </div>
        </>
    );
}
