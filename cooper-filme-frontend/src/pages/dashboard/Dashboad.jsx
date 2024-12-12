import React, { useContext } from 'react';
import { AuthContext } from '../../api/useContext';

const Dashboard = () => {
    const { user, logout } = useContext(AuthContext);
  
    return (
      <div>
        <h1>Dashboard</h1>
        <p>Welcome, {user?.role}!</p>
  
        {user?.role === 'ADMIN' && <p>Admin-specific content here.</p>}
        {user?.role === 'ANALISTA' && <p>Analyst-specific content here.</p>}
        {user?.role === 'REVISOR' && <p>Revisor-specific content here.</p>}
  
        <button onClick={logout}>Logout</button>
      </div>
    );
  };

export default Dashboard;
