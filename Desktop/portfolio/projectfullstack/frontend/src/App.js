import React, { useState, useEffect } from 'react';

function App() {
  const [portfolio, setPortfolio] = useState([]);

  useEffect(() => {
    // כאן אנו מבצעים את הבקשה ל-Backend כדי למשוך את הנתונים
    fetch('http://localhost:8081/portfolio')
      .then(response => response.json())
      .then(data => {
        // שומרים את הנתונים בסטייט
        setPortfolio(data);
      })
      .catch(error => console.error('Error fetching portfolio:', error));
  }, []); // ייבצע את הקוד רק פעם אחת כשהקומפוננטה נטענת

  return (
    <div>
      <h1>Portfolio</h1>
      {portfolio.length === 0 ? (
        <p>Loading...</p>
      ) : (
        <ul>
          {portfolio.map(item => (
            <li key={item.id}>
              <strong>{item.name}</strong>: {item.description}
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}

export default App;
