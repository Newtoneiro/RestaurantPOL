import React, {createContext, useState, useMemo} from 'react'
import { publicFetch } from '../../../utils/fetch';

const ChooseContext = createContext();
const {Provider} = ChooseContext;

const ChooseProvider = ({ children }) => {
    const [restaurants, setRestaurants] = useState([]);
    const [cuisine, setCuisine] = useState('%');
    const [rating, setRating] = useState(0);

    useMemo(() => getRestaurants(cuisine, rating), [cuisine, rating])
        .then(data => {setRestaurants(data)}
    );

    async function getRestaurants(cuisine, rating) {
        const { data } = await publicFetch.get('/restaurant/find/', {
            params: {
                cuisine: cuisine,
                rating: rating
            }
        });
        return data;
    }

    async function getRestaurantTemplates() {
        const { data } = await publicFetch.get('/restaurant/getTemplates/', {
        });
        return data;
    }

    return (
        <Provider
            value={{
                restaurants,
                setRestaurants,
                cuisine,
                setCuisine,
                rating,
                setRating,
                getRestaurants,
                getRestaurantTemplates,
            }}
        >
        { children }
        </Provider>
    )
}

export { ChooseContext, ChooseProvider };