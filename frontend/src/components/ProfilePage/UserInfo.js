import { useState } from 'react';
import { ReactComponent as ProfilePicture } from '../../images/profile-picture.svg';
import DeletePopup from './DeletePopup';
import EditPopup from './EditPopup';
import './UserInfo.css';

function UserInfo({ userInfo }) {
    const [background, setBackground] = useState(false);
    const [deleteWindow, setDeleteWindow] = useState(false);
    const [editWindow, setEditWindow] = useState(false);

    function showDeletePopup() {
        setBackground(true);
        setDeleteWindow(!deleteWindow);
    }

    function showEditPopup() {
        setBackground(true);
        setEditWindow(!deleteWindow);
    }

    return (
        <>
        {background && <div className='background' onClick={() => {setBackground(false); setDeleteWindow(false); setEditWindow(false)}}></div>}
        {deleteWindow && <DeletePopup setBackground={setBackground} setDeleteWindow={setDeleteWindow}/>}
        {editWindow && <EditPopup setBackground={setBackground} setEditWindow={setEditWindow} userInfo={userInfo}/>}
        <div className="user-page-info">
            <ProfilePicture />
            <h1>{userInfo.firstName} {userInfo.lastName}</h1>
            <h2>@{userInfo.username}</h2>
            <h3>{userInfo.email}</h3>
        </div>
        <div onClick={showDeletePopup} className="button-account delete">Delete account</div>
        <div onClick={showEditPopup} className="button-account edit">Edit account</div>
        </>
    );
}

export default UserInfo;