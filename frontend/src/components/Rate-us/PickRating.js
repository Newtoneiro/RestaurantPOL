import { AiFillStar } from "react-icons/ai"

const PickRating = ({setHovered, setStars, stars, hovered}) => {
    const ratings = {0: " ", 1: "Utter garbage", 2: "Bearable", 3: "OK", 4: "Pretty good", 5: "Brilliant!"}
    
    const getRating = () => {
        return <h4 key={stars} className="rating-desc">{ratings[stars]}</h4>
    }
    
    return <>
            <div className="star-wrapper" onMouseLeave={() => setHovered(0)}>
                <AiFillStar className={`star ${hovered > 0?hovered >=1 && 'lit-star':stars >= 1 && 'lit-star'}`} onClick={() => setStars(1)} onMouseEnter={() => setHovered(1)}></AiFillStar>
                <AiFillStar className={`star ${hovered > 0?hovered >=2 && 'lit-star':stars >= 2 && 'lit-star'}`}onClick={() => setStars(2)} onMouseEnter={() => setHovered(2)}></AiFillStar>
                <AiFillStar className={`star ${hovered > 0?hovered >=3 && 'lit-star':stars >= 3 && 'lit-star'}`}onClick={() => setStars(3)} onMouseEnter={() => setHovered(3)}></AiFillStar>
                <AiFillStar className={`star ${hovered > 0?hovered >=4 && 'lit-star':stars >= 4 && 'lit-star'}`}onClick={() => setStars(4)} onMouseEnter={() => setHovered(4)}></AiFillStar>
                <AiFillStar className={`star ${hovered > 0?hovered >=5 && 'lit-star':stars >= 5 && 'lit-star five-stars'}`}onClick={() => setStars(5)} onMouseEnter={() => setHovered(5)}></AiFillStar>
            </div>
            <div className="rating-description">
                {getRating()}
            </div>
        </>
}

export default PickRating