import { Routes, Route } from 'react-router-dom';
import { AuthProvider } from './context/KeycloakContext';
import NavBar from './components/NavBar';

import HomePage from './pages/HomePage';
import AdminPage from './pages/AdminPage';
import CreatePage from './pages/CreatePage';
import ReadPage from './pages/ReadPage';
import UpdatePage from './pages/UpdatePage';
import DoctorPage from './pages/DoctorPage';
import PatientPage from './pages/PatientPage';
import Unauthorized from './pages/UnauthorizedPage';
import NotFoundPage from './pages/NotFoundPage';

import ProtectedRoute from './routes/ProtectedRoute';

function App() {
  return (
    <AuthProvider>
      <NavBar />

      <Routes>
        <Route path="/" element={<HomePage />} />

        <Route
          path="/doctor"
          element={
            <ProtectedRoute roles={['doctor']}>
              <DoctorPage />
            </ProtectedRoute>
          }
        />

        <Route
          path="/patient"
          element={
            <ProtectedRoute roles={['patient']}>
              <PatientPage />
            </ProtectedRoute>
          }
        />

        <Route
          path="/admin"
          element={
            <ProtectedRoute roles={['admin']}>
              <AdminPage />
            </ProtectedRoute>
          }
        />

        <Route
          path="/create"
          element={
            <ProtectedRoute roles={['admin']}>
              <CreatePage />
            </ProtectedRoute>
          }
        />

        <Route
          path="/read"
          element={
            <ProtectedRoute roles={['admin']}>
              <ReadPage />
            </ProtectedRoute>
          }
        />

        <Route
          path="/update/:id"
          element={
            <ProtectedRoute roles={['admin']}>
              <UpdatePage />
            </ProtectedRoute>
          }
        />

        <Route path="/unauthorized" element={<Unauthorized />} />
        <Route path="*" element={<NotFoundPage />} />
      </Routes>
    </AuthProvider>
  );
}

export default App;
