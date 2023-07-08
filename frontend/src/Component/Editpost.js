import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';


function EditPost() {

  const { id } = useParams();
  const [caption, setCaption] = useState('');
  const [expression, setExpression] = useState('');
  const [photo, setPhoto] = useState(null);

  useEffect(() => {
    fetchPost();
  }, []);

  const fetchPost = async () => {
    try {
      const response = await axios.get(`http://localhost:8080/paf/post/${id}`);
      setCaption(response.data.caption);
      setExpression(response.data.expression);
      setPhoto(response.data.photo);
    } catch (error) {
      console.error(error);
    }
  };

  const handleFormSubmit = async (event) => {
    event.preventDefault();
    const formData = new FormData();
    formData.append('caption', caption);
    formData.append('expression', expression);
    if (photo) {
      formData.append('photo', photo);
    }
    try {
      await axios.put(`http://localhost:8080/paf/updatepost/${id}`, formData, {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      });
      // Handle success message or redirect to another page

    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className="edit-post-page">
      <h2>Edit Post</h2>
      <form onSubmit={handleFormSubmit}>
        <div className="form-group">
          <label htmlFor="caption">Caption</label>
          <input type="text" id="caption" name="caption" value={caption} onChange={(e) => setCaption(e.target.value)} required />
        </div>
        <div className="form-group">
          <label htmlFor="expression">Expression:</label>
          <textarea id="expression" name="expression" value={expression} onChange={(e) => setExpression(e.target.value)} required></textarea>
        </div>
        <div className="form-group">
          <label htmlFor="photo">Photo:</label>
          <input type="file" id="photo" name="photo" onChange={(e) => setPhoto(e.target.files[0])} required/>
        </div>
        <button type="submit">Save Changes</button>
      </form>
    </div>
  );
}

export default EditPost;