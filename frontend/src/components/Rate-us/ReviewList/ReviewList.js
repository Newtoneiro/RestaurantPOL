import { useContext } from "react";
import { RateContext } from "../RateContext";
import { AiFillStar } from "react-icons/ai"
import { AuthContext } from "../../../context/AuthContext";
import { FaTimes} from "react-icons/fa"
import "./reviewlist.css"

const ReviewList = ({userName}) => {
    const {reviews, removeReview} = useContext(RateContext)
    const authCon = useContext(AuthContext)

    return <div className="reviews-grid">
        {reviews.map((item) => {
            return <div key={item.id} className="single-review">
                {(item.username === userName || authCon.isAdmin()) && <FaTimes className="remove-button" onClick={() => removeReview(item.id)}/>}
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
}

export default ReviewList;