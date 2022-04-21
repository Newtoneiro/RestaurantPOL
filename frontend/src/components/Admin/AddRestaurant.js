
import { useForm } from 'react-hook-form';
import { RequiredInput, RequiredTextarea } from '../Forms/Inputs';
import { useState, useEffect } from "react";
import { publicFetch } from "../../utils/fetch";

function SelectCuisine(props) {
    const [cuisines, setCuisines] = useState([]);

    useEffect(() => {
        async function fetchData() {
            const { data } = await publicFetch.get('/cuisine/all/');
            setCuisines(data)
        }
        fetchData();
    }, []);
    
    return(
        <>
        <select {...props.register('Cuisine', {validate: value => value !== ''})} className="select-cuisine">
            <option value=''>Select Cuisine</option>
            {cuisines.map(cuisine => <option key={cuisine.cuisineId} value={cuisine.cuisineId}>{cuisine.name}</option>)}
        </select>
        {props.errors.Cuisine?.type === 'validate' && <p className='form-error'>You need to choose a cuisine!</p>}
        </>
    );
}

function AddRestaurant() {
    const {register, handleSubmit, formState: { errors }} = useForm();
    const [errorText, setErrorText] = useState('');


    async function onSubmit(formData) {
        const restaurant = {
            name: formData.Name,
            cuisineId: formData.Cuisine,
            description: formData.Description,
            imgUrl: formData['Image URL'],
            street: formData.Street,
            city: formData.City,
            postCode: formData['Post Code'],
            country: formData.Country,
            rating : 5,
        }

        const { data } = await publicFetch.post('/restaurant/add/', restaurant);
        

        if (data === null) {
            setErrorText('Error adding restaurant');
            return;
        }

        setErrorText('Successfully added restaurant');
    }

    return (
    <div className='form-container'>
        <h1>Add new restaurant</h1>
        <h2 className='form-error'>{errorText}</h2>

        <form onSubmit={handleSubmit(onSubmit)}>
            <RequiredInput name='Name' register={register} errors={errors}/>
            <SelectCuisine register={register} errors={errors}/>
            <RequiredTextarea name='Description' register={register} errors={errors}/>
            <RequiredInput name='Image URL' register={register} errors={errors}/>
            <p>Address:</p>
            <RequiredInput name='Street' register={register} errors={errors}/>
            <RequiredInput name='Post Code' register={register} errors={errors}/>
            <RequiredInput name='City' register={register} errors={errors}/>
            <RequiredInput name='Country' register={register} errors={errors}/>
            <button type='submit'>Add</button>
        </form>
    </div>
    
    );
}

export default AddRestaurant;
