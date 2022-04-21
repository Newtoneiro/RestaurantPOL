import { useContext, useState, useEffect } from 'react';
import { AuthContext } from '../../context/AuthContext';
import { publicFetch } from '../../utils/fetch';
import { useForm } from 'react-hook-form';
import { Input, RequiredInput } from '../Forms/Inputs';

function SelectRestaurant({ register, errors, currentRestaurantId }) {
    const [restaurants, setRestaurants] = useState([]);
    const [selectedRestaurant, setSelectedRestaurant] = useState(currentRestaurantId);

    useEffect(() => {
        async function fetchData() {
            const { data } = await publicFetch.get('/restaurant/all/');
            setRestaurants(data)
        }
        fetchData();
    }, []);
    
    return(
        <>
        <select
            {...register('Restaurant', {validate: value => value !== ''})}
            className="select-cuisine"
            value={selectedRestaurant}
            onChange={(e)=>setSelectedRestaurant(e.target.value)}>
            <option value=''>Select Restaurant</option>
            {restaurants.map(restaurant => (
                <option key={restaurant.restaurantId} value={restaurant.restaurantId}>
                    {restaurant.name} ({restaurant.city} {restaurant.street})
                </option>
            ))}
        </select>
        {errors.Cuisine?.type === 'validate' && <p className='form-error'>You need to choose a restaurant!</p>}
        </>
    );
}

function EditPopup({ setBackground, setEditWindow, userInfo }) {
    const {register, handleSubmit, formState: { errors }} = useForm();
    const authContext = useContext(AuthContext);
    const { authState } = authContext;
    const [errorMessage, setErrorMessage] = useState('');

    function checkIfChanges(formData) {
        if ((formData['First Name'] !== userInfo.firstName ||
            formData['Last Name'] !== userInfo.lastName ||
            formData['Username'] !== userInfo.username ||
            formData['Email'] !== userInfo.email ||
            formData['Favourite Restaurant'] !== userInfo.favouriteRestaurant) ||
            (formData['Old Password'] !== '' ||
            formData['New Password'] !== '')) {
            return true;
        }
        return false;
    }

    async function editAccount(formData) {
        if (checkIfChanges(formData)) {
            if (formData['Old Password'] !== '' && formData['New Password'] === '') {
                setErrorMessage('You need to enter a new password!');
                return;
            }
            if (formData['Old Password'] === '' && formData['New Password'] !== '') {
                setErrorMessage('You need to enter your old password!');
                return;
            }
            const editData = {
                userId: authState.user_id,
                firstName: formData['First Name'],
                lastName: formData['Last Name'],
                username: formData['Username'],
                email: formData['Email'],
                favouriteRestaurant: formData['Restaurant'],
                password: formData['Old Password'],
                newPassword: formData['New Password']
            }
            console.log(editData);
            const { data } = await publicFetch.put(`user/edit`, editData)
            if (data === null) {
                setErrorMessage('Error editing account');
            }
            else if (data === false) {
                setErrorMessage('Incorrect old password');
            }
            else {
                setErrorMessage('Your account has been edited');
                const newAuthInfo = authState;
                newAuthInfo.firstName = editData.firstName;
                authContext.setAuthState(newAuthInfo);
            }
        }
    }

    return (
        <div className='delete-popup'>
            <form className='form-container' onSubmit={handleSubmit(editAccount)}>
                <h1>Edit your account</h1>
                <p>{errorMessage}</p>
                <RequiredInput name='First Name' defaultValue={userInfo.firstName} register={register} errors={errors}/>
                <RequiredInput name='Last Name' defaultValue={userInfo.lastName} register={register} errors={errors}/>
                <RequiredInput name='Username' defaultValue={userInfo.username} register={register} errors={errors}/>
                <RequiredInput name='Email' defaultValue={userInfo.email} register={register} errors={errors}/>
                <SelectRestaurant register={register} errors={errors} currentRestaurantId={userInfo.favouriteRestaurant}/>
                {/* <Input name='Favourite Restaurant' defaultValue={userInfo.favouriteRestaurant} register={register} errors={errors}/> */}
                <Input name='Old Password' register={register} errors={errors}/>
                <Input name='New Password' register={register} errors={errors}/>
            </form>

            <div className='delete-popup-buttons'>
                <div onClick={() => {setBackground(false); setEditWindow(false)}} className='cancel'>Cancel</div>
                <div onClick={handleSubmit(editAccount)} className='delete'>Edit</div>
            </div>
        </div>
    )
}

export default EditPopup;
