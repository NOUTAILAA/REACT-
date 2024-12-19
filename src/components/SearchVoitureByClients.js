import React, { useState, useEffect } from 'react';

const ClientVoitureSelection = () => {
  const [clients, setClients] = useState([]);
  const [voitures, setVoitures] = useState([]);
  const [selectedClient, setSelectedClient] = useState('');

  // Récupérer la liste des clients
  useEffect(() => {
    fetch('http://localhost:8070/clients') // Remplacez par l'URL de votre API pour récupérer les clients
      .then(response => response.json())
      .then(data => setClients(data))
      .catch(error => console.error('Error:', error));
  }, []);

  // Lorsque l'utilisateur sélectionne un client, récupérer les voitures associées
  const handleClientChange = (event) => {
    const clientId = event.target.value;
    setSelectedClient(clientId);

    if (clientId) {
      fetch(`http://localhost:8089/voitures/client/${clientId}`) // Remplacez par l'URL de votre API pour récupérer les voitures
        .then(response => response.json())
        .then(data => setVoitures(data))
        .catch(error => console.error('Error:', error));
    } else {
      setVoitures([]);
    }
  };

  return (
    <div>
      <h1>Choisir un client et afficher ses voitures</h1>

      {/* Liste déroulante pour sélectionner un client */}
      <select onChange={handleClientChange} value={selectedClient}>
        <option value="">Sélectionner un client</option>
        {clients.map(client => (
          <option key={client.id} value={client.id}>
            {client.name} 
          </option>
        ))}
      </select>

      {selectedClient && (
        <div>
          <h2>Voitures du Client:</h2>
          <table>
            <thead>
              <tr>
                <th>Marque</th>
                <th>Matricule</th>
                <th>Modèle</th>
              </tr>
            </thead>
            <tbody>
              {voitures.map(voiture => (
                <tr key={voiture.id}>
                  <td>{voiture.marque}</td>
                  <td>{voiture.matricule}</td>
                  <td>{voiture.model}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
};

export default ClientVoitureSelection;
