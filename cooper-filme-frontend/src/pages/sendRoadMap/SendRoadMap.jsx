import React, { useState } from 'react';
import axios from '../../api/axiosConfig.js';
import './SendoRoadMap.css'

const SendRoadmap = () => {
  const [formData, setFormData] = useState({
    clientName: '',
    clientEmail: '',
    clientPhone: '',
    file: null,
  });
  const [loading, setLoading] = useState(false);
  const [tooltip, setTooltip] = useState({ show: false, message: "", success: false });


  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleFileChange = (e) => {
    setFormData((prev) => ({
      ...prev,
      file: e.target.files[0],
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);

    const data = new FormData();
    data.append('clientName', formData.clientName);
    data.append('clientEmail', formData.clientEmail);
    data.append('clientPhone', formData.clientPhone);
    data.append('file', formData.file);

    try {
      const response = await axios.post('/road-map/send', data, {
        headers: {
          'Content-Type': 'multipart/form-data',
        },
      });

      setTooltip({ show: true, message: "Roteiro enviado com sucesso!", success: true });
      console.log(response);

      setTimeout(() => {
        setTooltip({ show: false, message: "", success: false });
        window.location.reload();
      }, 3000);
    } catch (error) {
      setTooltip({ show: true, message: "Erro ao enviar roteiro. Tente novamente.", success: false });
      console.error(error);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div>

      {tooltip.show && (
        <div
          style={{
            backgroundColor: tooltip.success ? "green" : "red",
            color: "white",
            padding: "10px",
            marginBottom: "10px",
            borderRadius: "5px",
          }}
        >
          {tooltip.message}
        </div>
      )}

      <h1>Envio de Roteiro</h1>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Nome do Cliente:</label>
          <input
            type="text"
            name="clientName"
            value={formData.clientName}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Email do Cliente:</label>
          <input
            type="email"
            name="clientEmail"
            value={formData.clientEmail}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Telefone do Cliente:</label>
          <input
            type="text"
            name="clientPhone"
            value={formData.clientPhone}
            onChange={handleChange}
            required
          />
        </div>
        <div>
          <label>Arquivo do Roteiro:</label>
          <input
            type="file"
            name="file"
            onChange={handleFileChange}
            required
          />
        </div>
        <button type="submit" disabled={loading}>
          {loading ? 'Enviando...' : 'Enviar'}
        </button>
      </form>
    </div>
  );
};

export default SendRoadmap;
