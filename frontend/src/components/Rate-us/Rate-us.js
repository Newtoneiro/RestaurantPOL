import { useContext, useEffect, useState } from "react";
// import {FaStarHalf} from "react-icons/fa"
import "./rate-us.css"
// import Reviews from "./Fake_reviews";
import Loading from "../Loading/Loading";
import ReviewList from "./ReviewList/ReviewList";
// Contexts
import { RateContext } from "./RateContext";
import { AuthContext } from '../../context/AuthContext';

import {publicFetch} from '../../utils/fetch';
import AvgRating from "./AvgRating";
import PickRating from "./PickRating"

function Rate() {
    const [stars, setStars] = useState(3)
    const [hovered, setHovered] = useState(0)
    const [text, setText] = useState('')
    const [loading, setLoading] = useState(true)
    const [avgRating, setAvgRating] = useState(0.0)

    const {authState} = useContext(AuthContext)
    
    const [reviews, setReviews] = useState([])
        useEffect(() => {
            setLoading(true)
            getData()
            updateAvgRating()
            setLoading(false)
    }, [])

    const updateAvgRating = async () => {
        const { data } = await publicFetch.get('review/getAvgStars');
        setAvgRating(data)
    }

    const getData = async () => {
        const { data } = await publicFetch.get('review/all');
        setReviews(data)
    }

    const updateText = (txt) => {
        setText(txt)
        document.getElementById("submit-button").innerHTML = "Submit!"
    }

    const handleSubmit = async (e) => {
        e.preventDefault()
        if (text === ""){
            e.target.innerHTML= "Insert text"
        }
        else {
            const {data} = await publicFetch.post("review/add",{
                    'username': authState.firstName,
                    'rating': stars,
                    'descript': text,
                })
            
            setLoading(true)
            if (data){
                await getData()
                updateAvgRating()
            }
            else {
                console.log("Post failed")
            }
            setLoading(false)   
            
            setText('')
            setStars(3)
            document.getElementById("alertbox").className = "alert";
            const timeout = await setTimeout(()=>{
                document.getElementById("alertbox").className = "alert hidden"
                }, 3000)
            return () => clearTimeout(timeout)
        }
    }

    const removeReview = async (review_id) => {
        const {data} = await publicFetch.post("review/remove", {"review_id": review_id})
        setLoading(true)
        if (data){
            getData()
            updateAvgRating()
        }
        else {
            console.log("Cant remove this review")
        }
        setLoading(false)
    }

    return (
    <div className="rate-us-page">
        <div className="alert hidden" id="alertbox">Posted!</div>
        <AvgRating avgRating={avgRating}/>
        <div className="rate-us-form">
            <h2>Rate us!</h2>
            <PickRating setHovered={setHovered} setStars={setStars} stars={stars} hovered={hovered}/>
            <textarea maxLength="180" className="rating-input" type='text' placeholder="Tell us something" value={text} onChange={(e) => updateText(e.target.value)}></textarea>
            <button className='submit-button' type="submit" id="submit-button" onClick={async (e) => await handleSubmit(e)}>Submit!</button>
        </div>
        <div className="reviews-container">
            <div className="title">
                {loading?<Loading/>:<>
                <h2>Your Reviews</h2>
                <div className="footer"/>
                </>}
            </div>
            <RateContext.Provider value={{reviews, removeReview}}>
                {loading || <ReviewList userName={authState.firstName}/>}
            </RateContext.Provider>
        </div>
    </div>
    );
}

export default Rate;