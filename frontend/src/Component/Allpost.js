import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './PostStyle/Allpost.css';

const Allpost = () => {
  const [posts, setPosts] = useState([]);

import { Link } from 'react-router-dom';

const Allpost = () => {
  const [posts, setPosts] = useState([]);
  const [currentPhotoIndex, setCurrentPhotoIndex] = useState(0);
  const [selectedPost, setSelectedPost] = useState(null);
  const userid = 2;


  useEffect(() => {
    async function fetchPosts() {
      try {

        const res = await axios.get('http://localhost:8080/paf/allpost');

        const res = await axios.get(`http://localhost:8080/paf/user/${userid}`);

        setPosts(res.data);
      } catch (err) {
        console.error(err);
      }
    }
    fetchPosts();
  }, []);


  return (
    <div className="gallery-container">
      {posts.map((post) => (
        <div key={post._id} className="gallery-item">
          <img src={`data:image/jpeg;base64,${post.photo}`} alt={post.caption} />
          <div className="gallery-caption">
            <h3>{post.caption}</h3>
            <p>{post.expression}</p>

  useEffect(() => {
    if (posts.length > 0) {
      const interval = setInterval(() => {
        setCurrentPhotoIndex((currentPhotoIndex + 1) % posts.length);
      }, 5000);
      return () => clearInterval(interval);
    }
  }, [currentPhotoIndex, posts]);

  const handleDelete = async (postId) => {
    try {
      await axios.delete(`http://localhost:8080/paf/deletepost/${postId}`);
      setPosts(posts.filter((post) => post._id !== postId));
    } catch (err) {
      console.error(err);
      // TODO: Handle error and show error message to the user
    }
  };

  const handleImageClick = (index) => {
    setCurrentPhotoIndex(index);
  };

  return (
    <>
      <div className="add-new-button-container">
        <Link to="/add">
          <button>Add New</button>
        </Link>
      </div>
      <div className="gallery-container">
        {posts.map((post, index) => (
          <div
            key={post._id}
            className={`gallery-item ${
              index === currentPhotoIndex ? 'active' : ''
            }`}
          >
            <div className="gallery-photos-container">
              {post.photos.map((photo, photoIndex) => (
                <img
                  key={photoIndex}
                  src={`data:image/jpeg;base64,${photo}`}
                  alt={post.caption}
                  onClick={() => handleImageClick(photoIndex)}
                />
              ))}
            </div>
            <div className="gallery-caption">
              <h3>{post.caption}</h3>
              <p>{post.expression}</p>
              <div className="buttons-container">
                <Link to={`/edit/${post.id}`}>Edit</Link>
                <button onClick={() => handleDelete(post.id)}>Delete</button>
              </div>
            </div>

          </div>
        ))}
      </div>
    </>
  );
};

export default Allpost;
