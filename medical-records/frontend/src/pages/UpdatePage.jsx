import { useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import keycloak from '../config/keycloak';

const BASE_URL = "http://localhost:8080/api/medicines";

export default function UpdatePage() {
    const { id } = useParams();
    const navigate = useNavigate();
    const [name, setName] = useState("");
    const [manufacturer, setManufacturer] = useState("");


    const submit = async () => {
        await fetch(`${BASE_URL}/${id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${keycloak.token}`,
            },
            body: JSON.stringify({ name, manufacturer }),
        });
        navigate("/read");
    };


    return (
        <div className="p-4">
            <input placeholder="name" value={name} onChange={e => setName(e.target.value)} />
            <input placeholder="manufacturer" value={manufacturer} onChange={e => setManufacturer(e.target.value)} />
            <button onClick={submit}>Update</button>
        </div>
    );
}
