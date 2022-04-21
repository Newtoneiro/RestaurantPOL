import { Link } from "react-router-dom";
import {BsBackspaceFill} from "react-icons/bs"
import "./pagenotfound.css"

const PageNotFound = () => {
    return <div className="main">
        <h1>PAGE NOT FOUND</h1>
        <div className="footer"></div>
        <Link to={"/"}>
            <div className="back-div">
                <div className="shut">
                    <h3 className="username">Back to homepage</h3>
                    <BsBackspaceFill className="back-icon"/>
                </div>
            </div>
        </Link>
    </div>
}

export default PageNotFound;