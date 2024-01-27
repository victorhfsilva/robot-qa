import HeaderContainer from "./HeaderContainer"
import HeaderMenu from "./HeaderMenu"
import HeaderTitle from "./HeaderTitle"

function Header() {
    return (
        <HeaderContainer>
            <HeaderTitle>
                Robot QA
            </HeaderTitle>
            
            <nav>
                <HeaderMenu />
            </nav>
        </HeaderContainer>
    )
}

export default Header