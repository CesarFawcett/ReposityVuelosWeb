import React, { useState, useEffect } from 'react';
import './styles/globals.css';

// Datos de ejempl para probar la estructura 
const sampleFlights = [
  {
    id: 1,
    airline: 'Avianca',
    flightNumber: 'AV123',
    from: { city: 'Bogotá', code: 'BOG' },
    to: { city: 'Medellín', code: 'MDE' },
    departure: '08:00 AM',
    arrival: '09:15 AM',
    duration: '1h 15m',
    price: 250000,
    seats: 45,
    date: '2024-01-15'
  },
  {
    id: 2,
    airline: 'LATAM',
    flightNumber: 'LA456',
    from: { city: 'Medellín', code: 'MDE' },
    to: { city: 'Cartagena', code: 'CTG' },
    departure: '14:30 PM',
    arrival: '16:00 PM',
    duration: '1h 30m',
    price: 320000,
    seats: 28,
    date: '2024-01-15'
  },
  {
    id: 3,
    airline: 'Wingo',
    flightNumber: 'P5789',
    from: { city: 'Bogotá', code: 'BOG' },
    to: { city: 'Santa Marta', code: 'SMR' },
    departure: '11:45 AM',
    arrival: '13:30 PM',
    duration: '1h 45m',
    price: 210000,
    seats: 52,
    date: '2024-01-16'
  }
];

