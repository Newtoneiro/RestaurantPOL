import React, { createContext, useState } from 'react';

const AuthContext = createContext();
const { Provider } = AuthContext;

const AuthProvider = ({ children }) => {
    const userInfo = localStorage.getItem('userInfo');

    const [authState, setAuthState] = useState(userInfo ? JSON.parse(userInfo) : {})

    function setAuthInfo(userInfo) {
        localStorage.setItem('userInfo', JSON.stringify(userInfo));
        setAuthState(userInfo);
    }

    function logout() {
        localStorage.removeItem('userInfo');
        setAuthState({});
    }

    function isAuthenticated() {
        if (authState && Object.keys(authState).length === 0
            && Object.getPrototypeOf(authState) === Object.prototype) {
            return false;
        }
        return true;
    }

    function isAdmin() {
        return authState.admin;
    }

    return (
        <Provider
            value={{
                authState,
                setAuthState: authInfo => setAuthInfo(authInfo),
                isAuthenticated,
                isAdmin,
                logout
            }}
        >
        { children }
        </Provider>
    )
}

export { AuthContext, AuthProvider };