
import { useContext } from 'react';
import { AuthContext } from '../../context/AuthContext';
import { Link, useHistory } from 'react-router-dom';
import LinkButton from '../LinkButton';
import { ReactComponent as ProfilePicture } from '../../images/profile-picture.svg';
import { FiLogOut, FiHelpCircle, FiMail } from 'react-icons/fi';
import { FaPoo } from 'react-icons/fa';
import { IoSettingsOutline } from 'react-icons/io5';
import { MdQueryStats } from 'react-icons/md';
import './dropdown.css';

function Dropdown(props) {
    const history = useHistory();
    const authContext = useContext(AuthContext);
    const { authState } = authContext;

    function onLogoutButtonClick() {
        authContext.logout();
        history.push('/login');
    }

    return (
        <div className='dropdown-menu'>
            <Link onClick={props.close} to='/user-profile' className='user'>
                <ProfilePicture className='profile-picture'/>
                <div className='user-info'>
                    <h1>{authState.firstName}</h1>
                    <p>Zobacz swój profil</p>
                </div>
            </Link>
            <hr />
            <Link to='/admin' onClick={props.close} className='dropdown-item'>
                <IoSettingsOutline />
                <p>Settings</p>
            </Link>
            <div className='dropdown-item'>
                <FiMail />
                <p>Messages</p>
            </div>
            <div className='dropdown-item'>
                <MdQueryStats />
                <p>Statistics</p>
            </div>
            <div className='dropdown-item'>
                <FiHelpCircle />
                <p>Help</p>
            </div>
            <div className='dropdown-item'>
                <FaPoo />
                <p>SOI</p>
            </div>
            <LinkButton onClick={onLogoutButtonClick} to='/login'>Log Out <FiLogOut /></LinkButton>
        </div>
    )
}

export default Dropdown;