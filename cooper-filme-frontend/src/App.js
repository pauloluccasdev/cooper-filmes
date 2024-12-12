import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Navbar from './components/NavBar';
import SendRoadmap from './pages/sendRoadMap/SendRoadMap.jsx';
import StatusCheck from './pages/statusCheck/StatusCheck.jsx';
import Login from './pages/login/Login.jsx';
import ProtectedRoute from './components/ProtectedRoute.js';
import FilteredRoadmaps from './pages/filteredRoadmaps/FilteredRoadmaps.jsx';
import { AuthProvider } from './api/useContext.js';
import Dashboard from './pages/dashboard/Dashboad.jsx';

function App() {
  return (
    <AuthProvider>
      <Router>
        <Navbar />
        <Routes>
          <Route
            path="/send-roadmap"
            element={<SendRoadmap />}
          />
          <Route
            path="/status-check"
            element={<StatusCheck />}
          />

          <Route path="/login" element={<Login />} />

          <Route path="/roadmaps" element={<ProtectedRoute element={FilteredRoadmaps} />} />
          <Route path="/dashboard" element={<ProtectedRoute element={Dashboard} />} />


        </Routes>
      </Router>
    </AuthProvider>
  );
}

export default App;
