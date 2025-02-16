import { useState } from 'react';
import '../Styles/Signin.css'
import axiosInstance from './AxiosAPI';
import webSocketService from './WebSocketService';
import { useNavigate } from 'react-router-dom';

export default function Signin() {

    const[Username,setUsername]=useState("")
    const[Password,setPassword]=useState("")
    const Navigate =useNavigate();

const handleLogin=async (e)=>{
    e.preventDefault(); 
    try{
    const respons=await axiosInstance.post('/login',{ "appUserEmail":Username,"appUserPassword":Password,"status":"ONLINE"});

    if(respons.status===200)
        {
        //storing JWT token and username
        const jwtToken=respons.data
        localStorage.setItem('jwtToken',jwtToken)
        localStorage.setItem('user',Username)

        //creating a websocket connection
        webSocketService.connect(
            function onConnect(){
                console.log("websocket connection successfully established, now redirecting user to echochat");
                Navigate("echochat")
            },
            function onError(){
                console.log("something wrong with websocket connection")
            }
        );
        
    }
    }
    catch(err){
  console.log("Login Failed with error"+err)
    }

};

    return <>
        <div className="signin-container">
            <div className="sigin-card">
                <h3>Sign In</h3>
                <label htmlFor='useranme' >Username</label>
                <input type='text' value={Username} onChange={(event)=>{setUsername(event.target.value)}} id='username' placeholder='enter your username' />
                <label htmlFor='password' >Password</label>
                <input type='password' value={Password} onChange={(event)=>{setPassword(event.target.value)}} id='password' placeholder='enter your password' />
                <button onClick={handleLogin}>Sign In</button>
            </div>
        </div>
    </>
}