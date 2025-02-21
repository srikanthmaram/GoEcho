import '../Styles/Sidebar.css'
import goecho_logo from '../Images/goecho2.png'
import community from '../Images/community.png'
import userlg from '../Images/user.png'
import chat from '../Images/chat.png'
import setting from '../Images/settings.png'
import { useEffect, useState } from 'react'
import axiosInstance from './AxiosAPI'
import webSocketService from './WebSocketService';


export default function Sidebar({onSelectUser}) {
    const [users, setUsers] = useState([]);
    const[ActiveUser,setActiveUser]=useState(null)


    useEffect(() => {

        //function to fetch the active users
        const fetchActiveUsers = async () => {
            const token = localStorage.getItem('jwtToken');
            const LoggedInUser = localStorage.getItem('user');
            try {
                const response = await axiosInstance.get('/appusers', {
                    headers: {
                        Authorization: `Bearer ${token}`,
                    },
                });
                const updatedUser = response.data.filter(user => user.username !== LoggedInUser);
                setUsers(updatedUser);
            }
            catch (err) {
                console.log("error while fetching the active users" + err)
            }
        }

        fetchActiveUsers();
    }, []);


    useEffect(() => {
        const LoggedInUser = localStorage.getItem('user');
        webSocketService.subscribe('/topic/activeusers', (message) => {
            const updatedUser = message.filter(user => user.username !== LoggedInUser);
            setUsers(updatedUser)
        })
    }, [])


    return <>
        <div className={`Sidebar-container ${ ActiveUser ? "hide":""}`}>
            <div className="App-header">
                <img src={goecho_logo} alt='logo' />
                <div className="searhbar">

                    <input type='text' placeholder='Find conversation' />
                </div>
            </div>
            <div className='Mainbar'>
                <div className="minisidebar">
                    <img className='chaticon' src={chat} alt='chat' />
                    <img src={setting} alt='img' />
                </div>
                <div className='Connectedusers'>
                    <div className='app-community'>
                        <img src={community} alt='logo' />
                        <p>App Community</p>
                    </div>
                    {
                        users.map((user, index) => (
                            <div className={`usercontainer ${ActiveUser===user.username ?'active':''}`} key={index} onClick={()=>{onSelectUser(user.username);setActiveUser(user.username)}}>
                                <img src={userlg} alt="user" />
                                <p>{user.username}</p>
                                <span>online</span>
                            </div>
                        ))
                    }

                  


                </div>
            </div>
        </div>
    </>
}