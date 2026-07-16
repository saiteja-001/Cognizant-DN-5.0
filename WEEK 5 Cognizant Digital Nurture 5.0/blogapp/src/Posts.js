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
