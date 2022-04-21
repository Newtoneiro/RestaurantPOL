import { useState, useContext, useEffect } from "react"
import { AuthContext } from "../../../../context/AuthContext"
import { publicFetch } from "../../../../utils/fetch"
import PickRating from "../../../Rate-us/PickRating"
import { FaTimes } from "react-icons/fa"
import { AiFillStar } from "react-icons/ai"

import "./restaurantreview.css"

const RestaurantReview = ({restaurant}) => {
    const [stars, setStars] = useState(3)
    const [hovered, setHovered] = useState(0)
    const [text, setText] = useState('')
    const [reviews, setReviews] = useState([])

    const authCon = useContext(AuthContext)

    const fetchData = async () => {
        const {data} = await publicFetch.get("/rating/getRatings", {
            params: {
                restaurantId: restaurant.restaurantId,
            }
        })
        if (data){
            setReviews(data)
        }
    }

    useEffect(() => {
        const updateData = async () => {
            const {data} = await publicFetch.get("/rating/getRatings", {
            params: {
                restaurantId: restaurant.restaurantId,
            }
        })
        if (data){
            setReviews(data)
        }
        }
        updateData()
    }, [restaurant])
    
    const handleSubmit = async () => {
        const fetchUser = async () => {
            const userInfo = JSON.parse(localStorage.getItem('userInfo'));
            return userInfo.user_id
        }
        const user_id = await fetchUser()
        const {data} = await publicFetch.post("/rating/add", {
            restaurantId: restaurant.restaurantId,
            userId : user_id,
            descript: text,
            rating: stars,
        })
        if (data){
            await fetchData()
            setStars(3)
            setText("")
        }
    }

    const removeRating = async (ratingId) => {
        const {data} = await publicFetch.delete("/rating/remove",{
            params: {
                ratingId: ratingId
            }
        });
        if (data){
            await fetchData()
        }
    }

    return (<div className="restaurant-review-page">
        <div className="review-form">
            <h2>Rate this restaurant</h2>
            <PickRating setHovered={setHovered} setStars={setStars} stars={stars} hovered={hovered}/>
            <textarea maxLength="180" className="rating-input" type='text' placeholder="Tell us something" value={text} onChange={(e) => setText(e.target.value)}></textarea>
            <button className='submit-button' type="submit" id="submit-button" onClick={() => handleSubmit()}>Submit!</button>
        </div>
        <div className="review-list">
            {reviews.map((item) => {
                return <div key={item.ratingId} className="single-review">
                {(item.userId === authCon.authState.user_id || authCon.isAdmin()) && <FaTimes className="remove-button" onClick={() => removeRating(item.ratingId)}/>}
                <div className="stars">
                    {item.rating >= 1 && <AiFillStar/>}
                    {item.rating >= 2 && <AiFillStar/>}
                    {item.rating >= 3 && <AiFillStar/>}
                    {item.rating >= 4 && <AiFillStar/>}
                    {item.rating >= 5 && <AiFillStar/>}
                </div>
                <div className="review-text">
                    "{item.descript}"
                </div>
                <div className="review-user">
                    - {item.username}
                </div>
                </div>
            })}
        </div>
    </div>    
    );
}

export default RestaurantReview