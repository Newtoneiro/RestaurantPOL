
import { useState, useRef, useEffect } from 'react';
import { Link } from 'react-router-dom';
import NavLink from './NavLink';
import Dropdown from './Dropdown';
import './navbar.css'

function Navbar() {
    const ref = useRef();
    const [dropdown, setDropdown] = useState(false);
    const [clicked, setClicked] = useState(false);

    useEffect(() => {
        function checkIfClickedOutside(event) {
            if (dropdown && ref.current && !ref.current.contains(event.target)) {
                setDropdown(false)
            }
        }
    
        document.addEventListener("mousedown", checkIfClickedOutside)
    
        return () => {
            document.removeEventListener("mousedown", checkIfClickedOutside)
        }
      }, [dropdown])

    return (
    <nav className='navbar-items' ref={ref}>
        <Link to="/"><h1 className='navbar-logo'> Restaurantpol <i className='fas fa-hamburger' /></h1></Link>
        <div className='menu-icon' onClick={() => setClicked(!clicked)}>
            <i className={clicked ? 'fas fa-times' : 'fas fa-bars'} />
        </div>
        <ul className={clicked ? 'nav-menu active' : 'nav-menu'}>
            <NavLink path='/' text='Home'/>
            <NavLink path='/restaurants' text='Find Restaurant'/>
            <NavLink path='/rate-us' text='Rate us'/>
            <NavLink path='/about' text='About'/>
            <NavLink className='nav-links-mobile' path='/login' text='Log Out'/>
        </ul>
        <p className="user-dropdown" onClick={() => setDropdown(!dropdown)}><i className="fas fa-solid fa-user" /></p>
        {dropdown && <Dropdown close={() => setDropdown(false)}/>}
    </nav>
    )
}

export default Navbar;