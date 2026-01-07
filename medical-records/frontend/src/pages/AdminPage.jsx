import { Link } from "react-router-dom";

export default function AdminPage() {
  return (
    <nav className="flex gap-4 p-4">
      <Link to="/create">Create</Link>
      <Link to="/read">Read</Link>
      <Link to="/update">Update</Link>
    </nav>
  );
}
