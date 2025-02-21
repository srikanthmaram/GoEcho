import { useState } from 'react'
import '../Styles/Home.css'
import Header from './Header'
import Signin from './Signin'
import Signup from './Signup'
import goecho from '../Images/goecho2.png'
export default function Home() {


    const [component, setComponent] = useState("Home")
    return <>
        <div className="Home-container">

            <div className='graphic-circle-1'></div>
            <Header setComponent={setComponent} component={component} />
            {component === "Home" &&
           
               
               
               <div className="Home-card">
                    <div className='punch'><h4>Connect. Chat. Echo.</h4></div>
                    <img src={goecho} alt='lglg' />
                    <p>Make every word count with GoEcho. Connect in real-time, build communities, and be part of the conversation that matters. Welcome to the chat that echoes your world!....</p>
                    <button className='getstarted'>Get Started</button>
                    <div className='MobileSignButton'>
                        
                        <button className={component === 'Signin' ? 'active' : 'nothing'} onClick={() => { setComponent("Signin") }}>Sign in</button>
                        <button className={component === 'Signup' ? 'active' : 'nothing'} onClick={() => { setComponent("Signup") }}>Sign up</button>
                    </div>
                    <div className="even-columns">
                        <div className='card'>
                            <p>hello</p>
                        </div>
                        <div className='card'></div>
                    </div>
                </div> 
}

            
            {component === "Signin" && <Signin />}
            {component === "Signup" && <Signup />}

            <div className='graphic-circle-5'></div>

        </div>
    </>
}