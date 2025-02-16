
import '../Styles/Signup.css'
export default function Signup()
{
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
                <label htmlFor='password' >Password</label>
                <input type='text' id='lastname' placeholder='enter your lastname' />
                <label htmlFor='confirmpassword' >Confirm Password</label>
                <input type='password' id='confirmpassword' placeholder='re-enter your password' />
                </div>
                <div className='signupcontainer'>
                <label htmlFor='email' >Email</label>
                <input type='email' id='email' placeholder='enter your mail id' />
                <button >Sign In</button>
                </div>
            </div>
        </div>
    
    </>
}