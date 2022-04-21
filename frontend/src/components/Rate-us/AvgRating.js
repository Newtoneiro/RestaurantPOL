import {AiFillStar} from 'react-icons/ai'

function AvgRating({avgRating}){
    const HalfStar = () => {
        return <div className="half-star-container"><AiFillStar className="half-star"/><AiFillStar/></div>
    }

    return <div className="avg-rating-container">
            <div className="avg-rating">
                {avgRating >= 1?<AiFillStar className="full-star"/>:(avgRating >= 0.5?<HalfStar/>:<AiFillStar/>)}
                {avgRating >= 2?<AiFillStar className="full-star"/>:(avgRating >= 1.5?<HalfStar/>:<AiFillStar/>)}
                {avgRating >= 3?<AiFillStar className="full-star"/>:(avgRating >= 2.5?<HalfStar/>:<AiFillStar/>)}
                {avgRating >= 4?<AiFillStar className="full-star"/>:(avgRating >= 3.5?<HalfStar/>:<AiFillStar/>)}
                {avgRating >= 5?<AiFillStar className="full-star"/>:(avgRating >= 4.5?<HalfStar/>:<AiFillStar/>)}
            </div>
            <h3>Average Rating</h3>
            <div className="footer" style={{height: '0.1em', width: '5rem', margin: '0.3em 0 0 0'}}/>
        </div>
}

export default AvgRating