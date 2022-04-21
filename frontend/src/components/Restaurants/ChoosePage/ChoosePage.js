import { useContext } from 'react';
import RestaurantsList from './RestaurantsList';
import SelectCuisine from './Filters/SelectCuisine';
import SelectRating from './Filters/SelectRating';
import './restaurants.css';
import ChooseMap from './ChooseMap/ChooseMap'
import { ChooseContext } from './choosepagecontext';
import { ChooseMapProvider } from './ChooseMap/choosemapcontext'

function ChoosePage() {
    const chooseCon = useContext(ChooseContext)
    
    return (
        <div className='main-container'>
            <h2>All Available Restaurants</h2>
            <div className="footer" style={{width: '30%'}}></div>
            <p>Select your restaurant!</p>
            <ChooseMapProvider>
                <ChooseMap restaurants={chooseCon.restaurants}/>
            </ChooseMapProvider>
            <h2>Filters</h2>
            <div className="footer" style={{width: '10%'}}></div>
            <div className="params-selects">
                <SelectCuisine/>
                <SelectRating/>
            </div>
            <RestaurantsList/>
        </div>
    );
}

export default ChoosePage;