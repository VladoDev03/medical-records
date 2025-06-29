import { useState } from 'react';
import { Link } from 'react-router-dom'
import { createPatient } from '../services/service';

export default function DoctorPage() {
    const [patient, setPatient] = useState();

    const clickHandler = async () => {
        const result = await createPatient();
        setPatient(result);
    };

    return (
        <>
            <h1 onClick={clickHandler}>Doctor Page</h1>
            {patient && <div>
                <h2>Patient with ID {patient.id} created.</h2>
                <h2>We cheat the system so their last date insured is {patient.lastDateInsured}.</h2>
            </div>}
            <Link to="/">Return to home page</Link>
        </>
    );
}
