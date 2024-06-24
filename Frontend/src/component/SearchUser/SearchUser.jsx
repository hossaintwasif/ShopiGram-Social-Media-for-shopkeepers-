import { Avatar, Card, CardHeader } from '@mui/material'
import React from 'react'
import { useState } from 'react'
import { useDispatch, useSelector } from 'react-redux';
import { searchUser } from '../../pages/Authentication/redux/Auth/auth.action';
import { createChat } from '../../pages/Authentication/redux/Message/message.action';


const SearchUser = () => {
  const [username,setUsername]=useState("");
  const dispatch = useDispatch();
  const {message,auth}=useSelector(store=>store);

  
    const handleSearchUser=(e)=>{
        setUsername(e.target.value)
        console.log('Search User....-----',auth.searchUser);
        dispatch(searchUser(username))

    // const value = e.target.value;
    // setUsername(value);
    // console.log('Search User....', auth.searchUser);
    // if (value.trim()) {
    //   dispatch(searchUser(username));
    // }
    };

    const handleClick=(id)=>{
        dispatch(createChat({userId:id}))
    }
  return (
    <div>

        <div className='py-5 relative'>
             <input className='bg-transparent border border-[#3b4054] outline-none w-full px-5 py-3 rounded-full' placeholder='Search User...' onChange={handleSearchUser} type="text" />
        
             
        {
            username && (
              auth.searchUser.map((item)=> <Card key={item.id  } className='absolute w-full z-10 top-[4.5rem] cursor-pointer'>
              <CardHeader onClick={()=>{
               handleClick(item.id);
               setUsername("")
              }}
              avatar={<Avatar src='https://images.pexels.com/photos/1043471/pexels-photo-1043471.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1'/> }
               title={item.firstName +" "+ item.lastName}
               subheader={item.firstName.toLowerCase()+" "+item.lastName.toLowerCase()}
             />
           </Card>)
        )}
        </div>


    </div>
  )
}

export default SearchUser