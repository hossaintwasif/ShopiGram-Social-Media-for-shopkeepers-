import axios from 'axios'
import { API_BASE_URL, api } from '../../../../config/api'
import { GET_PROFILE_FAILURE, GET_PROFILE_REQUEST, GET_PROFILE_SUCCESS, LOGIN_FAILURE, LOGIN_REQUEST, LOGIN_SUCCESS, REGISTER_FAILURE, REGISTER_REQUEST, REGISTER_SUCCESS, SEARCH_USER_FAILURE, SEARCH_USER_REQUEST, SEARCH_USER_SUCCESS, UPDATE_PROFILE_FAILURE, UPDATE_PROFILE_REQUEST, UPDATE_PROFILE_SUCCESS } from './auth.actionType';

export const loginUserAction=(loginData)=>async(dispatch)=>{
    dispatch({type:LOGIN_REQUEST})
    try {
        const {data}=await axios.post(`${API_BASE_URL}/auth/signin`,loginData.data)
        console.log(data.token)
        if(data.token){
            localStorage.setItem("JWT",data.token);
            console.log("JWT token stored in local storage:", data.token);
        }

        const token = localStorage.getItem("JWT");
        
       if (token) {
            console.log("JWT token retrieved from local storage:", token);
             // Perform any actions with the token here
       } else {
            console.log("JWT token not found in local storage");
       }
      
        console.log("Login success",data);
        dispatch({type:LOGIN_SUCCESS,payload:data.token});
    } catch (error) {
        console.log("------------",error);
        dispatch({type:LOGIN_FAILURE,payload:error})
    }
}




export const registerUserAction=(registerData)=>async(dispatch)=>{
    dispatch({type:REGISTER_REQUEST})
    try {
        const {data}=await axios.post(`${API_BASE_URL}/auth/signup`,registerData.data)

        if(data.token){
            localStorage.setItem("JWT",data.token);
        }
        console.log("Register success",data);
        dispatch({type:REGISTER_SUCCESS,payload:data.token});
    } catch (error) {
        console.log("------------",error);
        dispatch({type:REGISTER_FAILURE,payload:error})
    }
}


export const getProfileAction=(token)=>async(dispatch)=>{
    dispatch({type:GET_PROFILE_REQUEST})
    try {
        const {data}=await axios.get(`${API_BASE_URL}/api/users/profile`,{headers:{'Authorization':`Bearer ${token}`,},});

        console.log("profle",data);
        dispatch({type:GET_PROFILE_SUCCESS,payload:data});

    } catch (error) {
        console.log("------------",error);
        dispatch({type:GET_PROFILE_FAILURE,payload:error})
    }
}


export const updateProfileAction=(reqData)=>async(dispatch)=>{
    dispatch({type:UPDATE_PROFILE_REQUEST})
    try {
        const {data}=await api.put(`${API_BASE_URL}/api/users`,reqData);

        console.log("update profle",data);
        dispatch({type:UPDATE_PROFILE_SUCCESS,payload:data});

    } catch (error) {
        console.log("------------",error);
        dispatch({type:UPDATE_PROFILE_FAILURE,payload:error})
    }
}

export const searchUser=(query)=>async(dispatch)=>{ 
    dispatch({type:SEARCH_USER_REQUEST})
    console.log("Query:", query);
    try {
        const {data}=await api.get(`/api/users/search?query=${query}`);

        console.log("Search User---",data);
        dispatch({type:SEARCH_USER_SUCCESS,payload:data});

    } catch (error) {
        console.log("Search Error-------",error);
        dispatch({type:SEARCH_USER_FAILURE,payload:error})
    }
}