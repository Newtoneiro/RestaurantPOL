
import { useForm } from 'react-hook-form';
import { useState, useContext } from 'react';
import { Link, Redirect } from 'react-router-dom';
import { AuthContext } from '../../context/AuthContext';
import { publicFetch } from '../../utils/fetch';
import './login.css';

function Register() {
    const {register, handleSubmit, formState: { errors }} = useForm();

    const [redirectOnLogin, setRedirectOnLogin] = useState(false);
    const [registerText, setRegisterText] = useState('');
    const [usernameStatus, setUsernameStatus] = useState('');
    const [emailStatus, setEmailStatus] = useState('');

    const authContext = useContext(AuthContext);

    async function onClickRegister(formData) {
        const { data: availability } = await publicFetch.get('user/checkUsernameEmail', {
            params: {username: formData.username, email: formData.email} })
        if (availability.username === true && availability.email === true) {
            const { data } = await publicFetch.post('user/add', formData);
            if (data === {}) {
                setRegisterText('Nie udało się zarejestrować');
            }
            else {
                setRegisterText('');
                setUsernameStatus('');
                setEmailStatus('');
                authContext.setAuthState(data);
                setRedirectOnLogin(true);
            }
        }
        else {
            if (availability.username === false) {
                setUsernameStatus('Username is already taken')
            }
            else {
                setUsernameStatus('')
            }
            if (availability.email === false) {
                setEmailStatus('Email is already taken')
            }
            else {
                setEmailStatus('')
            }
        }
    }

    return (
    <>
        {redirectOnLogin && <Redirect to='/'/>}
        <div className='login-container'>
            <h1>Register</h1>
            <h2 className='form-error'>{registerText}</h2>

            <form onSubmit={handleSubmit(onClickRegister)}>
                <input
                    type='text'
                    placeholder='First Name'
                    {...register("firstName", { required: true })}
                />
                {errors.firstName && <p>First name is required</p>}

                <input
                    type='text'
                    placeholder='Last Name'
                    {...register("lastName", { required: true })}
                />
                {errors.lastName && <p>Last name is required</p>}

                <input
                    type='email'
                    placeholder='Email'
                    {...register("email", {
                        required: true,
                        pattern: /\S+@\S+\.\S+/
                    })}
                />
                {errors.email?.type === 'required' && <p>Email is required</p>}
                {errors.email?.type === 'pattern' && <p>This is not a valid email</p>}
                {emailStatus && <p>This email is taken</p>}

                <input
                    type='text'
                    placeholder='Username'
                    {...register("username", { required: true })}
                />
                {errors.username && <p>Login is required</p>}
                {usernameStatus && <p>This username is taken</p>}

                <input
                    type='password'
                    placeholder='Password'
                    {...register("password", {
                        required: true,
                        minLength: 8
                    })}
                />
                {errors.password?.type === 'required'  && <p>Password is required</p>}
                {errors.password?.type === 'minLength'  && <p>Password is too short</p>}

                <button type='submit'>Submit</button>
            </form>
            <p><Link to='/login'>Already have an account?</Link></p>
        </div>
    </>
    );
}

export default Register;
