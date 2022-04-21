import "./products.css"
import { publicFetch } from "../../../../utils/fetch";
import { useState, useMemo } from "react";
import Product from "./Product";

function Products(props) {

    const[products, setProducts] = useState([]);
    async function getDishes(restaurantId) {
        const { data } = await publicFetch.get('/dish/all/', {
            params: {
                restaurant_id: restaurantId,
            }
        });
        return data;
    }

    useMemo(() => getDishes(props.restaurantId), [props.restaurantId])
        .then(data => {setProducts(data)});

    return (
        <div className="products">
            {products && products.map((product, i) => <Product key={i} index={i} product={product} />)}
        </div>
    );
}

export default Products;