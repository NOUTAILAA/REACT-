import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';

const DetailsPage = () => {
  const { id } = useParams();
  const [voiture, setVoiture] = useState(null);
  const [loading, setLoading] = useState(true);
  const [errorMessage, setErrorMessage] = useState(null);

  useEffect(() => {
    const fetchVoiture = async () => {
      try {
        const response = await axios.get(`http://localhost:8888/voitures/${id}`);
        setVoiture(response.data);
      } catch (error) {
        setErrorMessage('Erreur de connexion');
      } finally {
        setLoading(false);
      }
    };
    fetchVoiture();
  }, [id]);

  if (loading) return <div className="text-center mt-5">Chargement...</div>;
  if (errorMessage)
    return <div className="alert alert-danger text-center mt-5">{errorMessage}</div>;

  return (
    <div className="container mt-5">
      <div className="card shadow-lg">
        <div className="card-header bg-primary text-white">
          <h3>Détails de la Voiture</h3>
        </div>
        <div className="card-body">
          <p>
            <strong>Marque :</strong> {voiture.marque}
          </p>
          <p>
            <strong>Modèle :</strong> {voiture.model}
          </p>
          <p>
            <strong>Matricule :</strong> {voiture.matricule}
          </p>
        </div>
      </div>
      <div className="card shadow-lg mt-4">
        <div className="card-header bg-secondary text-white">
          <h3>Client Associé</h3>
        </div>
        <div className="card-body">
          {voiture.client ? (
            <div>
              <p>
                <strong>Nom :</strong> {voiture.client.name}
              </p>
              <p>
                <strong>Âge :</strong> {voiture.client.age}
              </p>
            </div>
          ) : (
            <p>Aucun client associé</p>
          )}
        </div>
      </div>
    </div>
  );
};

export default DetailsPage;
