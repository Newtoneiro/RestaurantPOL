
import { Switch, Route, useRouteMatch } from 'react-router-dom';
import { useContext } from 'react';
import { AuthContext } from '../context/AuthContext';
import Rate from './Rate-us/Rate-us';
import About from './About';
import UserPage from '../pages/UserPage';
import Restaurants from './Restaurants/Restaurants';

import { FaHamburger, FaPizzaSlice, FaIceCream, FaCoffee } from 'react-icons/fa';

import './home.css'
import PageNotFound from './PageNotFound/PageNotFound';

function Home() {
    let match = useRouteMatch();

    const authContext = useContext(AuthContext);
    const { authState } = authContext;
    
    return (
    <div className='main-div'>
        <Switch>
            <Route path={`${match.path}restaurants`}>
                <Restaurants />
            </Route>
            <Route path={`${match.path}rate-us`}>
                <Rate />
            </Route>
            <Route path={`${match.path}about`}>
                <About />
            </Route>
            <Route path={`${match.path}user-profile`}>
                <UserPage />
            </Route>
            <Route path={match.path} exact>
            <div className='home-page'>
                <div className='logo'>
                    <h2>Restaurantpol</h2>
                    <div className='cube-wrapper'>
                        <div className='cube'>
                            <div className='cube-side back'><FaHamburger></FaHamburger></div>
                            <div className='cube-side top'><FaPizzaSlice></FaPizzaSlice></div>
                            <div className='cube-side front'><FaIceCream></FaIceCream></div>
                            <div className='cube-side bottom'><FaCoffee></FaCoffee></div>
                        </div>
                    </div>
                </div>
                <div className='footer'></div>
                <div className='greeting'>
                    <h1>Hello <span className='username'>{authState.firstName}</span>!</h1>
                    {authContext.isAdmin()?<p>(You are logged in as admin)</p>:
                    <p>How may we help?</p>}
                </div>
            </div>
            </Route>
            <Route>
                <PageNotFound/>
            </Route>
        </Switch>
    </div>
    );
}

export default Home;
