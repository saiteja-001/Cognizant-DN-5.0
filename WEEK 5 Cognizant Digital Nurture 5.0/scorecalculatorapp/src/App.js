import React from 'react';
import './App.css';
import CalculateScore from './Components/CalculateScore';

function App() {
  return (
    <div className="App">
      <CalculateScore Name="Alex Carter" School="Springfield High" Total={380} Goal={500} />
    </div>
  );
}

export default App;
