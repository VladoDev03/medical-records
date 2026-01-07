import { useState } from "react";
import keycloak from '../config/keycloak'

const BASE_URL = "http://localhost:8080/api/medicines";

export default function CreatePage() {
    const [name, setName] = useState("");
    const [manufacturer, setManufacturer] = useState("");


    const submit = async () => {
        await fetch(BASE_URL, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${keycloak.token}`,
            },
            body: JSON.stringify({ name, manufacturer }),
        });
    };


    return (
        <div className="p-4">
            <input placeholder="name" value={name} onChange={e => setName(e.target.value)} />
            <input placeholder="manufacturer" value={manufacturer} onChange={e => setManufacturer(e.target.value)} />
            <button onClick={submit}>Create</button>
        </div>
    );
}
