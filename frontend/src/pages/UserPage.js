
import { useContext, useEffect, useState } from 'react';
import { AuthContext } from '../context/AuthContext';
import { publicFetch } from '../utils/fetch';
import UserInfo from '../components/ProfilePage/UserInfo';
import '../components/ProfilePage/UserPage.css';

function UserPage () {
    const [userInfo, setUserInfo] = useState({
        userId: '',
        firstName: '',
        lastName: '',
        username: '',
        email: '',
        favouriteRestaurant: ''
    });

    const authContext = useContext(AuthContext);
    const { authState } = authContext;

    useEffect(() => {
        let didCancel = false;

        async function fetchData() {
            const { data } = await publicFetch.get(`user/${authState.user_id}`);
            if (!didCancel && data) {
                setUserInfo(data);
            }
        }

        fetchData();
        return () => { didCancel = true; };
    }, [authState.user_id]);

    return (
        <div className="user-page">
            <UserInfo userInfo={userInfo}/>
        </div>
    )
}

export default UserPage