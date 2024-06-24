import axios from "axios";

export const API_BASE_URL="http://localhost:9090";

const jwtToken=localStorage.getItem("JWT")

export const api=axios.create({baseURL:API_BASE_URL,
                               headers:{"Authorization":`Bearer ${jwtToken}`,
                             "Content-Type":"application/json"}})