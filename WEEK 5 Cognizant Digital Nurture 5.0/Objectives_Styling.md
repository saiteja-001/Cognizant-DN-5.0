# Objectives

* Understanding the need for styling React component
* Working with CSS Module and inline styles

In this hands-on lab, you will learn how to:
* Style a React component
* Define styles using the CSS Module
* Apply styles to components using `className` and `style` properties

## Prerequisites

The following is required to complete this hands-on lab:
* Node.js
* NPM
* Visual Studio Code

## Notes

Estimated time to complete this lab: 30 minutes.

My Academy team at Cognizant want to create a dashboard containing the details of ongoing and completed cohorts. A React application is created which displays the detail of the cohorts using React components. You are assigned the task of styling these React components.

1. Create a React project named `cohorts-app` using `create-react-app`:
   ```bash
   npx create-react-app cohorts-app
   ```
2. Open the application using VS Code.
3. Create a new CSS Module file named `CohortDetails.module.css` in `src/Components/`.
4. Define a CSS class with the name `box` with following properties:
   * `width`: 300px
   * `display`: inline-block
   * `margin`: 10px
   * `padding-top` & `padding-bottom`: 10px
   * `padding-left` & `padding-right`: 20px
   * `border`: 1px solid black
   * `border-radius`: 10px
5. Define a CSS style for the HTML `<dt>` element using a tag selector. Set the font weight to 500.
6. Open the cohort details component and import the CSS Module.
7. Apply the `box` class to the container `div`.
8. Define the inline style for the `<h3>` element to use `green` color font when the cohort status is `ongoing` and `blue` color in all other scenarios.
9. Verify the final result.

### Completed Source Files

#### `src/Components/CohortDetails.module.css`
```css
.box {
  width: 300px;
  display: inline-block;
  margin: 10px;
  padding: 10px 20px;
  border: 1px solid black;
  border-radius: 10px;
  box-sizing: border-box;
  vertical-align: top;
  background-color: #fafafa;
  font-family: Arial, sans-serif;
}

dt {
  font-weight: 500;
  margin-top: 8px;
  color: #555;
}

dd {
  margin-left: 0;
  margin-bottom: 8px;
  color: #333;
}
```

#### `src/Components/CohortDetails.js`
```javascript
import React from 'react';
import styles from './CohortDetails.module.css';

function CohortDetails({ cohort }) {
  const isOngoing = cohort.status.toLowerCase() === 'ongoing';
  const headingStyle = {
    color: isOngoing ? 'green' : 'blue',
    marginTop: '0'
  };

  return (
    <div className={styles.box}>
      <h3 style={headingStyle}>{cohort.name}</h3>
      <dl>
        <dt>Cohort ID</dt>
        <dd>{cohort.id}</dd>
        <dt>Domain</dt>
        <dd>{cohort.domain}</dd>
        <dt>Start Date</dt>
        <dd>{cohort.startDate}</dd>
        <dt>Status</dt>
        <dd>{cohort.status}</dd>
      </dl>
    </div>
  );
}

export default CohortDetails;
```

#### `src/App.js`
```javascript
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
```
