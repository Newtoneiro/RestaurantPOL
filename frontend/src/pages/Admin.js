
import AddDish from '../components/Admin/AddDish';
import AddRestaurant from '../components/Admin/AddRestaurant';
import '../components/Admin/Admin.css';

function Admin() {
    return (
    <div className='add-items'>
        <AddRestaurant />
        <AddDish />
    </div>
    
    );
}

export default Admin;
