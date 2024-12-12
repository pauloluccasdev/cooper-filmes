import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import '../style/NavBar.css';

const Navbar = () => {
  const navigate = useNavigate();
  const token = localStorage.getItem('token');

  const handleLogout = () => {
    localStorage.removeItem('token');
    navigate('/login');
  };

  return (
    <nav className="navbar">
      <div className="navbar-container">

        <div className="navbar-logo">
          <Link to="/">Roteiros App</Link>
        </div>

        <div className="navbar-links">
          {token ? (
            <>
              <Link to="/view-roadmaps">Meus Roteiros</Link>
              <button className="logout-button" onClick={handleLogout}>
                Logout
              </button>
            </>
          ) : (
            <>
              <Link to="/send-roadmap">Enviar Roteiro</Link>
              <Link to="/status-check">Consultar Status</Link>
              <Link to="/login">Login</Link>
            </>
          )}
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
