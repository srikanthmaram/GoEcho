import '../Styles/Chatbox.css'
import profile from '../Images/profile.png'
import send from '../Images/send.png'
import React, { useState, useRef, useEffect } from 'react';
import webSocketService from './WebSocketService';

export default function Chatbox({ selectedUser }) {

        const [currentDateTime, setCurrentDateTime] = useState(new Date());
        const formattedDate = currentDateTime.toLocaleDateString();
        const formattedTime = currentDateTime.toLocaleTimeString();
        const chatboxRef = useRef(null);
        const [message, setMessage] = useState('');
        const [MyQueue, updateMyQueue] = useState([]);
        const LoggedInUser = localStorage.getItem('user');

        useEffect(() => {
                if (selectedUser != null) {
                        const chatbox = chatboxRef.current;
                        chatbox.scrollTop = chatbox.scrollHeight;
                }

        }, [selectedUser])
        useEffect(() => {
                if (selectedUser) {
                        updateMyQueue([])
                        setMessage('')

                        const subqueue = '/user/queue/messages/' + selectedUser
                        webSocketService.subscribe(subqueue, (message) => {

                                updateMyQueue((prevMessages) => [...prevMessages, message]);
                        });

                }
        }, [selectedUser]);

        function handleSendMessage() {


                if (selectedUser && message) {

                        webSocketService.sendMessage('/app/chat', { senderId: LoggedInUser, receiverId: selectedUser, content: message, timestamp: Date.now() });
                        updateMyQueue((prevMessages) => [...prevMessages, { senderId: LoggedInUser, receiverId: selectedUser, content: message, timestamp: Date.now() }]);
                        setMessage('');
                }
        }


        const handleInputChange = (event) => {
                setMessage(event.target.value);

        };

        return <>
                <div className={`Chatbox-container ${selectedUser ? "":"hide"}`}>
                        {selectedUser == null && (<>
                                <div className="Messae-container">
                                        <div className="mymessages">
                                                <p>Please Select A User or Community to Chat with ......</p>
                                        </div>
                                </div>
                        </>)

                        }
                        {selectedUser != null && (<>

                                <div className="chatbox-header">
                                        <img src={profile} alt='profile' />
                                        <h4>{selectedUser}</h4>
                                        <div className='dots-menu'>
                                                <div className='dot'></div>
                                                <div className='dot'></div>
                                                <div className='dot'></div>
                                        </div>
                                </div>





                                <div className="chatbox" ref={chatboxRef}>
                                        {
                                                MyQueue.map((item, index) => {
                                                        return (
                                                                <React.Fragment key={index}>
                                                                        {item.senderId === LoggedInUser && (
                                                                                <div className="Message-container">
                                                                                        <div className='profilestamp'>
                                                                                                <div className='Namestamp'>
                                                                                                        <h4>{item.senderId}</h4>
                                                                                                        <p>{formattedDate + formattedTime}</p>
                                                                                                </div>
                                                                                                <img src={profile} alt='logo' />
                                                                                        </div>
                                                                                        <div className="mymessages">
                                                                                                <p>{item.content}</p>
                                                                                        </div>
                                                                                </div>
                                                                        )}

                                                                        {item.senderId !== LoggedInUser && (
                                                                                <div className="Mymessage-container">
                                                                                        <div className='myprofilestamp'>
                                                                                                <img src={profile} alt='logo' />
                                                                                                <div className='mynamestamp'>
                                                                                                        <h4>{item.senderId}</h4>
                                                                                                        <p>{formattedDate + formattedTime}</p>
                                                                                                </div>
                                                                                        </div>
                                                                                        <div className="receivedmessages">
                                                                                                <p>{item.content}</p>
                                                                                        </div>
                                                                                </div>
                                                                        )}
                                                                </React.Fragment>
                                                        );
                                                })
                                        }




                                </div>

                                <div className="inputbox">
                                        <input type='text' value={message} onChange={handleInputChange} placeholder='Type a message' />
                                        <img src={send} alt='send' onClick={handleSendMessage} />
                                </div>
                        </>)}
                </div>
        </>
}