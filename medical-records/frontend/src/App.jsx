import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { AuthProvider } from './context/KeycloakContext';
import NavBar from './components/NavBar';
import HomePage from './pages/HomePage';
import Unauthorized from './pages/UnauthorizedPage';
import ProtectedRoute from './routes/ProtectedRoute';
import NotFoundPage from './pages/NotFoundPage';
import DoctorPage from './pages/DoctorPage';
import PatientPage from './pages/PatientPage';

function App() {
  return (
    <AuthProvider>
      <NavBar />
      <Routes>
        <Route path="/" element={<HomePage />} />
        <Route path="/doctor" element={
          <ProtectedRoute roles={['doctor']}>
            <DoctorPage />
          </ProtectedRoute>
        } />
        <Route path="/patient" element={
          <ProtectedRoute roles={['patient']}>
            <PatientPage />
          </ProtectedRoute>
        } />
        <Route path="/unauthorized" element={<Unauthorized />} />
        <Route path="*" element={<NotFoundPage />} />
      </Routes>
    </AuthProvider>
  );
}

export default App;
