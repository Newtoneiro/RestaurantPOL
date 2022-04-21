
import { useContext } from 'react';
import { Container, Row, Col } from 'react-grid-system';
import { FaTimes } from 'react-icons/fa';
import { AuthContext } from '../../../../context/AuthContext';
import { publicFetch } from '../../../../utils/fetch';

function Product({product, index}) {
    const authCon = useContext(AuthContext)
    const {name, price, description, imgUrl} = product;

    const removeProduct = async () => {
        const {data} = await publicFetch.delete("dish/remove", {
            params: {
                    id: product.dish_id,
                }
        })
        if (data){
            window.location.reload();
        }
    }

    return (
        <Container fluid>
            <Row>
                <div className={index%2 === 0 ? "product odd" : "product even"}>
                    <Col align="center" xxl={2} xl={2} lg={12}>
                        <img src={imgUrl} alt={name} />
                    </Col>
                    <Col align="center" xxl={3} xl={3} lg={12}>
                        <h1>{name}</h1>
                    </Col>
                    <Col align="center" xxl={1} xl={1} lg={12}>
                        <h2>${price}</h2>
                    </Col>
                    <Col align="center" xxl={3} xl={4} lg={6} md={9} sm={10} xs={11}>
                        <p>{description}</p>
                    </Col>
                    {authCon.isAdmin() && 
                    <Col align="center" className='remove-mark' xxl={1} xl={1} lg={12}>
                        <FaTimes onClick={() => removeProduct()}/>
                    </Col>}
                </div>
            </Row>
        </Container>
    );
}

// <Col xxl={3} xl={4} lg={4} md={6} sm={12} xs={12}>

export default Product;