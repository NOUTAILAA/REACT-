import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const HomePage = () => {
  const [voitures, setVoitures] = useState([]);
  const [loading, setLoading] = useState(true);
  const [errorMessage, setErrorMessage] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchVoitures = async () => {
      try {
        const response = await axios.get('http://localhost:8888/voitures');
        setVoitures(response.data);
      } catch (error) {
        setErrorMessage('Erreur de connexion');
      } finally {
        setLoading(false);
      }
    };
    fetchVoitures();
  }, []);

  if (loading) return <div className="text-center mt-5">Chargement...</div>;
  if (errorMessage)
    return <div className="alert alert-danger text-center mt-5">{errorMessage}</div>;

  return (
    <div className="container mt-5">
      <h1 className="text-center mb-4">Liste des Voitures</h1>
      <div className="list-group">
        {voitures.map((voiture) => (
          <button
            key={voiture.id}
            className="list-group-item list-group-item-action d-flex justify-content-between align-items-center"
            onClick={() => navigate(`/details/${voiture.id}`)}
          >
            <div>
              <strong>{voiture.marque}</strong> - {voiture.model}
            </div>
            <span className="badge bg-primary">Matricule: {voiture.matricule}</span>
          </button>
        ))}
      </div>
      
      {/* Button to navigate to ClientVoitureSelection */}
      <div className="text-center mt-4">
        <button 
          className="btn btn-secondary"
          onClick={() => navigate('/search')}
        >
          Choisir un Client et ses Voitures
        </button>
      </div>
    </div>
  );
};

export default HomePage;
