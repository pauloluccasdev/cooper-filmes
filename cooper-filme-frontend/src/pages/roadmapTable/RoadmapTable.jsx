import React, { useEffect, useState } from 'react';
import axios from 'axios';
import Actions from './Actions';

const RoadmapTable = () => {
  const [roadmaps, setRoadmaps] = useState([]);
  const [userRole, setUserRole] = useState('');

  useEffect(() => {
    const fetchRoadmaps = async () => {
      try {
        const response = await axios.get('http://localhost:8080/api/road-map');
        setRoadmaps(response.data);

        const role = 'ANALISTA';
        setUserRole(role);
      } catch (error) {
        console.error('Erro ao buscar roteiros:', error);
      }
    };

    fetchRoadmaps();
  }, []);

  return (
    <div>
      <h2>Listagem de Roteiros</h2>
      <table>
        <thead>
          <tr>
            <th>Nome do Arquivo</th>
            <th>Status</th>
            <th>Ações</th>
          </tr>
        </thead>
        <tbody>
          {roadmaps.map((roadmap, index) => (
            <tr key={index}>
              <td>{roadmap.fileName}</td>
              <td>{roadmap.status}</td>
              <td>
                <Actions 
                  status={roadmap.status} 
                  role={userRole} 
                  roadmapId={roadmap.id} 
                />
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default RoadmapTable;
