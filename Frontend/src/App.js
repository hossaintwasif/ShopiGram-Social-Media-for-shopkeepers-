import React from 'react';
import './App.css';
import Authentication from './pages/Authentication/Authentication';
import { Route, Routes } from 'react-router-dom';
import Message from './pages/Message/Message';
import HomePage from './pages/HomePage/HomePage';
import { useDispatch, useSelector } from 'react-redux';
import { store } from './pages/Authentication/redux/store';
import { useEffect } from 'react';
import { getProfileAction } from './pages/Authentication/redux/Auth/auth.action';

function App() {
  const dispatch=useDispatch();
  const {auth}=useSelector(store=>store);
  const jwt=localStorage.getItem("JWT");

  useEffect(()=>{
    dispatch(getProfileAction(jwt))
  },[])
  return (
      <div className="">

        <Routes>
          
          <Route path='/*' element={auth.user?<HomePage/>:<Authentication/>}/>
          <Route path='/message' element={<Message/>}/>
          <Route path='/*' element={<Authentication/>}/>

        </Routes>

         
      </div>
  );
}


export default App;
