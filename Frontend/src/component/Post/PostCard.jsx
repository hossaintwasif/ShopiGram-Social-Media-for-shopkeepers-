import { Avatar, Card, CardHeader, Divider, IconButton } from '@material-ui/core'
import { red } from '@material-ui/core/colors';
import React, { useState } from 'react'
import MoreVertIcon from '@mui/icons-material/MoreVert';
import { CardActions, CardContent, CardMedia, Typography } from '@mui/material';
import FavoriteIcon from '@mui/icons-material/Favorite';
import FavoriteBorderIcon from '@mui/icons-material/FavoriteBorder';
import ShareIcon from '@mui/icons-material/Share';
import MarkUnreadChatAltIcon from '@mui/icons-material/MarkUnreadChatAlt';
import TurnedInNotIcon from '@mui/icons-material/TurnedInNot';
import TurnedInIcon from '@mui/icons-material/TurnedIn';
import { useDispatch, useSelector } from 'react-redux';
import { createCommentPostAction, likePostAction } from '../../pages/Authentication/redux/Post/post.action';
import { isLikedByReqUser } from '../../utils/IsLikeByRegUser';


const PostCard = ({item}) => {
  const [showComments,setShowComments]=useState(false);
  const dispatch=useDispatch();
  const {post,auth}=useSelector(store=>store);

  const handleShowComments=()=>setShowComments(!showComments);

  const handleCreateComment=(content)=>{
    const reqData={
      postid:item.postid,
      data:{
        content
      }
    }
    dispatch(createCommentPostAction(reqData))
  }

  const handleLikePost=()=>{
    dispatch(likePostAction(item.postid))
  }

  // Ensure auth.user and item are defined before calling isLikedByReqUser
  const liked = auth.user && item ? isLikedByReqUser(auth.user.id, item) : false;

  // Check if item and item.user are defined
  const user = item?.user;
  const userName = user ? `${user.firstName} ${user.lastName}` : 'Unknown User';
  const userSubheader = user ? `@${user.firstName.toLowerCase()}_${user.lastName.toLowerCase()}` : '';


  console.log("is like: ",liked)
  return (
    <Card>

        <CardHeader 
        avatar={
          <Avatar sx={{ bgcolor: red[500] }} aria-label="recipe">
            R
          </Avatar>
        }
        action={
          <IconButton aria-label="settings">
            <MoreVertIcon />
          </IconButton>
        }

        //  title= {item.user.firstName+" "+item.user.lastName}
        title={`${item.user.firstName} ${item.user.lastName}`}
        subheader={`@${item.user.firstName ? item.user.firstName.toLowerCase() : ''}_${item.user.lastName ? item.user.lastName.toLowerCase() : ''}`}

        //subheader={`@${item.user.firstName.toLowerCase()}_${item.user.lastName.toLowerCase()}`} // Corrected method name
         />

      <CardMedia
        component="img"
        height="194"
        image={item.image}
        alt="Paella dish"
      />

     <CardContent>
        <Typography variant="body2" color="text.secondary">
          {item.caption}
        </Typography>
      </CardContent>

      <CardActions className='flex justify-between' disableSpacing>
        <div>
        <IconButton onClick={handleLikePost} >
          {isLikedByReqUser(auth.user.id,item)?<FavoriteIcon/>:<FavoriteBorderIcon/>}
        </IconButton>

        <IconButton >
          {<ShareIcon/>}
        </IconButton>

        <IconButton onClick={handleShowComments}>
          {<MarkUnreadChatAltIcon/>}
        </IconButton>

        </div>
        
        <div>
        <IconButton>
             {true?<TurnedInIcon/>:<TurnedInNotIcon/>}
        </IconButton>     
        </div>

      </CardActions>

    { showComments &&  <section>
      <div className='flex item-center space-x-5 mx-3 my-5'>
        <Avatar style={{}}/>

        <input onKeyPress={(e)=>{
          if(e.key=="Enter"){
            handleCreateComment(e.target.value)
            console.log('Enter Pressed -----> ',e.target.value)
          }
        }} className='w-full outline-none bg-transparent border border-[#3b4054] rounded-full px-5 py-2' type='text' placeholder='write your comments...!!'/>
      </div>

      <Divider/>

      <div className='mx-3 space-y-2 my-5 text-xs'>
         
          {item.comments?.map((comment)=> <div className='flex items-center space-x-5'>
            <Avatar style={{height:'2rem',width:'2rem',fontSize:'1.5rem'}}>
                {comment.user.firstName[0]}
            </Avatar>

            <p>{comment.content}</p>
          </div>)}

          </div>
     

    </section>}

    </Card>
  )
}

export default PostCard