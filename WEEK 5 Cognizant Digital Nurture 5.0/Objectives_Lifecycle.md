# Objectives

* Explain the need and Benefits of component life cycle
* Identify various life cycle hook methods
* List the sequence of steps in rendering a component

In this hands-on lab, you will learn how to:
* Implement `componentDidMount()` hook
* Implement `componentDidCatch()` life cycle hook

## Prerequisites

The following is required to complete this hands-on lab:
* Node.js
* NPM
* Visual Studio Code

## Notes

Estimated time to complete this lab: 60 minutes.

1. Create a new React application using `create-react-app` tool with the name as “blogapp”:
   ```bash
   npx create-react-app blogapp
   ```
2. Open the application using VS Code.
3. Create a new file named as `Posts.js` in `src` folder.
4. Create a new class-based component named as `Posts` inside `Posts.js` file.
5. Initialize the component with a list of Post in state of the component using the constructor.
6. Create a new method in the component with the name `loadPosts()` which will be responsible for using Fetch API and assigning the result to the component state created earlier. To get the posts, use the URL: `https://jsonplaceholder.typicode.com/posts`.
7. Implement the `componentDidMount()` hook to make calls to `loadPosts()` which will fetch the posts.
8. Implement the `render()` method which will display the title and body of posts in the HTML page using headings and paragraphs respectively.
9. Define a `componentDidCatch()` method which will be responsible for displaying any error happening in the component as alert messages.
10. Add the `Posts` component to `App` component.
11. Build and Run the application using `npm start` command.

### Completed Source Files

#### `src/Posts.js`
```javascript
import React, { Component } from 'react';

class Posts extends Component {
  constructor(props) {
    super(props);
    this.state = {
      posts: [],
      hasError: false,
      errorMessage: ''
    };
  }

  loadPosts() {
    fetch('https://jsonplaceholder.typicode.com/posts')
      .then(response => {
        if (!response.ok) {
          throw new Error('Failed to fetch posts');
        }
        return response.json();
      })
      .then(data => {
        this.setState({ posts: data });
      })
      .catch(error => {
        alert('Fetch Error: ' + error.message);
        this.setState({ hasError: true, errorMessage: error.message });
      });
  }

  componentDidMount() {
    this.loadPosts();
  }

  componentDidCatch(error, info) {
    alert('Error caught in Posts component: ' + error.message);
    this.setState({ hasError: true, errorMessage: error.message });
  }

  render() {
    if (this.state.hasError) {
      return (
        <div style={{ padding: '20px', color: 'red' }}>
          <h2>Something went wrong: {this.state.errorMessage}</h2>
        </div>
      );
    }

    return (
      <div style={{ padding: '20px', maxWidth: '800px', margin: '0 auto', fontFamily: 'Arial, sans-serif' }}>
        <h1 style={{ textAlign: 'center', color: '#333' }}>Blog Posts</h1>
        {this.state.posts.map(post => (
          <div key={post.id} style={{ borderBottom: '1px solid #eee', padding: '15px 0' }}>
            <h3 style={{ color: '#4a90e2', textTransform: 'capitalize' }}>{post.title}</h3>
            <p style={{ color: '#666', lineHeight: '1.6' }}>{post.body}</p>
          </div>
        ))}
      </div>
    );
  }
}

export default Posts;
```

#### `src/App.js`
```javascript
import React from 'react';
import './App.css';
import Posts from './Posts';

function App() {
  return (
    <div className="App">
      <Posts />
    </div>
  );
}

export default App;
```
