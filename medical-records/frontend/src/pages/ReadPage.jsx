import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import keycloak from '../config/keycloak';

const MEDICINES_URL = "http://localhost:8080/api/medicines";
const VISITS_URL = "http://localhost:8080/api/visits";

export default function ReadPage() {
    const [medicines, setMedicines] = useState([]);
    const [visits, setVisits] = useState([]);

    const loadMedicines = async () => {
        const response = await fetch(MEDICINES_URL, {
            headers: {
                Authorization: `Bearer ${keycloak.token}`,
            },
        });
        setMedicines(await response.json());
    };

    const loadVisits = async () => {
        const response = await fetch(VISITS_URL, {
            headers: {
                Authorization: `Bearer ${keycloak.token}`,
            },
        });
        setVisits(await response.json());
    };

    const removeVisit = async (id) => {
        await fetch(`${VISITS_URL}/${id}`, {
            method: "DELETE",
            headers: {
                Authorization: `Bearer ${keycloak.token}`,
            },
        });
        loadVisits();
    };

    useEffect(() => {
        loadMedicines();
        loadVisits();
    }, []);

    return (
        <div className="p-4 space-y-8">

            <div>
                <h2 className="text-lg font-bold mb-2">Medicines</h2>
                <ul className="space-y-2">
                    {medicines.map((m) => (
                        <li key={m.id} className="flex gap-4 items-center">
                            <span>{m.name} → {m.manufacturer}</span>

                            <Link to={`/update/${m.id}`}>
                                <button>Update</button>
                            </Link>
                        </li>
                    ))}
                </ul>
            </div>

            <div>
                <h2 className="text-lg font-bold mb-2">Visits</h2>
                <ul className="space-y-2">
                    {visits.map((v) => (
                        <li key={v.id} className="flex gap-4 items-center">
                            <span>ID: {v.id} | Date: {v.visitDate}</span>
                            <button onClick={() => removeVisit(v.id)}>Delete</button>
                        </li>
                    ))}
                </ul>
            </div>

        </div>
    );
}
