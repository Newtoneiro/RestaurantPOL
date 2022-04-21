import { useParams } from 'react-router-dom';
import { publicFetch } from '../../../utils/fetch';
import { AuthContext } from '../../../context/AuthContext';
import { useState, useEffect, useContext } from 'react';

import Products from './Products/Products';
import RestaurantReview from './RestaurantReview/RestaurantReview';

import PageNotFound from '../../PageNotFound/PageNotFound';
import "./detailpage.css"
import AvgRating from '../../Rate-us/AvgRating';
import AdminPanel from './AdminPanel';

import BarChartt from './BarChartt/BarChartt';

function DetailPage() {
    let { restaurantSlug } = useParams();
    const [restaurant, setRestaurant] = useState({});
    const authCon = useContext(AuthContext)
    const [disrev, setDisrev] = useState(0)

    useEffect(() => {
        async function fetchData() {
            const { data } = await publicFetch.get('/restaurant/get/', {
                params: {
                    slug: restaurantSlug
                }
            });
            setRestaurant(data);
        }
        fetchData();
    }, [restaurantSlug]);

    const removeRestaurant = async () => {
        const { data } = await publicFetch.delete('/restaurant/remove/', {
                params: {
                    id: restaurant.restaurantId,
                }
            });
        if (data){
            window.location.reload();
        }
        else{
            console.log('Usuwanie restauracji nie powiodło się')
        }
    }

    const [dishWindow, setDishWindow] = useState(false);
    const [background, setBackground] = useState(false);

    function showDishPopup() {
        setBackground(true);
        setDishWindow(!dishWindow);
    }
    
    return (<>
        {restaurant?<>
        {background && <div className='background' onClick={() => {setBackground(false); setDishWindow(false)}}></div>}
        {dishWindow && <AdminPanel setBackground={setBackground} setDishWindow={setDishWindow} restaurant={restaurant}/>}
        <div className='main-container'>
            <img className="detail-img" src={restaurant.imgUrl} alt={restaurant.name} />
            <AvgRating avgRating={restaurant.rating}></AvgRating>
            <div className='address-div'>
                <h2>{restaurant.country}</h2>
                <h4>{restaurant.city}</h4>
                <p>{restaurant.street}</p>
                <div className="footer"></div>
            </div>
            <h1>{restaurant.name}</h1>
            <p className="detail-desc">{restaurant.description}</p>
            {authCon.isAdmin() &&
            <>
                <div onClick={showDishPopup} className="button-dish add">Add dish</div>
                <div onClick={removeRestaurant} className='button-dish cancel'>Remove Restaurant</div>
            </>}
            <div className='switch-container'>
                <div className='choice-container'>
                    <div className={(disrev===0)?'choice active':'choice'} onClick={() => setDisrev(0)}>Dishes</div>
                    <div className='footer' style={{width: '40%'}}></div>
                </div>
                <div className='choice-container'>
                    <div className={(disrev===1)?'choice active':'choice'} onClick={() => setDisrev(1)}>Reviews</div>
                    <div className='footer' style={{width: '40%'}}></div>
                </div>
                <div className='choice-container'>
                    <div className={(disrev===2)?'choice active':'choice'} onClick={() => setDisrev(2)}>Attendance</div>
                    <div className='footer' style={{width: '40%'}}></div>
                </div>

            </div>
            {(disrev===1)?(
            <RestaurantReview restaurant={restaurant}/>):
            (disrev===0)?(
            <Products restaurantId={restaurant.restaurantId}/>):
            <BarChartt restaurant={restaurant}/>}

        </div></>:
        <PageNotFound/>}
        </>
    );
}

export default DetailPage;
