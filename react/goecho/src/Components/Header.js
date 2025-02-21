import '../Styles/Header.css'
import goecho from '../Images/goecho2.png'

export default function Header({setComponent,component}) {
    
    return <>
        <div className="Header-container">
            <img src={goecho} alt='lgo' />
           <div className="Nav-container">
                <p  className={component==='Home' ? 'activeNav' :undefined} onClick={()=>{setComponent('Home')}}>Home</p>
                <p>About</p>
                <p>Contact me</p>
            </div>  
            <div className='SignButton'>
                <button className={component==='Signin' ? 'active':'nothing'} onClick={()=>{setComponent("Signin")} }>Sign in</button>
                <button className={component==='Signup' ? 'active':'nothing'} onClick={()=>{setComponent("Signup")}}>Sign up</button>
            </div>
            
        </div>
    </>
}