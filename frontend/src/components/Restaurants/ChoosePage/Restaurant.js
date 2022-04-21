
import { Link, useRouteMatch } from 'react-router-dom';
import { Col } from 'react-grid-system';
import Rating from './Rating/Rating';

function Restaurant(props) {

    let match = useRouteMatch();
    return (
        <Col xxl= {3} xl={4} lg={4} md={6} sm={12} xs={12}>
            <div className="restaurant">
                <Link to={`${match.url}/${props.data.slug}`}>
                    <img className="logo" src={props.data.imgUrl} alt={props.data.name}></img>
                    <div className='address'>
                        <h4>{props.data.city}</h4>
                        <p className='color'>{props.data.country}</p>
                        <p>st. {props.data.street}</p>
                    </div>
                    <h2>{props.data.name}</h2>
                </Link>
                <Rating ratings={props.data.rating}/>
                <h3>{props.data.cuisine}</h3>
            </div>
        </Col>
    );
}

export default Restaurant;