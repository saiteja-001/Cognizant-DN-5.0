import React from 'react';
import '../Stylesheets/mystyle.css';

function CalculateScore({ Name, School, Total, Goal }) {
  const average = Goal > 0 ? (Total / Goal) * 100 : 0;
  return (
    <div className="formatstyle">
      <h2>Student Details:</h2>
      <p><b>Name:</b> {Name}</p>
      <p><b>School:</b> {School}</p>
      <p><b>Total:</b> {Total}</p>
      <p><b>Goal:</b> {Goal}</p>
      <p><b>Average Score:</b> {average.toFixed(2)}%</p>
    </div>
  );
}

export default CalculateScore;
