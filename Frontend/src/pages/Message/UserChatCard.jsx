import { Avatar, Card, CardHeader, IconButton } from '@mui/material'
import React from 'react'
import MoreHorizIcon from '@mui/icons-material/MoreHoriz';
import { useSelector } from 'react-redux';
//import { store } from '../Authentication/redux/store';

const UserChatCard = ({chat}) => {
  const {message,auth}=useSelector(store=>store);

   // Log the incoming data for debugging
   console.log('auth.user:', auth.user);
   console.log('chat:', chat);
 
  if (!auth.user) {
    console.error('auth.user is null:', auth.user);
    return null;
  }

   // Ensure chat.users array exists and has at least 2 users
   if (!chat.users || chat.users.length < 2) {
     console.error('Invalid chat.users structure:', chat.users);
     return null;
   }
   //console.log(chat.users[1].firstName+" "+chat.users[1].lastName )
   //console.log(chat.users[0].firstName+" "+chat.users[0].lastName)
   const isCurrentUserFirstUser = auth.user?.id === chat.users[0].id;
   const otherUser = isCurrentUserFirstUser ? chat.users[1] : chat.users[0];
   
  return (
    <Card>
        <CardHeader avatar={
            <Avatar style={{width:'3.5rem',height:'3.5rem',fontSize:'1.5rem',backgroundColor:'#191c29',color:'rgb(88,199,250)'}} src='https://images.pexels.com/photos/1040880/pexels-photo-1040880.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1'/>
        } action={
            <IconButton>
                <MoreHorizIcon/>
            </IconButton>
        }
          title={auth.user.id===chat.users[0].id?chat.users[1].firstName+" "+chat.users[1].lastName:chat.users[0].firstName+" "+chat.users[0].lastName}
          subheader={"New Message"}
        >
        
    </CardHeader>
    </Card>
    
  )
}

export default UserChatCard