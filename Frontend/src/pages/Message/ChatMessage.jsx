import React from 'react'
import { useSelector } from 'react-redux';

const ChatMessage = ({messageing}) => {
  const {message,auth}=useSelector(store=>store);
  const isReqUserMessage=auth.user?.id===messageing.user?.id
  return (
    <div className={`flex ${isReqUserMessage?"justify-start":"justify-end"} text-white`}>
        <div className={`p-1 ${messageing.image?"rounded-md":"px-5 rounded-full"} bg-[#191c29]`}>
            {messageing.image && <img className='w-[12rem] h-[17rem] object-cover rounded-md' src={messageing.image}/>}
            <p className={`${true?"py-2":"py-1"}`}>{messageing.content}</p>

        </div>
    </div>
  )
}

export default ChatMessage