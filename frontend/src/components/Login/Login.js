
import { useForm } from 'react-hook-form';
import { useState, useContext } from 'react';
import { Link, Redirect } from 'react-router-dom';
import { AuthContext } from '../../context/AuthContext';
import { publicFetch } from '../../utils/fetch';
import './login.css';

function Login() {
    const {register, handleSubmit, formState: { errors }} = useForm();
    const [redirectOnLogin, setRedirectOnLogin] = useState(false);
    
    const [loginText, setLoginText] = useState('');

    const authContext = useContext(AuthContext);
    
    async function onClickLogin(formData) {
        const { data } = await publicFetch.post('user/auth', formData);
        if (!data) {
            setLoginText('Niepoprawny login lub hasło')
        }
        else {
            authContext.setAuthState(data);
            setRedirectOnLogin(true);
        }
    }
    
    return (
    <>
    {redirectOnLogin && <Redirect to='/'/>}
    <div className='login-container'>
        <h1>Hello!</h1>
        <h2 className='form-error'>{loginText}</h2>

        <form onSubmit={handleSubmit(onClickLogin)}>
            <input
                type='text'
                placeholder='Username'
                {...register("username", { required: true })}
            />
            {errors.username && <p>Login is required</p>}

            <input
                type='password'
                placeholder='Password'
                {...register("password", { required: true })}
            />
            {errors.password && <p>Password is required</p>}

            <button type='submit'>Submit</button>
        </form>

        <p><Link to='/register'>Create new account</Link></p>
    </div>
    </>
    );
}

export default Login;
