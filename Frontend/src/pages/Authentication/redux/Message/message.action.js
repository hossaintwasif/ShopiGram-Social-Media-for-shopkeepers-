import { api } from "../../../../config/api"
import { CREATE_MESSAGE_REQUEST } from "./message.actionType"
import * as actionType from "./message.actionType"

export const createMessage=(reqData)=>async(dispatch)=>{
    console.log("reqData ",reqData);
    console.log("reqData message",reqData.message);
    dispatch({type:actionType.CREATE_MESSAGE_REQUEST})
    try {
        const {data}=await api.post(`/api/message/chat/${reqData.message.chatId}`,reqData.message);

        reqData.sendMessageToServer(data)
        console.log("create message",data)
        dispatch({type:actionType.CREATE_MESSAGE_SUCCESS,payload:data})
    } catch (error) {
        console.log("Message error",error)
        dispatch({type:actionType.CREATE_MESSAGE_FAILURE,payload:error})
    }
}

export const createChat=(chat)=>async(dispatch)=>{
    dispatch({type:actionType.CREATE_CHAT_REQUEST})
    try {
        const {data}=await api.post(`/api/chats`,chat);
        console.log("create chat",data)
        dispatch({type:actionType.CREATE_CHAT_SUCCESS,payload:data})
    } catch (error) {
        console.log("Chat error",error)
        dispatch({type:actionType.CREATE_CHAT_FAILURE,payload:error})
    }
}

export const getAllChat=()=>async(dispatch)=>{
    dispatch({type:actionType.GET_ALL_CHATS_REQUEST})
    try {
        const {data}=await api.get(`/api/chats`);
        console.log("Get all chat",data)
        dispatch({type:actionType.GET_ALL_CHATS_SUCCESS,payload:data})
    } catch (error) {
        console.log("Get all chat error",error)
        dispatch({type:actionType.GET_ALL_CHATS_FAILURE,payload:error})
    }
}