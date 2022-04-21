
import { useState, useEffect, useContext } from "react";
import { publicFetch } from "../../../../utils/fetch";
import { ChooseContext } from "../choosepagecontext";
import './filters.css';

function SelectCuisine() {
    const [cuisines, setCuisines] = useState([]);
    const chooseCon = useContext(ChooseContext)

    useEffect(() => {
        async function fetchData() {
            const { data } = await publicFetch.get('/cuisine/all/');
            setCuisines(data)
        }
        fetchData();
    }, []);
    
    return(
        <div className="select-param">
            <p>Cuisine</p>
            <select onChange={event => chooseCon.setCuisine(event.target.value)}>
                <option value='%'>All</option>
                {cuisines.map(cuisine => <option key={cuisine.cuisineId} value={cuisine.name}>{cuisine.name}</option>)}
            </select>
        </div>
    );
}

export default SelectCuisine;