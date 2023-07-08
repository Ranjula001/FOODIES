//import logo from './logo.svg';
import './App.css';
import AddPostForm from './Component/PostCreation';
import { BrowserRouter as Router, Route, Routes } from "react-router-dom"
import Allpost from './Component/Allpost';


import Editpost from './Component/Editpost';
import PostList from './Component/All';

function App() {
  return (
    <Router>

      <Routes>


        <Route path="/" element={<PostList />} />
        <Route path="/add" element={<AddPostForm />} />
        <Route path="/all" element={<Allpost />} />
        <Route path="/edit/:id" element={<Editpost />} />


      //rusiru
        <Route path = "/" element = {<AddPostForm/>} />
        <Route path = "/all" element = {<Allpost/>} />


        <Route path = "/edit/:id" element = {<Editpost/>} />
        <Route path = "/home" element = {<PostList/>} />



      </Routes>
    </Router>
  );
}

export default App;
