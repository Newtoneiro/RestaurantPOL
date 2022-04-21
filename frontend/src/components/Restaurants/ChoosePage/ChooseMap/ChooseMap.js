import * as React from 'react';
import { useContext } from 'react';
import ReactMapGL, {Marker} from 'react-map-gl';
import 'mapbox-gl/dist/mapbox-gl.css';


import { ChooseContext } from '../choosepagecontext';
import { AuthContext } from '../../../../context/AuthContext';
import './choosemap.css'

import RestaurantPin from './MapPins/RestaurantPin';
import { Link, useRouteMatch } from 'react-router-dom';

import { ChooseMapContext } from './choosemapcontext';

function ChooseMap() {
  const ChooseMapCon = useContext(ChooseMapContext)

  const match = useRouteMatch()
  const chooseCon = useContext(ChooseContext)
  const authCon = useContext(AuthContext)

  return <div className='map-container'>
            <ReactMapGL className='map'
            {...ChooseMapCon.viewport}
            onViewportChange={nextViewport => ChooseMapCon.setViewport(nextViewport)}
            mapboxApiAccessToken={ChooseMapCon.myAccessToken}
            mapStyle='mapbox://styles/newtoneiro/cky3ervk20ejy14nu6zzk2bdt'
            onClick={(e) => ChooseMapCon.handleClick(e)}>
            {chooseCon.restaurants.map((restaurant) => {
                return <Marker key={restaurant.restaurantId} longitude={restaurant.longitude} latitude={restaurant.latitude}>
                  <Link to={`${match.url}/${restaurant.slug}`}>
                    <RestaurantPin restaurant={restaurant}/>
                  </Link>
                </Marker>
            })}
            {(ChooseMapCon.chosenLngLat.lng !== 0 || ChooseMapCon.chosenLngLat.lat !== 0) && ChooseMapCon.getMarker()}
            </ReactMapGL>
            {authCon.isAdmin() && 
            <div className='admin-control-container'>
              <div className='admin-control-panel'>
                <h2>Add new restaurant</h2>
                <div className='geocordinputs'>
                    <div className='input-label'>
                      <label htmlFor='longitude'>Longitude</label>
                      <input id='longitude' min={-180} max={80} className={ChooseMapCon.wrongInputs[0] === 1?'geocordinput error':'geocordinput'} type='text' value={ChooseMapCon.chosenLngLat.lng} onChange={(e) => ChooseMapCon.updateChosenLng(e.target.value)}/>
                    </div>
                    <div className='input-label'>
                      <label htmlFor='latitude'>Latitude</label>
                      <input id='latitude' min={-90} max={90} className={ChooseMapCon.wrongInputs[1] === 1?'geocordinput error':'geocordinput'} type='text' value={ChooseMapCon.chosenLngLat.lat} onChange={(e) => ChooseMapCon.updateChosenLat(e.target.value)}/>
                    </div>
                </div>
                <div className='address-inputs'>
                  <div className='input-label'>
                      <label htmlFor='country'>Country</label>
                      <input id='country' type='text' className={ChooseMapCon.wrongInputs[2] === 1?'error':''} value={ChooseMapCon.chosenAddress.country} onChange={(e) => ChooseMapCon.setChosenAddress({...ChooseMapCon.chosenAddress, country: e.target.value})}/>
                  </div>
                  <div className='input-label'>
                      <label htmlFor='city'>City</label>
                      <input id='city' type='text' className={ChooseMapCon.wrongInputs[3] === 1?'error':''} value={ChooseMapCon.chosenAddress.city} onChange={(e) => ChooseMapCon.setChosenAddress({...ChooseMapCon.chosenAddress, city: e.target.value})}/>
                  </div>
                  <div className='input-label'>
                      <label htmlFor='street'>Street</label>
                      <input id='street' type='text' className={ChooseMapCon.wrongInputs[4] === 1?'error':''} value={ChooseMapCon.chosenAddress.street} onChange={(e) => ChooseMapCon.setChosenAddress({...ChooseMapCon.chosenAddress, street: e.target.value})}/>
                  </div>
                  <div className='input-label'>
                      <label htmlFor='postal-code'>Postal code</label>
                      <input id='postal-code' type='text' className={ChooseMapCon.wrongInputs[5] === 1?'error':''} value={ChooseMapCon.chosenAddress.postal_code} onChange={(e) => ChooseMapCon.setChosenAddress({...ChooseMapCon.chosenAddress, postal_code: e.target.value})}/>
                  </div>
                </div>
                  <div className='input-label'>
                    <label htmlFor='restaurant_type'>Restaurant</label>
                    <select id='restaurant_type' value={ChooseMapCon.restaurantType} onChange={(e) => ChooseMapCon.updateRestaurantType(e.target.value)}>
                      {ChooseMapCon.restaurantTemplates.map((type) => {
                        return <option key={type} value={type}>{type}</option>
                      })}
                    </select>
                  </div>
                  <div className='input-label'>
                      <label htmlFor='slug'>Restaurant Slug</label>
                      <input id='slug' type='text' className={ChooseMapCon.wrongInputs[6] === 1?'error':''} value={ChooseMapCon.slug} onChange={(e) => ChooseMapCon.setSlug(e.target.value)}/>
                  </div>
              </div>
              <input type='submit' className='submit-button' onClick={ChooseMapCon.handleSubmit} value='Add new restaurant'></input>
              </div>
            }
        </div>
}

export default ChooseMap;
