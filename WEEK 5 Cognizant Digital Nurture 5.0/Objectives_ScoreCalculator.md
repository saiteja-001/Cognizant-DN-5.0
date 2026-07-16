# Objectives

* Explain React components
* Identify the differences between components and JavaScript functions
* Identify the types of components
* Explain class component
* Explain function component
* Define component constructor
* Define `render()` function

In this hands-on lab, you will learn how to:
* Create a function component
* Apply style to components
* Render a component

## Prerequisites

The following is required to complete this hands-on lab:
* Node.js
* NPM
* Visual Studio Code

## Notes

Estimated time to complete this lab: 30 minutes.

Create a react app for Student Management Portal named scorecalculatorapp and create a function component named “CalculateScore” which will accept Name, School, Total and goal in order to calculate the average score of a student and display the same.

1. Create a React project named “scorecalculatorapp” type the following command in terminal of Visual Studio:
   ```bash
   npx create-react-app scorecalculatorapp
   ```
2. Create a new folder under `src` folder with the name `Components`. Add a new file named `CalculateScore.js`.
3. Type the following code in `CalculateScore.js`:
   ```javascript
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
   ```
4. Create a Folder named `Stylesheets` under `src` and add a file named `mystyle.css` in order to add some styles to the components:
   ```css
   .formatstyle {
     font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
     border: 2px solid #4a90e2;
     padding: 20px;
     margin: 20px auto;
     background-color: #f4f7f6;
     border-radius: 10px;
     max-width: 400px;
     box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
   }

   .formatstyle h2 {
     color: #4a90e2;
     margin-bottom: 15px;
     text-align: center;
   }

   .formatstyle p {
     font-size: 16px;
     color: #333;
     line-height: 1.6;
     margin: 10px 0;
     border-bottom: 1px solid #e0e0e0;
     padding-bottom: 5px;
   }
   ```
5. Edit the `App.js` to invoke the `CalculateScore` functional component as follows:
   ```javascript
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
   ```
6. In Command Prompt, navigate into `scorecalculatorapp` and execute the code by typing the following command:
   ```bash
   npm start
   ```
7. Open browser and type `localhost:3000` in the address bar.
