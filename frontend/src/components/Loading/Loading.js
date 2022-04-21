import {GoPrimitiveDot} from "react-icons/go"
import "./loading.css"

const Loading = () => {
    return <div className="loading">
        <GoPrimitiveDot className="dot" id="dot1"/>
        <GoPrimitiveDot className="dot" id="dot2"/>
        <GoPrimitiveDot className="dot" id="dot3"/>
        <GoPrimitiveDot className="dot" id="dot4"/>
    </div>
}

export default Loading;