import React, { useEffect, useState } from 'react'
import { Card, Grid } from '@material-ui/core';
import WestIcon from '@mui/icons-material/West';
import { Avatar, CircularProgress, IconButton } from '@mui/material';
import AddIcCallIcon from '@mui/icons-material/AddIcCall';
import DuoIcon from '@mui/icons-material/Duo';
import AddPhotoAlternateIcon from '@mui/icons-material/AddPhotoAlternate';
import UserChatCard from './UserChatCard';
import ChatMessage from './ChatMessage';
import SearchUser from '../../component/SearchUser/SearchUser';
import { useDispatch, useSelector } from 'react-redux';
import { createMessage, getAllChat } from '../Authentication/redux/Message/message.action';
import ChatBubbleOutlineIcon from '@mui/icons-material/ChatBubbleOutline';
import { uploadToCloudinary } from '../../utils/uploadToCloudniry';
import Backdrop from '@mui/material/Backdrop';
import SockJS from 'sockjs-client';
import Stomp from 'stompjs';
// import "./Message.css"


const Message = () => {

  const dispatch=useDispatch();
  const {message,auth}=useSelector(store=>store);
  const [currentChat,setCurrentChat]=useState();
  const [messages,setMessages]=useState([]);
  const [selectedImage,setSelectedImage]=useState();
  const [loading,setLoading]=useState(false);
  const [isConnected, setIsConnected] = useState(false);

  useEffect(()=>{
    dispatch(getAllChat())
  },[dispatch])

  console.log("chats----",message.chats)

  const handleSelectImage=async(e)=>{
    setLoading(true)
    console.log("handle select image..")
    const imgUrl=await uploadToCloudinary(e.target.files[0],"image")
    setSelectedImage(imgUrl)
    setLoading(false)
  }

  const handleCreateMessage=(value)=>{
    
    const message={
      //chatId:currentChat?.id,
      chatId:currentChat?.chatId,
      content:value,
      image:selectedImage
    };
    dispatch(createMessage({message,sendMessageToServer}))
  };

  useEffect(()=>{
    setMessages([...messages,message.message])
  },[message.message])

  const [stompClient,setStompClient]=useState(null);

  useEffect(()=>{
    const sock=new SockJS("http://localhost:9090/ws");
    const stomp=Stomp.over(sock);
    setStompClient(stomp);

    stomp.connect({},onConnect,onErr)
  },[])

  const onConnect=()=>{
    console.log("WebSocket Connect...!!");
    setIsConnected(true);
  }

  const onErr=(error)=>{
    console.log("Error ",error);
    setIsConnected(false);
  }

 

  useEffect(()=>{
    if(stompClient && auth.user && currentChat){
      console.log("The auth user is ",auth.user);
      console.log("The stompclient is ",stompClient);
      console.log("The currentChat is ",currentChat);
      const subscription=stompClient.subscribe(`/user/${currentChat?.chatId}/private `,onMessagereceive);
      console.log("Subscribed: ", subscription);
    }
  });

  const sendMessageToServer=(newmessage)=>{
    // if(stompClient && newmessage && isConnected){
      if(stompClient && newmessage ){
      stompClient.send(`/app/chat/${currentChat?.chatId.toString()}`,{},JSON.stringify(message))
      console.log("the message is: ",message);
      console.log("the new message is: ",newmessage);
    }else {
      console.error('Unable to send message: WebSocket connection not established.');
    }
  }
  


  const onMessagereceive=(payload)=>{
    const receivedmessage=JSON.parse(payload.body)
    console.log("Message receive from WebSocket ",receivedmessage);
    setMessages([...messages,receivedmessage])
}

  return (
    <div>
      <Grid container className='h-screen overflow-y-hidden'>

        {/* Left side user name  */}
        <Grid className='px-5' item xs={3}>
          <div className='flex h-full justify-between space-x-2'>
            <div className='w-full'>

            <div className='flex space-x-4 items-center py-5'>
                <WestIcon/>
                 <h1 className='text-xl font-bold'> Home </h1>
              </div>

              <div className='h-[83vh]'>
                <div className=''>
                <SearchUser/>
                </div>  

                <div className='h-full space-y-4 overflow-y-scroll hideScrollbar'>

                  {
                    message.chats.map((item)=> {
                        return <div onClick={()=>{
                        setCurrentChat(item)
                        setMessages(item.messages)
                        }}>
                        <UserChatCard chat={item}/>
                      </div>
                    
                  })
                  }
                     
                </div>

              </div>
            </div>
             
          </div>
        </Grid>

         {/* Right side user chatting  */}
        <Grid className='h-full' item xs={9}>
          {currentChat ? <div>
            <div className='flex justify-between items-center border-1 p-5'>

              <div className='flex items-center space-x-3'>

                <Avatar src='https://images.pexels.com/photos/160414/female-portrait-studio-attractive-160414.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1'/>
                <p>{auth.user?.id===currentChat.users[0]?.id?currentChat.users[1].firstName+" "+currentChat.users[1].lastName:currentChat.users[0].firstName+" "+currentChat.users[0].lastName}</p>

              </div>

              <div className='flex space-x-3'> 
               <IconButton>
                <AddIcCallIcon/>
                
               </IconButton>

               <IconButton>
               <DuoIcon/>
               </IconButton>

              </div>

            </div>

            <div className='hideScrollBar overflow-y-scroll h-[82vh] px-2 space-y-5 py-5'>
              {
              messages.map((item)=><ChatMessage messageing={item}/> )
              }
              
            </div>
            <div className='sticky bottom-0 border-l'>
            {selectedImage && <img className='w-[5rem] h-[5rem] object-cover px-2' src={selectedImage} alt="" />}
            <div className='py-5 flex items-center justify-center space-x-5'> 

            
            <input onKeyPress={(e)=>{
              if(e.key==="Enter" && e.target.value){
                handleCreateMessage(e.target.value)
                setSelectedImage("")
              }
            }} 
            className='bg-transparent border border-[#3b4054] rounded-full w-[90%] py-3 px-5' placeholder='Enter message...!!' type="text" 
            />

             <div>

              <input type="file" accept='image/*' onChange={handleSelectImage} className='hidden' id='image-input'/>
              <label htmlFor="image-input">
                <AddPhotoAlternateIcon/>
              </label>
             </div>
            </div>

          </div>
          </div>:<div className='h-full space-y-5 flex flex-col justify-center items-center'>
               <ChatBubbleOutlineIcon style={{fontSize:"15rem"}}/>
               <p className='text-x1 font-semibold'>No Chat Selected</p>
            </div>}

          
        </Grid>
      </Grid>

      <Backdrop
        sx={{ color: '#fff', zIndex: (theme) => theme.zIndex.drawer + 1 }}
        open={loading}
      >
        <CircularProgress color="inherit" />
      </Backdrop>
    </div>
  )
}

export default Message


