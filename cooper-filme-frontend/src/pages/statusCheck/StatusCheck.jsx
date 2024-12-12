import React, { useState } from "react";
import axios from "../../api/axiosConfig";
import './StatusCheck.css';
import DataTable from "react-data-table-component";


const StatusCheck = () => {
  const [email, setEmail] = useState("");
  const [status, setStatus] = useState([]);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setStatus([]);
    setError(null);

    try {
      const response = await axios.get(`/road-map/status?clientEmail=${email}`);
      console.log(response);
      setStatus(response.data);
    } catch (err) {
      console.log(err);
      setError(err.response?.data?.message || "Erro ao consultar o status.");
    } finally {
      setLoading(false);
    }
  };

  const columns = [
    {
      name: "Nome do Arquivo",
      selector: (row) => row.fileName,
      sortable: true,
    },
    {
      name: "Status",
      selector: (row) => row.status,
      sortable: true,
    },
  ];

  return (
    <div className="container">
      <h1>Consulta de Status do Roteiro</h1>
      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="email">Email do Cliente:</label>
          <input
            type="email"
            id="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
        </div>
        <button type="submit" disabled={loading}>
          {loading ? "Consultando..." : "Consultar"}
        </button>
      </form>

      {status.length > 0 && (
        <div className="status-result">
          <h2>Status dos Roteiros:</h2>
          <DataTable
            columns={columns}
            data={status}
            pagination
            highlightOnHover
            striped
          />
        </div>
      )}

      {error && <div className="error-message">{error}</div>}
    </div>
  );
};

export default StatusCheck;
