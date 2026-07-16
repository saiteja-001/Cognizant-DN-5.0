# Objectives

* Explain React components
* Identify the differences between components and JavaScript functions
* Identify the types of components
* Explain class component
* Explain function component
* Define component constructor
* Define `render()` function

In this hands-on lab, you will learn how to:
* Create a class component
* Create multiple components
* Render a component

## Prerequisites

The following is required to complete this hands-on lab:
* Node.js
* NPM
* Visual Studio Code

## Notes

Estimated time to complete this lab: 30 minutes.

Create a react app for Student Management Portal named StudentApp and create a component named Home which will display the Message “Welcome to the Home page of Student Management Portal”. Create another component named About and display the Message “Welcome to the About page of the Student Management Portal”. Create a third component named Contact and display the Message “Welcome to the Contact page of the Student Management Portal”. Call all the three components.

1. Create a React project named “StudentApp” type the following command in terminal of Visual Studio:
   ```bash
   npx create-react-app StudentApp
   ```
2. Create a new folder under `src` folder with the name `Components`. Add a new file named `Home.js`.
3. Type the following code in `Home.js`:
   ```javascript
   import React, { Component } from 'react';

   class Home extends Component {
     render() {
       return (
         <div>
           <h2>Welcome to the Home page of Student Management Portal</h2>
         </div>
       );
     }
   }

   export default Home;
   ```
4. Under `src/Components` folder add another file named `About.js`.
5. Repeat the same steps for Creating “About” and “Contact” components by adding new files as `About.js` and `Contact.js` under `src/Components` folder and edit the code as mentioned for “Home” Component:
   * **About.js**:
     ```javascript
     import React, { Component } from 'react';

     class About extends Component {
       render() {
         return (
           <div>
             <h2>Welcome to the About page of the Student Management Portal</h2>
           </div>
         );
       }
     }

     export default About;
     ```
   * **Contact.js**:
     ```javascript
     import React, { Component } from 'react';

     class Contact extends Component {
       render() {
         return (
           <div>
             <h2>Welcome to the Contact page of the Student Management Portal</h2>
           </div>
         );
       }
     }

     export default Contact;
     ```
6. Edit the `App.js` to invoke the Home, About, and Contact components as follows:
   ```javascript
   import React from 'react';
   import './App.css';
   import Home from './Components/Home';
   import About from './Components/About';
   import Contact from './Components/Contact';

   function App() {
     return (
       <div className="App">
         <Home />
         <About />
         <Contact />
       </div>
     );
   }

   export default App;
   ```
7. In Command Prompt, navigate into `StudentApp` and execute the code by typing the following command:
   ```bash
   npm start
   ```
8. Open browser and type `localhost:3000` in the address bar.
