import React, { useState, useEffect } from 'react';
import './PostStyle/All.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faComment } from '@fortawesome/free-solid-svg-icons';

function PostList() {
  const [posts, setPosts] = useState([]);

  useEffect(() => {
    fetch('http://localhost:8080/paf/allpost')
      .then(response => response.json())
      .then(data => setPosts(data))
      .catch(error => console.error(error));
  }, []);

  const handleCommentClick = (postId) => {
    // Implement logic to handle comment click
    console.log(`Comment clicked for post ${postId}`);
  };

  return (
    <div className="post-list-wrapper">
      <h1>Post List</h1>
      <ul className="post-list">
        {posts.map(post => (
          <li key={post.id} className="post-container post-size">
            <h2>{post.caption}</h2>
            <p>{post.expression}</p>
            <div className="photo-container">
              {post.photos.map((photo, index) => (
                <img key={index} className="photo post-size" src={`data:image/jpeg;base64,${photo}`} alt="" />
              ))}
            </div>
            <div className="comment-container">
              <FontAwesomeIcon icon={faComment} onClick={() => handleCommentClick(post.id)} />
              <span className="comment-label">Add Comment</span>
            </div>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default PostList;
