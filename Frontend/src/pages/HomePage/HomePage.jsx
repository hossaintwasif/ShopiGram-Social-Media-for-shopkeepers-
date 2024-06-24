import React, { useEffect } from 'react'
import { Card, Grid } from '@material-ui/core';
import Sidebar from '../../component/Sidebar/Sidebar';
import { Route, Routes, useLocation, useRouteError } from 'react-router-dom';
import MiddlePart from '../../component/MiddlePart/MiddlePart';
import Reels from '../../component/Reels/Reels';
import CreateReelsform from '../../component/Reels/CreateReelsform';
import Profile from '../../component/profile/Profile';
import HomeRight from '../../component/HomeRight/HomeRight';
import { useDispatch, useSelector } from 'react-redux';
import { getProfileAction } from '../Authentication/redux/Auth/auth.action';
import {store} from '../Authentication/redux/store';


const HomePage = () => {
  const dispatch=useDispatch()

    const location=useLocation();
    const jwt=localStorage.getItem("jwt");
    const { auth } = useSelector(store => store);
     
    console.log("Auth",auth)
    
  return (
    <div className='px-20'>
        <Grid container spacing={0}>
           <Grid item xs={0} lg={3}>
              <div className='sticky top-0'>
                  <Sidebar/>
              </div>
           </Grid>

           <Grid lg={location.pathname=="/"?6:9} item className='px-5 flex justify-center' xs={12}>
             <Routes>
                <Route path='/' element={<MiddlePart/>}/>
                <Route path='/reels' element={<Reels/>}/>
                <Route path='/create-reels' element={<CreateReelsform/>}/>
                <Route path='/profile/:id' element={<Profile/>}/>
             </Routes>
           </Grid>
            
           {location.pathname==='/' && <Grid item lg={3} className='relative'>

              <div className='sticky top-0 w-full'>

                <HomeRight/>

              </div>

           </Grid>}
           
        </Grid>
    </div>
  )
}

export default HomePage