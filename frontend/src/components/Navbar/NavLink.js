import { Link } from 'react-router-dom';

function NavLink(props) {
    return (
        <li>
            <Link className={props.className} to={props.path}>{props.text}</Link>
        </li>
    )
}

NavLink.defaultProps = {
    className: 'nav-links'
}

export default NavLink;