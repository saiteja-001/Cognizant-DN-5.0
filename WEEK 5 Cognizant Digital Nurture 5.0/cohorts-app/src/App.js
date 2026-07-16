import React from 'react';
import './App.css';
import CohortDetails from './Components/CohortDetails';

const cohortsList = [
  { id: 'CH-2026-A1', name: 'Java Full Stack Developer', domain: 'Application Development', startDate: '2026-06-01', status: 'ongoing' },
  { id: 'CH-2026-B3', name: 'React Native Cohort', domain: 'Mobile Development', startDate: '2026-04-10', status: 'completed' },
  { id: 'CH-2026-C2', name: 'AWS Cloud Architect', domain: 'Cloud & DevOps', startDate: '2026-07-15', status: 'ongoing' },
  { id: 'CH-2026-D4', name: 'Data Engineering with Spark', domain: 'Big Data & AI', startDate: '2026-03-01', status: 'completed' }
];

function App() {
  return (
    <div style={{ padding: '20px', fontFamily: 'Arial, sans-serif' }}>
      <h1 style={{ textAlign: 'center', color: '#333' }}>Cognizant Academy Cohorts Dashboard</h1>
      <hr />
      <div style={{ display: 'flex', flexWrap: 'wrap', justifyContent: 'center', marginTop: '20px' }}>
        {cohortsList.map(cohort => (
          <CohortDetails key={cohort.id} cohort={cohort} />
        ))}
      </div>
    </div>
  );
}

export default App;
