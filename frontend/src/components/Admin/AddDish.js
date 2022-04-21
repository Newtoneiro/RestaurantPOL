
import { useForm } from 'react-hook-form';
import { RequiredInput, RequiredTextarea, RequiredFloatInput } from '../Forms/Inputs';
import { useState, useEffect } from "react";
import { publicFetch } from "../../utils/fetch";

function SelectRestaurant(props) {
    const [restaurants, setRestaurants] = useState([]);

    useEffect(() => {
        async function fetchData() {
            const { data } = await publicFetch.get('/restaurant/all/');
            setRestaurants(data)
        }
        fetchData();
    }, []);
    
    return(
        <>
        <select {...props.register('Restaurant', {validate: value => value !== ''})} className="select-cuisine">
            <option value=''>Select Restaurant</option>
            {restaurants.map(restaurant => (
                <option key={restaurant.restaurantId} value={restaurant.restaurantId}>
                    {restaurant.name} ({restaurant.city} {restaurant.street})
                </option>
            ))}
        </select>
        {props.errors.Cuisine?.type === 'validate' && <p className='form-error'>You need to choose a restaurant!</p>}
        </>
    );
}

function AddDish(props) {
    const {register, handleSubmit, formState: { errors }} = useForm();
    const [errorText, setErrorText] = useState('');


    async function onSubmit(formData) {
        var restaurantId = formData.Restaurant;
        if (props.restaurant){
            restaurantId = props.restaurant.restaurantId;
        }
        const dish = {
            name: formData.Name,
            restaurantId: restaurantId,
            price: formData.Price,
            description: formData.Description,
            imgUrl: formData['Image URL']
        }

        const { data } = await publicFetch.post('/dish/add/', dish);
        

        if (data === null) {
            setErrorText('Error adding dish');
            return;
        }
        else{
            setErrorText('Successfully added dish');
            if (props.restaurant){
                window.location.reload()
            }
        }
    }

    return (
    <div className='form-container'>
        <h1>Add new dish</h1>
        <h2 className='form-error'>{errorText}</h2>

        <form onSubmit={handleSubmit(onSubmit)}>
            <RequiredInput name='Name' register={register} errors={errors}/>
            {props.restaurant?<div className='forcedInput'>{`${props.restaurant.name} (${props.restaurant.city} ${props.restaurant.street})`}</div>:
            <SelectRestaurant register={register} errors={errors}/>
            }   
            <RequiredFloatInput type='number' name='Price' register={register} errors={errors}/>
            <RequiredTextarea name='Description' register={register} errors={errors}/>
            <RequiredInput name='Image URL' register={register} errors={errors}/>
            <button type='submit'>Add</button>
        </form>
    </div>
    
    );
}

export default AddDish;
