import React, { useState } from "react";
import axios from "axios";

const FilteredRoadmaps = () => {
  const [roadmaps, setRoadmaps] = useState([]);
  const [filters, setFilters] = useState({
    status: "",
    email: "",
    startDate: "",
    endDate: "",
  });
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  const fetchRoadmaps = async () => {
    setLoading(true);
    setError("");
    try {
      const params = {};
      if (filters.status) params.status = filters.status;
      if (filters.email) params.email = filters.email;
      if (filters.startDate) params.startDate = filters.startDate;
      if (filters.endDate) params.endDate = filters.endDate;

      const response = await axios.get("http://localhost:8080/api/road-map", {
        params,
      });

      setRoadmaps(response.data);
    } catch (err) {
      setError("Erro ao carregar os roteiros. Tente novamente.");
    } finally {
      setLoading(false);
    }
  };

  const handleFilterChange = (e) => {
    const { name, value } = e.target;
    setFilters((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    fetchRoadmaps();
  };

  return (
    <div>
      <h1>Listagem de Roteiros</h1>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Status:</label>
          <select name="status" value={filters.status} onChange={handleFilterChange}>
            <option value="">Todos</option>
            <option value="AGUARDANDO_ANALISE">Aguardando Análise</option>
            <option value="APROVADO">Aprovado</option>
            <option value="REJEITADO">Rejeitado</option>
          </select>
        </div>
        <div>
          <label>Email:</label>
          <input
            type="email"
            name="email"
            value={filters.email}
            onChange={handleFilterChange}
          />
        </div>
        <div>
          <label>Data Inicial:</label>
          <input
            type="date"
            name="startDate"
            value={filters.startDate}
            onChange={handleFilterChange}
          />
        </div>
        <div>
          <label>Data Final:</label>
          <input
            type="date"
            name="endDate"
            value={filters.endDate}
            onChange={handleFilterChange}
          />
        </div>
        <button type="submit">Filtrar</button>
      </form>

      {loading && <p>Carregando...</p>}
      {error && <p>{error}</p>}

      <table border="1">
        <thead>
          <tr>
            <th>Nome do Arquivo</th>
            <th>Status</th>
            <th>Data de Envio</th>
          </tr>
        </thead>
        <tbody>
          {roadmaps.map((roadmap, index) => (
            <tr key={index}>
              <td>{roadmap.fileName}</td>
              <td>{roadmap.status}</td>
              <td>{roadmap.createdAt}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default FilteredRoadmaps;
