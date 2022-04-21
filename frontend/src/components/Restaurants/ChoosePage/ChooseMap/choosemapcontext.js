import React, {useContext, createContext, useState, useEffect} from 'react'
import ChosenPlacePin from './MapPins/ChosenPlacePin';
import {Marker} from 'react-map-gl';
import { ChooseContext } from '../choosepagecontext';
import { publicFetch } from '../../../../utils/fetch';

const ChooseMapContext = createContext()
const {Provider} = ChooseMapContext;

const ChooseMapProvider = ({children}) => {
    const myAccessToken = 'pk.eyJ1IjoibmV3dG9uZWlybyIsImEiOiJja3kzYTB4cXYwMDJ3MndxbmQ3YWJxZWN1In0.m4dWIXARenX1NHecBzY05w'
    const chooseCon = useContext(ChooseContext)

    const [viewport, setViewport] = useState({
        width: "100%",
        height: "100%",
        latitude: 52.235916284044386,
        longitude: 21.002558026955786,
        zoom: 10,
    });

    const [restaurantType, setRestaurantType] = useState("McDonald's")
    const [chosenLngLat, setChosenLngLat] = useState({lng: 0, lat: 0})
    const [chosenAddress, setChosenAddress] = useState({country: '', city: '', postal_code: '', street: ''})
    const [wrongInputs, setWrongInputs] = useState([0, 0, 0, 0, 0, 0, 0])
    const [slug, setSlug] = useState(restaurantType)

    const [restaurantTemplates, setRestaurantTemplates] = useState([]);

    const updateChosenLng = (newlng) => {
        if (newlng) {
            if (newlng >= -255 && newlng <= 255){
                setChosenLngLat({...chosenLngLat, lng: newlng})
            }
        }
    }

    const updateChosenLat = (newlat) => {
        if (newlat){
            if (newlat >= -90 && newlat <= 90){
                setChosenLngLat({...chosenLngLat, lat: newlat})
            }
        }
    }

    useEffect(() => {
        var wrong_inputs = [0, 0, 0, 0, 0, 0, 0]
        if (chosenLngLat.lat === 0)
        {
            wrong_inputs[1] = 1;
        }
        if (chosenLngLat.lng === 0)
        {
            wrong_inputs[0] = 1;
        }
        if (chosenAddress.country === '')
        {
            wrong_inputs[2] = 1;
        }
        if (chosenAddress.city === '')
        {
            wrong_inputs[3] = 1;
        }
        if (chosenAddress.street === '')
        {
            wrong_inputs[4] = 1;
        }
        if (chosenAddress.postal_code === '')
        {
            wrong_inputs[5] = 1;
        }
        if (slug === '')
        {
            wrong_inputs[6] = 1;
        }
        setWrongInputs(wrong_inputs)
    }, [chosenLngLat, chosenAddress, slug])

    const handleSubmit = async (e) => {
        if (!wrongInputs.includes(1)){
            const {data} = await publicFetch.post("restaurant/add",{
                        'street': chosenAddress.street,
                        'city': chosenAddress.city,
                        'country': chosenAddress.country,
                        'postCode': chosenAddress.postal_code,
                        'longitude': chosenLngLat.lng,
                        'latitude': chosenLngLat.lat,
                        'name': restaurantType,
                        'slug': slug,
                    })
            if (data){
                console.log("Restaurant added successfully")
                chooseCon.setRating(0)
                setChosenAddress({country: '', city: '', postal_code: '', street: ''})
                setRestaurantType("McDonald's")
                setChosenLngLat({lng: 0, lat: 0})
                window.location.reload()
            }
            else{
                console.log("Something went wrong")
            }
        }
    }

    useEffect(() => {
        async function fetchRestaurantTemplates(){
            const data = await chooseCon.getRestaurantTemplates();
            setRestaurantTemplates(data)
        }
        fetchRestaurantTemplates();
    }, [chooseCon])

    const updateRestaurantType = (text) => {
        setRestaurantType(text)
        setSlug(text)
    }

    const handleClick = async (e) => {
        setChosenLngLat({lng: (e.lngLat[0]).toFixed(10), lat: (e.lngLat[1]).toFixed(10)})
        const response = await fetch(`https://api.mapbox.com/geocoding/v5/mapbox.places/${e.lngLat[0]},${e.lngLat[1]}.json?types=address&access_token=${myAccessToken}`);
        const data = await response.json();
        if (data.features.length > 0)
        {
            const address = data.features[0].place_name.split(', ')
            var postal_code_city = address[1].split(' ')
            if (postal_code_city.length === 1){
                postal_code_city = ['-', postal_code_city[0]]
            }
            setChosenAddress({country: address[3], city: postal_code_city[1], postal_code: postal_code_city[0], street: address[0]})
        }
    }

    const getMarker = () => {
        return <Marker latitude={parseFloat(chosenLngLat.lat)} longitude={parseFloat(chosenLngLat.lng)}>
        <ChosenPlacePin></ChosenPlacePin>
        </Marker>
    }
    
    return <Provider value={{
        myAccessToken,
        viewport,
        setViewport,
        restaurantType,
        setRestaurantType,
        chosenLngLat,
        setChosenLngLat,
        updateChosenLng,
        updateChosenLat,
        chosenAddress,
        setChosenAddress,
        slug,
        setSlug,
        restaurantTemplates,
        setRestaurantTemplates,
        updateRestaurantType,
        handleClick,
        getMarker,
        handleSubmit,
        wrongInputs
    }}>
        {children}
    </Provider>
}

export {ChooseMapContext, ChooseMapProvider}
