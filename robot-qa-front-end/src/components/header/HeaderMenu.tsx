import { Link } from "react-router-dom"
import MenuContainer from "./MenuContainer"

function HeaderMenu() {
    return (
        <MenuContainer>
                <li>
                    <Link to="/">Home</Link>
                </li>
                <li>
                    <Link to="robots">Robots</Link>
                </li>
                <li>
                    <Link to="chat">Chat</Link>
                </li>
                <li>
                    <Link to="about">About</Link>
                </li>
        </MenuContainer>
    )
}

export default HeaderMenu