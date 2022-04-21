import { useContext } from "react"
import { AuthContext } from "../../../context/AuthContext"
import AddDish from "../../Admin/AddDish"

const AdminPanel = ({setBackground, setDishWindow, restaurant}) => {
    const authCon = useContext(AuthContext)

    return <>
        {authCon.isAdmin() && <div className='admin-panel popup'>
                <AddDish restaurant={restaurant}/>
                <div onClick={() => {setBackground(false); setDishWindow(false)}} className='button-dish cancel'>Cancel</div>
            </div>
        }
    </>
}

export default AdminPanel
