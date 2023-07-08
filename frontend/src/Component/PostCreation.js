import React, { useState } from 'react';
import axios from 'axios';
import './PostStyle/PostCreation.css';
import { useNavigate } from "react-router-dom";



const AddPostForm = () => {


  const navigate = useNavigate();

  const [caption, setCaption] = useState('');
  const [expression, setExpression] = useState('');
  const [photo, setPhoto] = useState(null);
  const [error, setError] = useState('');

  const userid=2; 


  const handleCaptionChange = (e) => {
    setCaption(e.target.value);
  };

  const handleExpressionChange = (e) => {
    setExpression(e.target.value);
  };

  const handlePhotoChange = (e) => {

    setPhoto(e.target.files[0]);

    setPhoto(e.target.files);

  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const formData = new FormData();
    formData.append('caption', caption);
    formData.append('expression', expression);

    formData.append('photo', photo);
    try {
      const res = await axios.post('http://localhost:8080/paf/addpost', formData);

    for (let i = 0; i < photo.length; i++) {
        formData.append('photo', photo[i]);
    }
    try {
      const res = await axios.post('http://localhost:8080/paf/addpost/2', formData);

      console.log(res.data);

      navigate("/all");

    } catch (err) {
      if (err.response) {
        setError(err.response.data.message);
      } else if (err.request) {
        setError('Network Error');
      } else {
        setError(err.message);
      }
      console.error(err);
    }
  };

  return (
  
    <div className="form-container">
    <div>
     
      <form onSubmit={handleSubmit}>
      <h2>Create a New Post</h2>
        {error && <div className="error">{error}</div>}
        <div>
          <label htmlFor="caption">Caption:</label>
          <input type="text" id="caption" name="caption" value={caption} onChange={handleCaptionChange} />
        </div>
        <div>
          <label htmlFor="expression">Expression:</label>
          <input type="text" id="expression" name="expression" value={expression} onChange={handleExpressionChange} />
        </div>
        <div>
          <label htmlFor="photo">Photo:</label>

          <input type="file" id="photo" name="photo" onChange={handlePhotoChange} />

          <input type="file" id="photo" accept='image/*' name="photo" multiple onChange={handlePhotoChange} />

        </div>
        <button type="submit">Post</button>
      </form>
    </div>
    </div>
  
  );
};

export default AddPostForm;
