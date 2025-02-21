import '../Styles/Echochat.css'
import Sidebar from './Sidebar'
import Chatbox from './Chatbox'
import { useState } from 'react';

export default function  Echochat()
{
    const [selectedUser,setSelectedUser]=useState(null);
    
    function handleSelectedUser(username)
  {
    setSelectedUser(username);
    

  }
    return<>
    <div className="Echo-container">
      
      <Sidebar onSelectUser={handleSelectedUser}/>
      
      
    <Chatbox selectedUser={selectedUser}/>
    
    </div>
    </>
}