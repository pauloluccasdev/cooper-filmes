import React from 'react';
import axios from 'axios';

const Actions = ({ status, role, roadmapId }) => {
  const handleStatusUpdate = async (newStatus) => {
    try {
      await axios.put(`http://localhost:8080/api/road-map/status/${roadmapId}`, {
        status: newStatus,
      });
      alert('Status atualizado com sucesso!');
      window.location.reload();
    } catch (error) {
      console.error('Erro ao atualizar status:', error);
    }
  };

  const renderActions = () => {
    if (role === 'ANALISTA') {
      if (status === 'AGUARDANDO_ANALISE') {
        return (
          <>
            <button onClick={() => handleStatusUpdate('EM_ANALISE')}>Assumir</button>
            <button onClick={() => handleStatusUpdate('RECUSADO')}>Recusar</button>
          </>
        );
      } else if (status === 'EM_ANALISE') {
        return (
          <>
            <button onClick={() => handleStatusUpdate('AGUARDANDO_REVISAO')}>Enviar para Revisão</button>
            <button onClick={() => handleStatusUpdate('RECUSADO')}>Recusar</button>
          </>
        );
      }
    }

    if (role === 'REVISOR') {
      if (status === 'AGUARDANDO_REVISAO') {
        return <button onClick={() => handleStatusUpdate('EM_REVISAO')}>Assumir</button>;
      } else if (status === 'EM_REVISAO') {
        return <button onClick={() => handleStatusUpdate('AGUARDANDO_APROVACAO')}>Enviar para Aprovação</button>;
      }
    }

    if (role === 'APROVADOR') {
      if (status === 'AGUARDANDO_APROVACAO') {
        return (
          <>
            <button onClick={() => handleStatusUpdate('APROVADO')}>Aprovar</button>
            <button onClick={() => handleStatusUpdate('RECUSADO')}>Recusar</button>
          </>
        );
      }
    }

    return <span>Sem ações disponíveis</span>;
  };

  return <div>{renderActions()}</div>;
};

export default Actions;
