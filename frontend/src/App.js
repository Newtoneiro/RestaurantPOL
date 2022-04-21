
import Login from './components/Login/Login';
import Home from './components/Home';
import Admin from './pages/Admin';
import Register from './components/Login/Register';
import {
    BrowserRouter as Router,
    Switch,
    Route,
    Redirect
} from 'react-router-dom';
import {
    AuthContext,
    AuthProvider
} from './context/AuthContext';
import { useContext } from 'react';
import Navbar from './components/Navbar/Navbar';

function NotAuthenticatedRoute({ children, ...rest }) {
    const authContext = useContext(AuthContext);
    const { isAuthenticated } = authContext;
    return (
        <Route {...rest} render={() =>
            isAuthenticated() ? <Redirect to='/' /> : children
        }/>
    )
}

function AuthenticatedRoute({ children, ...rest }) {
    const authContext = useContext(AuthContext);
    const { isAuthenticated } = authContext;
    return (
        <Route {...rest} render={() =>
            isAuthenticated() ? <><Navbar />{children}</> : <Redirect to='/login' />
        }/>
    )
}

function AdminRoute({ children, ...rest }) {
    const authContext = useContext(AuthContext);
    const { isAdmin, isAuthenticated } = authContext; 
    return (
        <Route {...rest} render={() =>
            isAdmin() ? <><Navbar />{children}</> : isAuthenticated() ? <Redirect to='/' /> : <Redirect to='/login' />
        }/>
    )
}

function Routes() {
    return (
    <Switch>
        <NotAuthenticatedRoute path='/login'>
            <Login />
        </NotAuthenticatedRoute>
        <NotAuthenticatedRoute path='/register'>
            <Register />
        </NotAuthenticatedRoute>
        <AdminRoute path='/admin'>
            <Admin />
        </AdminRoute>
        <AuthenticatedRoute path='/'>
            <Home />
        </AuthenticatedRoute>
    </Switch>
    )
}

function App() {    
    return (
        <Router>
        <AuthProvider>
            <Routes />
        </AuthProvider>
        </Router>
    );
}

export default App;
