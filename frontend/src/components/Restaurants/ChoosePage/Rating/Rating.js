import './rating.css';

function Rating(props) {

    function makeStars(rating) {
        let stars = [];
        for (let i = 0; i < rating; i++) {
            stars.push(<i key={i} className="fas fa-star"></i>);
        }
        if (stars.length !== 0){
            return stars;
        }
        else
        {
            return <h3>Not yet rated</h3>
        }
    }

    const starsCount = parseInt(parseFloat(props.ratings))

    return (
        <div className="rating">
            {makeStars(starsCount)}
            {starsCount !== parseFloat(props.ratings) ? <i className="fas fa-star-half"></i> : null}
        </div>
    );
}

export default Rating;