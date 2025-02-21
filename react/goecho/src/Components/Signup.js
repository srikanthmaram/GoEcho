
import { useState } from 'react'
import '../Styles/Signup.css'

import axiosInstance from './AxiosAPI';
export default function Signup()
{
    const[appUserEmail,setappUserEmail]=useState("")
    const[appUserPassword,setappUserPassword]=useState("")
    
    const handleSignup=async(e)=>{
        e.preventDefault();
        try {
            const respons=await axiosInstance.post('/login',{ "appUserEmail":appUserEmail,"appUserPassword":appUserPassword,"status":"ONLINE"});
            if(respons.status===200)
            {
                alert("User is saved !...")
            }
        } catch (error) {
            console.log("registration Failed with error"+error)
        } 
    }
    return<>
    <div className="signup-container">
            <div className="signup-card">
                <h3>Sign Up</h3>
                <div className='signupcontainer'>
                <label htmlFor='firstname' >FirstName</label>
                <input type='text' id='firstname' placeholder='enter your firstname' />
                
                <label htmlFor='lastname' >LastName</label>
                <input type='text' id='lastname' placeholder='enter your lastname' />
                </div>
                <div className='signupcontainer'>
                <label htmlFor='password'>Password</label>
                <input type='text' id='lastname' placeholder='enter your lastname' />
                <label htmlFor='confirmpassword' >Confirm Password</label>
                <input type='password' id='confirmpassword' placeholder='re-enter your password' value={appUserPassword} onChange={(event)=>{setappUserPassword(event.target.value)}}/>
                </div>
                <div className='signupcontainer'>
                <label htmlFor='email' >Email</label>
                <input type='email' id='email' placeholder='enter your mail id' value={appUserEmail} onChange={(event)=>{setappUserEmail(event.target.value)}} />
                <button onClick={handleSignup}>Sign Up</button>
                </div>
            </div>
        </div>
    
    </>
}