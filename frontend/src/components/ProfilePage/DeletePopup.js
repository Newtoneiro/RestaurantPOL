import { useContext, useState } from 'react';
import { useHistory } from 'react-router-dom';
import { AuthContext } from '../../context/AuthContext';
import { publicFetch } from '../../utils/fetch';

function DeletePopup({ setBackground, setDeleteWindow }) {
    const history = useHistory();
    const authContext = useContext(AuthContext);
    const { authState } = authContext;
    const [errorMessage, setErrorMessage] = useState('');

    async function deleteAccount() {
        const { data } = await publicFetch.delete(`user/${authState.user_id}`);
        if (!data) {
            setErrorMessage('Error deleting account');
        }
        else {
            authContext.logout();
            history.push('/login');
        }
    }

    return (
        <div className='delete-popup'>
            <p>Are you sure you want to pernamently delete this account?</p>
            <p>{errorMessage}</p>
            <div className='delete-popup-buttons'>
                <div onClick={() => {setBackground(false); setDeleteWindow(false)}} className='cancel'>Cancel</div>
                <div onClick={deleteAccount} className='delete'>Delete</div>
            </div>
        </div>
    )
}

export default DeletePopup;