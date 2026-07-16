# Objectives

* Define SPA and its benefits
* Define React and identify its working
* Identify the differences between SPA and MPA
* Explain Pros & Cons of Single-Page Application
* Explain about React
* Define virtual DOM
* Explain Features of React

In this hands-on lab, you will learn how to:
* Set up a react environment
* Use `create-react-app`

## Prerequisites

The following is required to complete this hands-on lab:
* Node.js
* NPM
* Visual Studio Code

## Notes

Estimated time to complete this lab: 30 minutes.

Create a new React Application with the name “myfirstreact”, Run the application to print “welcome to the first session of React” as heading of that page.

1. To create a new React app, Install Nodejs and Npm from the following link: https://nodejs.org/en/download/
2. Install Create-react-app by running the following command in the command prompt:
   ```bash
   npm install -g create-react-app
   ```
3. To create a React Application with the name of “myfirstreact”, type the following command:
   ```bash
   npx create-react-app myfirstreact
   ```
4. Once the App is created, navigate into the folder of myfirstreact by typing the following command:
   ```bash
   cd myfirstreact
   ```
5. Open the folder of myfirstreact in Visual Studio Code.
6. Open the `App.js` file in `src` Folder of myfirstreact.
7. Remove the current content of `App.js`.
8. Replace it with the following:
   ```javascript
   import React from 'react';
   import './App.css';

   function App() {
     return (
       <div className="App">
         <h1>welcome to the first session of React</h1>
       </div>
     );
   }

   export default App;
   ```
9. Run the following command to execute the React application:
   ```bash
   npm start
   ```
10. Open a new browser window and type `localhost:3000` in the address bar.
