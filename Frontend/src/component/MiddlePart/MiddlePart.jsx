import React, { useEffect, useState } from 'react'
import { Avatar, Card, IconButton } from '@material-ui/core'
import AddIcon from '@mui/icons-material/Add';
import StoryCircle from './StoryCircle';
import ImageIcon from '@mui/icons-material/Image';
import VideocamIcon from '@mui/icons-material/Videocam';
import AssignmentIcon from '@mui/icons-material/Assignment';
import PostCard from '../Post/PostCard';
import CreatePostModel from '../CreatePost/CreatePostModel';
import { useDispatch, useSelector } from 'react-redux';
import { getAllPostAction } from '../../pages/Authentication/redux/Post/post.action';
import store from '../../pages/Authentication/redux/store';


const story=[1,1,1,1,1];
//const post=[1,1,1,1,1,1,1];
const MiddlePart = () => {
  const dispatch = useDispatch();
  const {post}=useSelector(store=>store);
  const [openCreatePostModel,setOpenCreatePostModel]=useState(false);
  const handleCloseCreatePostModel=()=>setOpenCreatePostModel(false);
  
  console.log("post store",post);

  const handleOpenCreatePostModel=()=>{
    setOpenCreatePostModel(true);
    console.log("Open Post model...",openCreatePostModel) 
  }

  useEffect(()=>{
    dispatch(getAllPostAction())
  },[post.newComment])

  // const renderPosts = Array.isArray(post.posts) ? post.posts.map((item, index) => (
  //   <PostCard key={index} item={item} />
  // )) : null;

  return (
    <div className='px-20'>
       <section className='flex items-center p-5 rounded-b-md'>
        <div className='flex flex-col items-center mr-4 cursor-pointer'>
           <Avatar style={{ width: '5rem', height: '5rem' }} /*src='https://w0.peakpx.com/wallpaper/205/620/HD-wallpaper-messi-cute-messi-cute-argentina-messi-2021-messi-argentina-messi-2018-messi-argentina-thumbnail.jpg'*/> <AddIcon style={{fontSize:'3rem'}}/> </Avatar>
         <p>Add New</p>
        </div>
        {story.map((item)=>(<StoryCircle/>))}
       </section>

       <Card className='p-5 mt-5'>
        <div className='flex justify-between'>
          <Avatar />
          <input onClick={handleOpenCreatePostModel} readOnly className='outline-none w-[90%] rounded-full px-5 bg-transparent border-[#3b4054] border' type="text" />
        </div>

        <div className='flex justify-center space-x-9 mt-5'>
          <div className='flex items-center'>
             <IconButton color='primary' onClick={handleOpenCreatePostModel}>
               <ImageIcon />
             </IconButton>

             <span>Media</span>
          </div>
          
          <div className='flex items-center'>
             <IconButton color='primary' onClick={handleOpenCreatePostModel}>
               <VideocamIcon />
             </IconButton>

             <span>Video</span>
          </div>

          <div className='flex items-center'>
             <IconButton color='primary' onClick={handleOpenCreatePostModel}>
               <AssignmentIcon />
             </IconButton>

             <span>Write Article</span>
          </div>

        </div>

       </Card>

       <div className='mt-5 space-y-5'>
        {post.posts.map((item)=><PostCard item={item}/>)}
        
       </div>
     <div>
      <CreatePostModel handleClose={handleCloseCreatePostModel} open={openCreatePostModel} />
     </div>
    </div>
  );
};

export default MiddlePart