function App() {
  const [flights, setFlights] = useState(sampleFlights);
  const [search, setSearch] = useState({
    origin: '',
    destination: '',
    date: '',
    passengers: 1
  });

  const handleSearch = (e) => {
    e.preventDefault();
    console.log('Buscando vuelos:', search);
    
    const filtered = sampleFlights.filter(flight => {
      return (!search.origin || flight.from.city.includes(search.origin)) &&
             (!search.destination || flight.to.city.includes(search.destination));
    });
    setFlights(filtered);
  };

  const handleBook = (flightId) => {
    console.log('Reservando vuelo:', flightId);
   
    alert(`Reservando vuelo ${flightId}. `);
  };

  return (
    <div className="app">
      {/* Header */}
      <header className="header">
        <div className="container header-content">
          <a href="/" className="logo">✈️ VuelosWeb</a>
          <nav className="nav">
            <a href="/">Inicio</a>
            <a href="/vuelos">Vuelos</a>
            <a href="/reservas">Mis Reservas</a>
            <a href="/login">Iniciar Sesión</a>
          </nav>
        </div>
      </header>

      {/* Hero Section */}
      <section className="hero">
        <div className="container">
          <h1>Encuentra los mejores vuelos</h1>
          <p>Compara precios y reserva tus vuelos nacionales e internacionales</p>
          
          {/* Formulario de búsqueda simple */}
          <form onSubmit={handleSearch} style={{
            background: 'white',
            padding: '20px',
            borderRadius: '8px',
            maxWidth: '600px',
            margin: '0 auto',
            display: 'grid',
            gridTemplateColumns: '1fr 1fr',
            gap: '15px'
          }}>
            <div>
              <label>Origen</label>
              <input
                type="text"
                placeholder="Ej: Bogotá"
                value={search.origin}
                onChange={(e) => setSearch({...search, origin: e.target.value})}
                style={{
                  width: '100%',
                  padding: '10px',
                  marginTop: '5px',
                  border: '1px solid #ddd',
                  borderRadius: '4px'
                }}
              />
            </div>
            
            <div>
              <label>Destino</label>
              <input
                type="text"
                placeholder="Ej: Medellín"
                value={search.destination}
                onChange={(e) => setSearch({...search, destination: e.target.value})}
                style={{
                  width: '100%',
                  padding: '10px',
                  marginTop: '5px',
                  border: '1px solid #ddd',
                  borderRadius: '4px'
                }}
              />
            </div>
            
            <div>
              <label>Fecha</label>
              <input
                type="date"
                value={search.date}
                onChange={(e) => setSearch({...search, date: e.target.value})}
                style={{
                  width: '100%',
                  padding: '10px',
                  marginTop: '5px',
                  border: '1px solid #ddd',
                  borderRadius: '4px'
                }}
              />
            </div>
            
            <button 
              type="submit" 
              className="btn"
              style={{ gridColumn: '1 / -1' }}
            >
              🔍 Buscar Vuelos
            </button>
          </form>
        </div>
      </section>

      {/* Lista de Vuelos */}
      <main className="container">
        <h2 style={{ margin: '40px 0 20px', fontSize: '1.8rem' }}>
          Vuelos Disponibles
        </h2>
        
        {flights.length === 0 ? (
          <div style={{
            textAlign: 'center',
            padding: '40px',
            background: 'white',
            borderRadius: '8px',
            color: '#666'
          }}>
            <p>No se encontraron vuelos con esos criterios.</p>
            <button 
              onClick={() => setFlights(sampleFlights)}
              className="btn"
              style={{ marginTop: '20px' }}
            >
              Mostrar todos los vuelos
            </button>
          </div>
        ) : (
          <div className="flights-grid">
            {flights.map(flight => (
              <div key={flight.id} className="flight-card">
                <div className="flight-header">
                  <span className="airline">{flight.airline} - {flight.flightNumber}</span>
                  <span className="price">${flight.price.toLocaleString()}</span>
                </div>
                
                <div className="route">
                  <div className="from">
                    <div className="city">{flight.from.city}</div>
                    <div className="code">({flight.from.code})</div>
                    <div className="time">{flight.departure}</div>
                  </div>
                  
                  <div className="arrow">→</div>
                  
                  <div className="to">
                    <div className="city">{flight.to.city}</div>
                    <div className="code">({flight.to.code})</div>
                    <div className="time">{flight.arrival}</div>
                  </div>
                </div>
                
                <div className="details">
                  <div className="detail-item">
                    <span className="detail-label">Duración:</span>
                    <span className="detail-value">{flight.duration}</span>
                  </div>
                  <div className="detail-item">
                    <span className="detail-label">Fecha:</span>
                    <span className="detail-value">{flight.date}</span>
                  </div>
                  <div className="detail-item">
                    <span className="detail-label">Asientos disponibles:</span>
                    <span className="detail-value">{flight.seats}</span>
                  </div>
                </div>
                
                <button 
                  onClick={() => handleBook(flight.id)}
                  className="btn btn-book"
                >
                  Reservar Ahora
                </button>
              </div>
            ))}
          </div>
        )}
      </main>

      {/* Footer */}
      <footer className="footer">
        <div className="container">
          <div className="footer-content">
            <div className="footer-section">
              <h3>VuelosWeb</h3>
              <p>Tu plataforma confiable para reservar vuelos nacionales e internacionales al mejor precio.</p>
            </div>
            
            <div className="footer-section">
              <h3>Enlaces Rápidos</h3>
              <a href="/vuelos">Buscar Vuelos</a>
              <a href="/ofertas">Ofertas Especiales</a>
              <a href="/ayuda">Ayuda</a>
              <a href="/contacto">Contacto</a>
            </div>
            
            <div className="footer-section">
              <h3>Aerolíneas</h3>
              <a href="/avianca">Avianca</a>
              <a href="/latam">LATAM</a>
              <a href="/wingo">Wingo</a>
              <a href="/easyfly">EasyFly</a>
            </div>
            
            <div className="footer-section">
              <h3>Contacto</h3>
              <p>✉️ info@vuelosweb.com</p>
              <p>📞 +57 1 234 5678</p>
              <p>📍 Bogotá, Colombia</p>
            </div>
          </div>
          
          <div className="copyright">
            © 2024 VuelosWeb. Todos los derechos reservados.
          </div>
        </div>
      </footer>
    </div>
  );
}

export default App;