import React from 'react'
import { Avatar, Box, Button, Card, CardHeader, IconButton, Tab, Tabs, dividerClasses } from '@mui/material'
import PostCard from '../Post/PostCard';
import UserReelCart from '../Reels/UserReelCart';
import { useSelector } from 'react-redux';
import ProfileModel from './ProfileModel';
import { useParams } from 'react-router-dom';

const tabs=[
  {value:"post",name:"Post"},
  {value:"reels",name:"Reels"},
  {value:"save",name:"Save"},
  {value:"repost",name:"Repost"},
];
const posts=[1,1,1,1,1,1];
const reels=[1,1,1,1,1,1];
const savePost=[1,1,1,1,1];

const Profile = () => {
  const {id}=useParams();
  const [open, setOpen] = React.useState(false);
  const handleOpenProfileModel = () => setOpen(true);
  const handleClose = () => setOpen(false);

  const { auth } = useSelector(store => store);
  const [value, setValue] = React.useState('post');

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };
  return (
    <Card className='my-10 w-[70%]'>
      <div className='rounded-md'>
        <div className='h-[15rem]'>
        <img className='w-full h-full rounded-t-md' src="https://img.freepik.com/free-vector/gradient-galaxy-background_23-2150810641.jpg?t=st=1715612630~exp=1715616230~hmac=43f6f3590398926cbe297aa5e07839c7c4e22b2f112de711d2ee51fe9f484d34&w=1380" alt="" />
        </div>
        <div className='px-5 flex justify-between items-start mt-5 h-[5rem]'>
         <Avatar className='transform -translate-y-24' style={{width:'10rem',height:'10rem'}} src='https://lh3.googleusercontent.com/a/ACg8ocKv89pMoEc4z-r1citYSk7cJTW73IsxA1en0qT8skVeY6c2kh_e=s288-c-no' />
         
         {true? <Button style={{borderRadius:'20px'}} variant='outlined' onClick={handleOpenProfileModel}>Edit Profile</Button> : <Button style={{borderRadius:'20px'}} variant='contained'>Follow</Button>}
                  
        </div>

        <div className='p-5'>
          <div>
            <h1 className='py-1 font-bold text-xl'>{auth.user?.firstName + " " + auth.user?.lastName}</h1>
            <p>@{auth.user?.firstName.toLowerCase() + "_" + auth.user?.lastName.toLowerCase()}</p>
          </div>

          <div className='flex gap-5 items-center py-3'>
             <span>41 Post</span>
             <span>35 Followers</span>
             <span>22 Followings</span>
          </div>

          <div>
            <p>Lorem ipsum dolor sit, amet consectetur adipisicing elit. Deserunt nostrum eius modi, nam at molestias rep</p>
          </div>
        </div>
          
          <section>
          <Box sx={{ width: '100%', borderBottom:1, borderColor:'divider' }}>
      <Tabs
        value={value}
        onChange={handleChange}
        aria-label="wrapped label tabs example"
      >
         {tabs.map((item)=> <Tab value={item.value} label={item.name} wrapped/>)}
       
        
      </Tabs>
          </Box>

               <div className='flex justify-center'>

               {value==="post" ? (<div className='space-y-5 w-[70%] my-10'>
                  
                  {posts.map((item)=> (<div className='borser border-slate-100 rounded-md'>
                    <PostCard/>
                  </div>))}

                </div>)
                

                :value==="reels"?<div className='flex justify-center flex-wrap gap-2 my-10'>

                  {reels.map((item)=><UserReelCart/>)}
                  handleOpen
                </div>
                
                
                :value==="save"?<div className='space-y-5 w-[70%] my-10'>
                  
                {savePost.map((item)=> (<div className='borser border-slate-100 rounded-md'>
                  <PostCard/>
                </div>))}

              </div>
              : (<div>Repost</div>)}

              </div>
          </section>
      </div>

      <section>
        <ProfileModel open={open} handleClose={handleClose} />
      </section>

    </Card>
  )
}

export default Profile