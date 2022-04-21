import { useContext } from 'react';
import { Container, Row } from 'react-grid-system';
import { ChooseContext } from './choosepagecontext';
import Restaurant from './Restaurant';

function RestaurantsList() {
    const chooseCon = useContext(ChooseContext)
    
    return (
    <div className="restaurants-list">
        <Container>
            <Row>
                {chooseCon.restaurants.map(data => (
                    <Restaurant data={data} key={data.restaurantId}/>
                ))}
            </Row>
        </Container>
    </div>
    );
}

export default RestaurantsList;